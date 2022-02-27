package simulation;

import java.util.ArrayList;
import java.util.List;

public class Simulator {

    private List<Auction> auctionContainer = new ArrayList<Auction>();
    private static final int MINBIDS = 1;
    private static final int MAXBIDS = 15;
    private static final int MINBIDLEVEL = 1;
    private static final int MAXBIDLEVEL = 9;

    public static void bidJob(Auction auction) {
        double price = Double.parseDouble(auction.getPrice().replace(",","."));

        int numberOfBids = (int)(Math.random() * (MAXBIDS - MINBIDS)) + MINBIDS;
        for (int i = 0; i < numberOfBids; i++) {
            // set a random value between 1 and 9 Euros, in a range of random number of bids
            price += (int)((Math.random() * (MAXBIDLEVEL - MINBIDLEVEL)) + MINBIDLEVEL);
        }

        auction.setPrice(String.valueOf(price));
    }

    public static void proofAuction(Auction auction) {
        double minimumPrice = Double.parseDouble(auction.getMinPrice().replace(",","."));
        double reachedPrice = Double.parseDouble(auction.getPrice().replace(",","."));
        if (minimumPrice <= reachedPrice) {
            auction.setSold();
        }
    }
}