package user;

import main.ModelFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

   private static final long serialVersionUID = -5651583618904943407L;

   private ModelFacade facade;

   private int id;
   private String firstname = null;
   private String lastname = null;
   private String email = null;
   private String zipcode = null;
   private String location = null;
   private String birth = null;
   private String address = null;

   private byte[] pwdHash = new byte[0];
   private byte[] salt = new byte[0];
   
   private List<Book> books = new ArrayList<Book>();
   
   public User(int id, String firstname, String lastname, String birth, String email, String address, String zipcode, String location) {
      super();
      this.id = id;
      this.firstname = firstname;
      this.lastname = lastname;
      this.email = email;
      this.address = address;
      this.zipcode = zipcode;
      this.location = location;
      this.birth = birth;
      this.books = new ArrayList<Book>();
   }
   
   public boolean addBuch(Book book)
   {
      return this.books.add(book);
   }

   public boolean removeBookFromUser(Book book) {
      return this.books.remove(book);
   }
   
   public List<Book> getBooks() {
      List<Book> result = new ArrayList<Book>();
      result.addAll( this.books);
      return result;
   }
   
   public int getId()
   {
      return this.id;
   }
   
   public String getFirstname()
   {
      return this.firstname;
   }
   
   public String getLastname()
   {
      return this.lastname;
   }

   public String getEmail()
   {
      return this.email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getBirth()
   {
      return this.birth;
   }

   public void setBirth(String birth)
   {
      this.birth = birth;
   }

   public String getAddress()
   {
      return this.address;
   }

   public void setAddress(String address)
   {
      this.address = address;
   }

   public String getZipcode()
   {
      return this.zipcode;
   }

   public void setZipcode(String zipcode)
   {
      this.zipcode = zipcode;
   }

   public String getLocation()
   {
      return this.location;
   }

   public void setLocation(String location)
   {
      this.location = location;
   }

   public byte[] getPwdHash()
   {
      return this.pwdHash;
   }

   public void setPwdHash(byte[] pwdHash)
   {
      this.pwdHash = pwdHash;
   }

   public byte[] getSalt()
   {
      return this.salt;
   }

   public void setSalt(byte[] salt)
   {
      this.salt = salt;
   }

   public void addBook(Book book)
   {
      this.books.add( book );
   }

   public List<Book> getAddedBook()
   {
      return this.books;
   }

   @Override
   public boolean equals(Object obj) {
      if( obj instanceof User == false )
         return false;
      
      if( obj == this )
         return true;
      
      if( ((User) obj).id == this.id )
         return true;
      else
         return false;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + id;
      return result;
   }

   @Override
   public String toString()
   {  
      return this.firstname + " " + this.lastname;
   }
}
