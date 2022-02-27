package main;

import user.Book;
import user.BookShopPersitenceException;
import user.User;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
/*
 * Die Fassade stellt verschiedene Zugriffsmethoden auf die Geschäftsobjekte bereit.
 * Weiter initialisiert die Fassade auch einen (transienten) Datenbestand, sodass
 * die Fassade sofort in die Web-Anwendung eingebaut werden kann.
 * 
 * Die Fassade ist als Singleton implementiert!
 *
 * created by C. Endler, modified by A.Wiltz
 */
public class ModelFacade {

   private static class InstanceHolder {
      private static ModelFacade instance = new ModelFacade();
   }
    
   public static ModelFacade getInstance()
   {
      return InstanceHolder.instance;
   }
   
   private List<User> userContainer = new ArrayList<User>();
   private List<Book> bookContainer = new ArrayList<Book>();
   
   private ModelFacade() {
      super();
      this.initModel();
   }
   
   public Collection<Book> findBooksfromUser(String name) {
      Set<Book> result = new HashSet<Book>();
      String[] names = name.split("\\*");
      
      for(User a : this.userContainer) {
         boolean found = true;
         for( String searchString : names ) {
            if(a.getLastname().toLowerCase().contains(searchString.toLowerCase())) {
               found &= true;
            }
            else {
               found &= false;
            }
         }    
         if( found )
            result.addAll(a.getBooks());
      }
      
      return result;
   }

   public Collection<Book> findUsersEditableBooks(String name) {
      Collection<Book> editableBooks = new ArrayList<>();
      for(Book b : this.findBooksfromUser(name)) {
         if(!b.isSold()) {
            editableBooks.add(b);
         }
      }
      return editableBooks;
   }

   public void setBookToContainer(Book book) {
      this.bookContainer.add(book);
   }

   public void removeBook(Book book) {
      List<Book> liste = this.bookContainer;
      liste.remove(book);
      this.bookContainer = liste;
   }

   public void setToActive(Book book) {
      book.setActive();
   }

   public void setAsSold(Book book) {
      book.setSold();
   }

   public void setUserToContainer(User user) {
      this.userContainer.add(user);
   }
   
   public Collection<Book> getAllBooks() {
      return new ArrayList<Book>( this.bookContainer);
   }
   
   public Collection<User> getAllUsers() {
      return new ArrayList<User>( this.userContainer);
   }

   public User findUser(String email) throws BookShopPersitenceException {
      Collection<User> userlist = getAllUsers();
      User user = null;

      for (User u : userlist) {
         if(Objects.equals(u.getEmail(), email)) {
            user = u;
            break;
         }
      }
      if (user == null) {
         throw new BookShopPersitenceException("Fehler beim anmelden. " + email + " nicht gefunden!");
      }

      return user;
   }

   public boolean checkLogin(String username, String passwd) throws BookShopPersitenceException {
      User b = this.findUser(username);
      byte[] pwd = b.getPwdHash();
      byte[] salt = b.getSalt();
      byte[] pwdToTest = generatePassword(passwd, salt);
      boolean compare = true;

      if (pwd.length != pwdToTest.length) {
         return false;
      }

      for (int i = 0; i < pwd.length; i++) {
         compare &= (pwd[i] == pwdToTest[i]);
      }

      return compare;
   }

