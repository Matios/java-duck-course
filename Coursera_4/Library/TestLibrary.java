
/**
 * Write a description of testLibrary here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TestLibrary {
    public void testCheckInAndOut(){
    
        String a = "Ryan";
        int c = 100;
        boolean i = false;
        String s = "Best book in the history of the world";
        
        String f = "Marcos";
        String l = "Minond";
        
        LibraryMaterial b = new Book(a, c, i, s);
        LibraryMaterial m = new Movie(false);
        
        ArrayList<LibraryMaterial> patronBooks = new ArrayList<LibraryMaterial>();
        ArrayList<LibraryMaterial> libraryBooks = new ArrayList<LibraryMaterial>();
        libraryBooks.add(b);
        libraryBooks.add(m);
        
        Patron patron = new Patron(f, l, patronBooks);
        
        ArrayList<Patron> patrons = new ArrayList<Patron>();
        patrons.add(patron);
        
        Library library = new Library(libraryBooks, patrons);
        
        System.out.println("num library books " + library.getBook().size());
        System.out.println("num patron books " + patron.getBook().size());
        library.checkOut(b, patron);
        library.checkOut(m, patron);
        
        System.out.println("num library books " + library.getBook().size());
        System.out.println("num patron books " + patron.getBook().size());
        
        library.checkIn(b, patron);
        library.checkIn(m, patron);
        
        System.out.println("num library books " + library.getBook().size());
        System.out.println("num patron books " + patron.getBook().size());
        
    }
}
