
/**
 * Write a description of Book here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Book implements LibraryMaterial{

    private String author;
    private boolean isCheckedOut;
    private int pageCount;
    private String summary;
    
    public Book(String a, int p ,boolean i, String s){
        author = a;
        pageCount = p;
        isCheckedOut = i;
        summary = s ;
    }
    public String getAuthor(){
        return author;
    }
    public int getPageCount(){
        return pageCount;
    }
    public boolean isCheckedOut(){
        return isCheckedOut;
    }
    public String summary(){
        return summary;
    }
    public void setIsCheckedOut(boolean i){
        isCheckedOut = i;
    }
    
}