   public static byte[] generatePassword(String password, byte[] salt) {
      try {
         SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
         KeySpec ks = new PBEKeySpec(password.toCharArray(), salt, 10000, 160);
         SecretKey s = f.generateSecret(ks);
         Key k = new SecretKeySpec(s.getEncoded(), "AES");
         return k.getEncoded();
      } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
         e.printStackTrace();
         return new byte[0];
      }
   }

   public static byte[] generateSalt() {
      try {
         SecureRandom random;
         random = SecureRandom.getInstance("SHA1PRNG");
         byte[] salt = new byte[8];
         random.nextBytes(salt);
         return salt;
      } catch (NoSuchAlgorithmException e) {
         return new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
      }

   }

   // returns all book which are marked as sold from user
   public Collection<Book> getSoldBooks(String user) {
      List<Book> soldBookContainer = new ArrayList<Book>();
      Collection<Book> booklist = findBooksfromUser(user);
      for (Book b : booklist) {
         if(b.isSold()) {
            soldBookContainer.add(b);
         }
      }
      return soldBookContainer;
   }

   public String getSummary(String user) {
      Collection<Book> booklist = findBooksfromUser(user);
      double sum = 0;

      for (Book b : booklist) {
         if(b.isSold()) {
            sum += Double.parseDouble(b.getPrice().replace(",","."));
         }
      }

      NumberFormat frmt = DecimalFormat.getInstance();
      frmt.setMinimumFractionDigits(2);
      return frmt.format( sum );
   }
   
   private void initModel() {
      User potter = new User(1, "Harry", "Potter","1980-07-31" ,"harry.potter@hogwards.co.uk","Ligusterweg 4", "12345", "Little Whinging");
      this.userContainer.add(potter);
      byte[] salt1 = generateSalt();
      byte[] pwd1 = generatePassword("Zitronensorbet", salt1);
      potter.setSalt(salt1);
      potter.setPwdHash(pwd1);
      User beutlin = new User(2,"Frodo", "Beutlin","4094-12-01" , "frodo.beutlin@hobbiton.com","", "12369", "Hobbington");
      this.userContainer.add(beutlin);
      byte[] salt2 = generateSalt();
      byte[] pwd2 = generatePassword("gandalf", salt2);
      beutlin.setSalt(salt2);
      beutlin.setPwdHash(pwd2);

      Book eSA = new Book(1,"3446412158","Effektive Software-Architekturen","Hanser",1,39.95,0.0 , "", false);
      this.bookContainer.add(eSA);
      eSA.addUser(potter);
      potter.addBuch( eSA );

      Book mSA = new Book(2,"3898642925","Moderne Software-Architekturen","dpunkt.verlag", 3 ,39.00 ,0, "", false);
      this.bookContainer.add(mSA);
      mSA.addUser(potter);
      potter.addBuch(mSA);

      Book aSD = new Book(3,"1292025948","Agile Software Development","Prentice Hall", 2,67.97 ,90, "", true);
      this.bookContainer.add(aSD);
      aSD.addUser(potter);
      potter.addBuch(aSD);

      Book cPJ = new Book(4,"0201310090" ,"Concurrent Programming in Java","Addison Wesely", 1,47.95,0, "" , false);
      this.bookContainer.add(cPJ);
      cPJ.addUser(potter);
      potter.addBuch(cPJ);

      Book sPP = new Book(5, "0124159931","Structured Parallel Programming","Morgan Kaufmann", 1, 42.95 , 48,"", true);
      this.bookContainer.add(sPP);
      sPP.addUser(potter);
      potter.addBuch(sPP);

      Book xeonPhi = new Book(6, "0124104142","Intel Xeon Phi Coprocerssor High-Performance Programming","Morgan Kaufmann", 3,42.95, 0,"" , false);
      this.bookContainer.add(xeonPhi);
      xeonPhi.addUser(beutlin);
      beutlin.addBuch(xeonPhi);

      Book mOS = new Book(7, "9332550018","Modern Operating Systems","Prentice Hall",2, 76.22 , 0,"", false);
      this.bookContainer.add(mOS);
      mOS.addUser(beutlin);
      beutlin.addBuch(mOS);
      
      Book cN = new Book(8,"1292374063", "Computer Networks","Prentice Hall", 1, 96.20, 0,"", false );
      this.bookContainer.add(cN);
      cN.addUser(beutlin);
      beutlin.addBuch(cN);

      Book cC = new Book(9,"0132350882","Clean Code","Prentice Hall",2, 33.95 ,0, "", false);
      this.bookContainer.add(cC);
      cC.addUser(potter);
      potter.addBuch(cC);
      
      Book cC2 = new Book(10, "0137081073","The Clean Coder","Prentice Hall", 3, 39.37,0, "aktiv", false );
      this.bookContainer.add(cC2);
      cC2.addUser(beutlin);
      beutlin.addBuch(cC2);
   }
}
