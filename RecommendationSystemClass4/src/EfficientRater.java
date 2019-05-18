
import java.util.*;

/*
 * Create a new class named EfficientRater, and copy the PlainRater 
 * class into this class. You will make several changes to this class, including:
 * Change the ArrayList of type Rating private variable to a HashMap<String,Rating>. 
 * The key in the HashMap is a movie ID, and its value is a rating associated with this movie.
 */
public class EfficientRater implements Rater {
	
	    private String myID;
	    private HashMap<String, Rating> myRatings;

	    public EfficientRater(String id) {
	        myID = id;
	        myRatings = new HashMap<String, Rating>();
	    }
	    
/*
 *  You will need to change addRating to instead add a new Rating to the
 *  HashMap with the value associated with the movie ID String item as the 
 *  key in the HashMap.
 */
	    public void addRating(String item, double rating) {
	        myRatings.put(item, new Rating(item,rating));
	    }

	    public boolean hasRating(String item) {
	    	
	       return myRatings.containsKey(item);
	    }
	    
	    public int numRating() {
	    	return 0;
	    }
	    
	    public String getID() {
	        return myID;
	    }

	    public double getRating(String item) {
	    	
	       for(String ratingsKey : myRatings.keySet()) {
	    	   if (ratingsKey.equals(item)) {
				return myRatings.get(ratingsKey).getValue();			}
	       }
	        
	        return -1;
	    }

	    public int numRatings() {
	        return myRatings.size();
	    }

	    public ArrayList<String> getItemsRated() {
	        ArrayList<String> list = new ArrayList<String>();
	        for(String s : myRatings.keySet()){
	            list.add(s);
	        }
	        
	        return list;
	    }
	
}
