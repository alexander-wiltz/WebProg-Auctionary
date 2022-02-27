package main;

import user.BookShopPersitenceException;
import user.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
    private static final long serialVersionUID = 112324L;
    private ModelFacade facade = null;

    public LoginServlet()
    {
        super();
    }


    @Override
    public void init() throws ServletException {
        super.init();
        this.facade = ModelFacade.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            // double check if user is already logged on
            String userid = request.getParameter("user");
            String password = request.getParameter("password");
            try {
                if (this.facade.checkLogin(userid, password)) { // check login PW and user equals to each other
                    user = this.facade.findUser(userid);

                } else {
                    BookShopPersitenceException e = new BookShopPersitenceException("Passworteingabe falsch!");
                    e.printStackTrace();
                    request.setAttribute("error", e);
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                }
            } catch (BookShopPersitenceException e) {
                request.setAttribute("error", e);
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }

        session.setAttribute("user", user);
        request.setAttribute("user", user);

        getServletContext().getRequestDispatcher("/profil").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}