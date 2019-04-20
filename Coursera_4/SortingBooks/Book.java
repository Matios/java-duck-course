
/**
 * Write a description of Book here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Book implements Comparable<Book>{
    private int pageCount;
    private String author;
    private double rating;
    
    public Book(String a, int count, double r){
        author = a;
        pageCount = count;
        rating = r;
    }
    
    int getPageCount(){
        return pageCount;
    }
    String getAuthor(){
        return author;
    }
    double getRating(){
        return rating;
    }
    
    public int compareTo(Book otherBook){
        Book otherRating = otherBook;
        return 0;//.compareTo(otherBook);
    }
    
}
