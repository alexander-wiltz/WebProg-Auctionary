package user;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {

   private static final long serialVersionUID = 6690657110776756579L;

   private int id;
   private String isbn;
   private String title;
   private String publisher;
   private int state; // 1: new, 2:good, 3:used
   private double minPrice;
   private double price;
   private String active;
   private boolean sold;
   
   private List<User> users = new ArrayList<User>();
   
   public Book(int id, String isbn, String title, String publisher, int state, double minPrice, double price, String active, boolean sold) {
      super();
      this.id = id;
      this.isbn = isbn;
      this.title = title;
      this.publisher = publisher;
      this.state = state;
      this.minPrice = minPrice;
      this.price = price;
      this.active = active;
      this.sold = sold;
   }
   
   public boolean addUser(User user)
   {
      return this.users.add(user);
   }
   
   public List<User> getUsers() {
      List<User>  result = new ArrayList<User>();
      result.addAll( this.users);
      return result;
   }

   public boolean removeUserFromBook(User user) {
      return this.users.remove(user);
   }
   
   public String getUserAsString() {
      StringBuilder strBuild = new StringBuilder();
      
      int i = 0;
      for(i =0; i < this.users.size()-1; i++ ) {
         User user = this.users.get(i);
         strBuild.append( user.getFirstname() + " " + user.getLastname() + ", ");
      }
      User user = this.users.get(i);
      strBuild.append( user.getFirstname() + " " + user.getLastname() );
         
      return strBuild.toString();
   }
   
   public int getId()
   {
      return this.id;
   }

   public String getIsbn() { return this.isbn; }

   public int getState() { return this.state; }

   public boolean isSold() {
      return this.sold;
   }

   public void setSold() {
      this.sold = true;
   }

   public void setActive() {
      this.active = "aktiv";
   }

   public void setInActive() {
      this.active = "";
   }

   public String getTitle()
   {
      return this.title;
   }
   
   public String getPublisher()
   {
      return this.publisher;
   }
   
   public String getPrice() {
      NumberFormat frmt = DecimalFormat.getInstance();
      frmt.setMinimumFractionDigits(2);
      return frmt.format( this.price );
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   public String getMinPrice() {
      NumberFormat frmt = DecimalFormat.getInstance();
      frmt.setMinimumFractionDigits(2);
      return frmt.format( this.minPrice );
   }

   public String getActive() { return this.active; }

   @Override
   public boolean equals(Object obj) {
      if( obj instanceof Book == false )
         return false;
      
      if( obj == this )
         return true;
      
      if( ((Book) obj).id == this.id )
         return true;
      else
         return false;
   }

   @Override
   public int hashCode()
   {
      return String.valueOf(this.id).hashCode();
   }

   @Override
   public String toString() {
      StringBuilder authors = new StringBuilder();
      int i=0;
      for(i=0; i < this.users.size()-1; i++)
      {
         authors.append( this.users.get(i) + ", ");
      }
      authors.append( this.users.get(i) );
      
      return authors.toString() + " : " + this.title + authors.toString();
   }
   
}
