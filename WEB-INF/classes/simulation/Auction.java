package simulation;

import java.io.Serializable;

public class Auction implements Serializable  {

    private static final long serialVersionUID = 66902152776756579L;

    private int id;
    private String title;
    private String seller;
    private String state;
    private String price;
    private String minPrice;
    private boolean isSold;

    public Auction(int id, String title, String seller, String state, String price, String minPrice, boolean isSold) {
        this.id = id;
        this.title = title;
        this.seller = seller;
        this.state = state;
        this.price = price;
        this.minPrice = minPrice;
        this.isSold = isSold;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSeller() {
        return this.seller;
    }

    public String getState() {
        return this.state;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMinPrice() {
        return this.minPrice;
    }

    public boolean isSold() {
        return this.isSold;
    }

    public void setSold() { this.isSold = true; }



    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", seller='" + seller + '\'' +
                '}';
    }
}
