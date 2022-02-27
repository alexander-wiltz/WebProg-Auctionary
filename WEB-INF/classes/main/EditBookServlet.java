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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@WebServlet("/editAuctions")
public class EditBookServlet extends HttpServlet {

    private static final long serialVersionUID = 11234;
    private ModelFacade facade = null;

    public EditBookServlet() { super(); }

    @Override
    public void init() throws ServletException {
        super.init();
        this.facade = ModelFacade.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String lastname = user.getLastname();

        // validate on server side, if valid user is logged on
        if (user == null) {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        // getting the selection value, if the user want to delete or to set the book to an auction
        String selectionValue = request.getParameter("controlaction");
        List<Book> markedBooks = new ArrayList<>();

        for (Book b : this.facade.findBooksfromUser(lastname)) {
            String bookState = request.getParameter(String.valueOf(b.getId()));
            // take book if is not sold and is activated in order to the book id
            if(!b.isSold() && Objects.equals(bookState, "on")) {
                markedBooks.add(b);
            }
        }

        // proof if user want to set active or to delete an entry
        if (Objects.equals(selectionValue, "commit")) {
            for (Book b : markedBooks) {
                this.facade.setToActive(b);
            }
        } else if (Objects.equals(selectionValue, "delete")) {
            for (Book b : markedBooks) {
                // because of not using a shopping cart, the link between the user and the book have to be done in both containers
                // and after delinking, the book object has to be removed overall
                user.removeBookFromUser(b);
                b.removeUserFromBook(user);
                this.facade.removeBook(b);
            }
        }

        // get a new list of books from user and set the list to refresh the session and the current request
        Collection<Book> books = this.facade.findBooksfromUser(lastname);
        session.setAttribute("books", books);

        //write the sold books for the profil.jsp to get the current list of sold books
        Collection<Book> soldbooks = this.facade.getSoldBooks(lastname);
        session.setAttribute("soldbooks", soldbooks);

        getServletContext().getRequestDispatcher("/profil.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
