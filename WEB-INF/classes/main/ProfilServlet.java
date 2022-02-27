package main;

import user.Book;
import user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/profil")
public class ProfilServlet extends HttpServlet
{
    private static final long serialVersionUID = 112454L;

    private ModelFacade facade = null;

    public ProfilServlet()
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
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            session.setAttribute("user", user);
            request.setAttribute("user", user);

            String lastname = user.getLastname();

            Collection<Book> books = this.facade.findBooksfromUser(lastname);
            session.setAttribute("books", books);

            Collection<Book> editableBooks = this.facade.findUsersEditableBooks(lastname);
            session.setAttribute("editablebooks",editableBooks);

            Collection<Book> soldbooks = this.facade.getSoldBooks(lastname);
            session.setAttribute("soldbooks", soldbooks);

            String summary = this.facade.getSummary(lastname);
            session.setAttribute("summary", summary);

            getServletContext().getRequestDispatcher("/profil.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}