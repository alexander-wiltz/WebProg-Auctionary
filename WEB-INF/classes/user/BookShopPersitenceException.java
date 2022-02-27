package user;

public class BookShopPersitenceException extends Exception
{
   private static final long serialVersionUID = -2410094803461586984L;

   public BookShopPersitenceException(String errorMsg)
   {
      super(errorMsg);
   }
   
   public String getErrorMessage()
   {
      return super.getMessage();
   }
}
