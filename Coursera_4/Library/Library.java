
/**
 * Write a description of library here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class Library {
    private ArrayList<LibraryMaterial> books;
    private ArrayList<Patron> patron;
    
    public Library(ArrayList<LibraryMaterial> bs, ArrayList<Patron> ps){
        books = bs;
        patron = ps;
    }
    
    public ArrayList<LibraryMaterial> getBook(){
         return books;
    }
    public ArrayList<Patron> getPatron(){
         return patron;
    }
    
    public void checkOut(LibraryMaterial b, Patron p){
       //remove book from library
       books.remove(b);
       b.setIsCheckedOut(true);
       ArrayList<LibraryMaterial> patronBook = p.getBook();
       patronBook.remove(b);
       b.setIsCheckedOut(false);
       patronBook.add(b);
    }
    
    public void checkIn(LibraryMaterial b, Patron p){
        ArrayList<LibraryMaterial> patron = p.getBook();
        patron.remove(b);
        b.setIsCheckedOut(false);
        books.add(b);
        
    }
}
