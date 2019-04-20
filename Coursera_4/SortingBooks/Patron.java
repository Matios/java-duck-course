
/**
 * Write a description of Patron here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class Patron implements Comparable<Patron> {
    private String name;
    public ArrayList<Book> book;
    
    public Patron(String n, ArrayList<Book> b){
        name = n;
        book = b;
    }
    public String getName(){
        return name;
    }
    public ArrayList<Book> getBook(){
        return book;
    }
    public int compareTo(Patron otherPatron){
        int result = 0;
        ArrayList<Book> otherBooks = otherPatron.getBook();
        for(int i = 0 ; i < book.size() ; i++){
            result = book.get(i).compareTo(otherBooks.get(i));
        }
        return result;
    }
}
