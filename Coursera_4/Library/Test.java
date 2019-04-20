
/**
 * Write a description of Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Test {
    
    public void testClassProperties(){
        String a = "Ryan";
        int p = 100;
        boolean i = false;
        String s="good";
      Book myBook = new Book(a,p,i,s); 
      System.out.println("My book, "+ myBook.getAuthor()+", has "+ myBook.getPageCount()+" pages "
            + "is checked out "+ myBook.isCheckedOut() + " how was it "+ myBook.summary());
      System.out.println(myBook.getAuthor().equals(a));
    }
    public void testPatron(){
        String first = "";
        String last = "";
        //ArrayList<Book> b =""; 
    
    }
    
}
