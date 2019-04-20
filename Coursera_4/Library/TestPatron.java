
/**
 * Write a description of TestPatron here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TestPatron {
    public void testPatron(){
        String first = "";
        String last = "";
        ArrayList<Book> bs = new ArrayList<Book>();
        //bs.add(3, _element_)
        String a = "matios";
        int p = 100;
        boolean i = false;
        String s = "best book";
        Book bestNovelInHistory = new Book(a,p,i,s);
        System.out.println(bestNovelInHistory.getAuthor()+" there");
    }
}