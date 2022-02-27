package main;

import user.Book;
import user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/createAuctions")
public class CreateBookServlet extends HttpServlet {

    private static final long serialVersionUID = 112345;
    private ModelFacade facade = null;

    public CreateBookServlet() { super(); }

    @Override
    public void init() throws ServletException {
        super.init();
        this.facade = ModelFacade.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        int state = -1;

        String isbn = request.getParameter("isbn");
        String booktitle = request.getParameter("booktitle");
        String publisher = request.getParameter("publisher");
        String status = request.getParameter("status");
        String price = request.getParameter("price");
        String checked = request.getParameter("activate");

        // isbn parser, cause of possible expressions on client-side with "-"
        try {
            isbn.replace("-","");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // cast status from string to int
        try {
            state = Integer.parseInt(status);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // proof the price and format to a decimal
        double priceval = 0.0;
        try {
            priceval = Double.parseDouble(price.replace(",","."));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // make a String from checkbox
        if (Objects.equals(checked, "on")) {
            checked = "aktiv";
        } else {
            checked = "";
        }

        int newID = this.facade.getAllBooks().size() + 1;
        Book newBook = new Book(newID, isbn, booktitle, publisher, state, priceval, 0.0, checked, false);

        // validate on server side, if valid user is logged on
        if (user == null) {
            // have to save the book object for adding after successful login
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        this.facade.setBookToContainer(newBook);
        newBook.addUser(user);
        user.addBuch(newBook);

        getServletContext().getRequestDispatcher("/login").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
