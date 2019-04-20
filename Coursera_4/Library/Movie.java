
/**
 * Write a description of Movie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Movie implements LibraryMaterial {
    private boolean isCheckedOut;
    
    public Movie(boolean i){
        isCheckedOut = i;
    }
    public void setIsCheckedOut(boolean i){
        isCheckedOut = i;
    }
}
