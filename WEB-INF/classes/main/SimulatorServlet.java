package main;

import simulation.Auction;
import simulation.Simulator;
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

@WebServlet("/simulations")
public class SimulatorServlet extends HttpServlet {

    private static final long serialVersionUID = 11234;
    private ModelFacade facade = null;

    public SimulatorServlet() { super(); }

    @Override
    public void init() throws ServletException {
        super.init();
        this.facade = ModelFacade.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Collection<Book> listOfBooks = this.facade.getAllBooks();
        List<Auction> listOfAuctions = new ArrayList<>();
        String state = "";

        for (Book b: listOfBooks) {
            if(Objects.equals(b.getActive(),"aktiv") && b.isSold()) {
                b.setInActive();
            } else if (Objects.equals(b.getActive(),"aktiv") && !b.isSold()) {
                // do a string from the book state which is an integer
                if (b.getState() == 1) {
                    state = "Wie neu";
                } else if (b.getState() == 2) {
                    state = "Gut";
                } else if (b.getState() == 3) {
                    state = "Mit Gebrauchsspuren";
                } else {
                    state = "undefined";
                }

                // Build an auction object to handle them easier
                Auction auction = new Auction(b.getId(), b.getTitle(),b.getUserAsString(), state,b.getPrice(), b.getMinPrice(), b.isSold());
                // give the auction objects to the simulation controller -> Singleton
                listOfAuctions.add(auction);
            }
        }

        // execute the fancy simulator with insane multi-threads
        for (Auction a : listOfAuctions) {

            Simulator.bidJob(a);
            Simulator.proofAuction(a);
            for (Book b : listOfBooks) {
                if(Objects.equals(b.getId(),a.getId()) && a.isSold()) {
                    b.setSold();
                    String auctionPricing = a.getPrice().replace(",",".");
                    b.setPrice(Double.parseDouble(auctionPricing));
                }
            }
        }

        Collection<Book> editableBooks = this.facade.findUsersEditableBooks(user.getLastname());
        session.setAttribute("editablebooks",editableBooks);

        request.setAttribute("auctions", listOfAuctions);
        session.setAttribute("books", listOfBooks);

        getServletContext().getRequestDispatcher("/auctions.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
