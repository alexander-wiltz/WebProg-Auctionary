package main;

import user.BookShopPersitenceException;
import user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 112345;
    private ModelFacade facade = null;

    public RegisterServlet() { super(); }

    @Override
    public void init() throws ServletException {
        super.init();
        this.facade = ModelFacade.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            // user already logged on - why are you here? 0o
            getServletContext().getRequestDispatcher("/profil.jsp").forward(request, response);
        }

        String firstname = request.getParameter("vname");
        String lastname = request.getParameter("nname");
        String birth = request.getParameter("birth");
        String address = request.getParameter("address");
        String zipcode = request.getParameter("zipcode");
        String location = request.getParameter("location");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (Objects.equals(lastname, "") || Objects.equals(birth, "") || Objects.equals(zipcode,"") || Objects.equals(location,"") || Objects.equals(email,"") || Objects.equals(password,"")) {
            BookShopPersitenceException e = new BookShopPersitenceException("Füllen Sie alle Pflichtfelder aus!");
        }

        // server side email validation
        String mailx = "(?=.*[a-zA-Z0-9])(@((hs-kl)|(fh-kl))\\.de)";
        Pattern patternMail = Pattern.compile(mailx, Pattern.CASE_INSENSITIVE);
        Matcher matcherMail = patternMail.matcher(email);
        if(!matcherMail.find()) {
            BookShopPersitenceException ex = new BookShopPersitenceException("Sie m&uuml;ssen eine Adresse der Hochschule Kaiserslautern angeben!");
            request.setAttribute("error", ex);
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
        }

        // server side password validation
        String passx = "(?=.{8,})(?=.*[a-zA-Z0-9])(?=.*[!?;+])";
        Pattern patternPass = Pattern.compile(passx, Pattern.CASE_INSENSITIVE);
        Matcher matcherPass = patternPass.matcher(password);
        if(!matcherPass.find()) {
            BookShopPersitenceException ex = new BookShopPersitenceException("Passwortrichtlinie beachten!");
            request.setAttribute("error", ex);
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
        }

        // double check if user already exists
        for (User u : this.facade.getAllUsers()) {
            if (Objects.equals(email,u.getEmail())) {
                BookShopPersitenceException ex = new BookShopPersitenceException("Email bereits verwendet!");
                request.setAttribute("error", ex);
                getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
            }
        }

        int newID = this.facade.getAllUsers().size() + 1;
        User newUser = new User(newID, firstname, lastname, birth, email, address, zipcode,location);
        this.facade.setUserToContainer(newUser);
        byte[] salt = this.facade.generateSalt();
        byte[] pwd = this.facade.generatePassword(password, salt);
        newUser.setSalt(salt);
        newUser.setPwdHash(pwd);

        session.setAttribute("user", newUser);
        request.setAttribute("user", newUser);

        getServletContext().getRequestDispatcher("/profil").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
