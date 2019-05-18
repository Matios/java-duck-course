

import java.util.ArrayList;
import java.util.Collections;

/*
 * Create a new class named FourthRatings. Copy over the following methods from the 
 * class ThirdRatings and get FourthRatings to compile. Do not copy over any of the 
 * other methods. You should not copy, nor should you have any instance variables in 
 * FourthRatings—you'll use RaterDatabase and MovieDatabase static methods in place of
 *  instance variables—so where you have code with myRaters, you need to replace the 
 *  code with calls to methods in the RaterDatabase class. The methods to copy into 
 *  FourthRatings from ThirdRatings are below (you'll need to modify these after 
 *  copying): getAverageByID, getAverageRatings, and getAverageRatingsByFilter.
 */
public class FourthRatings {
	
	public FourthRatings() {
		this("ratings.csv");
	}
	
   public FourthRatings(String ratingfile) {
	  RaterDatabase.initialize(ratingfile);
   }
  
	private double getAverageById(String movieId, int minimalRater) {
    	double sum = 0.0;
    	int count = 0;
    	
    	ArrayList<Rater> myRaters = RaterDatabase.getRaters();
    	
    	for (Rater rater : myRaters) {
    		for (String i : rater.getItemsRated()) {
				if(i.equals(movieId)) {
					count++;
					sum += rater.getRating(movieId); 
				}
			 //  System.out.println(count + " " +i+" "+rater.getRating(i));  
			}	
		}
    	
    	if (count < minimalRater) {
			return 0.0;
		}
    	//System.out.println("Number of raters " + count + " Sum of their ratings "+ sum);
    	return sum/count;
    }
	
	 public ArrayList<Rating> getAverageRatings(int minimalRaters){
	    	
	    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
	    	ArrayList<Rating> allAverageRating = new ArrayList<Rating>();
	    	
	    	for (String currMovieId : movies) {
	    		
				double averageRating = getAverageById(currMovieId, minimalRaters);
				allAverageRating.add(new Rating(currMovieId, averageRating));
			}
	    	return allAverageRating;	
	    }
	 
	 public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
	    	
		    ArrayList<String> movieIds = MovieDatabase.filterBy(filterCriteria);
	    	ArrayList<Rating> averageRatings = new ArrayList<Rating>();
	    	for (String s : movieIds) {
				double ratingValue = getAverageById(s, minimalRaters);
				averageRatings.add(new Rating(s, ratingValue));	
			}
	    	return averageRatings;
	    }
	 
	 /*
	  * Write the private helper method named dotProduct, which has two parameters, a Rater
	  * named me and a Rater named r. This method should first translate a rating from the
	  * scale 0 to 10 to the scale -5 to 5 and return the dot product of the ratings of 
	  * movies that they both rated. This method will be called by getSimilarities.
	  */
	private double dotProduct(Rater me, Rater r) {
	
		double result = 0.0;
		ArrayList<String> myItems = me.getItemsRated();
		ArrayList<String> otherItems = r.getItemsRated();
		for (String s : myItems) {
			if (otherItems.contains(s)) {
				double value1 = me.getRating(s) - 5;
				double value2 = r.getRating(s)- 5;
				result += value1*value2;
			}	
		}
		return result;
	}
	
	/*
	 * Write the private method named getSimilarities, which has one String parameter 
	 * named id—this method computes a similarity rating for each rater in the RaterDatabase 
	 * (except the rater with the ID given by the parameter) to see how similar they 
	 * are to the Rater whose ID is the parameter to getSimilarities. This method 
	 * returns an ArrayList of type Rating sorted by ratings from highest to lowest 
	 * rating with the highest rating first and only including those raters who have 
	 * a positive similarity rating since those with negative values are not similar 
	 * in any way. Note that in each Rating object the item field is a rater’s ID, 
	 * and the value field is the dot product comparison between that rater and the 
	 * rater whose ID is the parameter to getSimilarities. Be sure not to use the 
	 * dotProduct method with parameter id and itself!
	 */
	//compute a similarity for each rater in the raterDatabase
	//except the rater with id given by the parameter
	//if(!r.getId().equals(id))
	//to see how similar they are to the rater whose ID is the parameter to getSimilarity
	//r.getRating(id)
	//highest to lowest 
	//return only positive
	//in each rating object the filed is a rater's id
	//value field is the dot product
	private ArrayList<Rating> getSimilarities(String id){
		
		ArrayList<Rating> list = new ArrayList<>();
		//Rater me = RaterDatabase.getRater(id);
		for (Rater r : RaterDatabase.getRaters()) {
			//add dot_product(r,me) to list if r != me
			if (!r.getID().equals(id)) {
				double results= dotProduct(RaterDatabase.getRater(id), r);
				if(results > 0.0) {
					list.add(new Rating(r.getID(), results));
				}
			}
		}
		Collections.sort(list, Collections.reverseOrder());
		return list;
	}
	
	/*
	 *  Write the public method named getSimilarRatings, which has three parameters: a String 
	 *  named id representing a rater ID, an integer named numSimilarRaters, and an integer
	 *   named minimalRaters. This method should return an ArrayList of type Rating, of movies 
	 *   and their weighted average ratings using only the top numSimilarRaters with positive 
	 *   ratings and including only those movies that have at least minimalRaters ratings from 
	 *   those most similar raters (not just minimalRaters ratings overall). For example, 
	 *   if minimalRaters is 3 and a movie has 4 ratings but only 2 of those ratings were made 
	 *   by raters in the top numSimilarRaters, that movie should not be included. These Rating 
	 *   objects should be returned in sorted order by weighted average rating from largest to 
	 *   smallest ratings. This method is very much like the getAverageRatings method you have 
	 *   written previously. In particular this method should:
	 */
	public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int  minimalRaters){
		//return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
		 ArrayList<Rating> movieWeightedAverage = new ArrayList<Rating>();
	        ArrayList<Rating> similarityList = getSimilarities(id);
	        ArrayList<String> movieIDWithMinimalRaters = new ArrayList<String>();
	        while (true) {
	            if (similarityList.size() > numSimilarRaters)
	                similarityList.remove(similarityList.size() - 1);
	            else
	                break;
	        }
	        for (String movieID : MovieDatabase.filterBy(new TrueFilter())) {
	            int numMinimalRaters = 0;
	            for (Rating rating : similarityList) {
	                if (RaterDatabase.getRater(rating.getItem()).hasRating(movieID)) {
	                    numMinimalRaters++;
	                }
	            }
	            if (numMinimalRaters >= minimalRaters)
	                movieIDWithMinimalRaters.add(movieID);
	        }
	       // System.out.println(similarityList.size());
	       // System.out.println(movieIDWithMinimalRaters.size());

	        for (String movieID : movieIDWithMinimalRaters) {
	            double sumOfRating = 0;
	            double numberOfRaters = 0;
	            for (Rating rating : similarityList) {
	                if (RaterDatabase.getRater(rating.getItem()).hasRating(movieID)) {
	                    sumOfRating += (rating.getValue() * RaterDatabase.getRater(rating.getItem()).getRating(movieID));
	                    numberOfRaters++;
	                }
	            }
	            if (numberOfRaters > 0)
	                movieWeightedAverage.add(new Rating(movieID, (sumOfRating / numberOfRaters)));
	        }
	        Collections.sort(movieWeightedAverage,Collections.reverseOrder());
	        return movieWeightedAverage;
	
	}
	

	/*
	 *  Write the public method getSimilarRatingsByFilter, which is similar to the getSimilarRatings
	 *   method but has one additional Filter parameter named filterCriteria and uses that filter to
	 *    access and rate only those movies that match the filter criteria.
	 */
	public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
		
//	//create an empty arraylist<Rater> result
//	 ArrayList<Rating> result = new ArrayList<Rating>();
//	//create an arrayList<ratting> getSimilarities with id
//	 ArrayList<Rating> getSimilars = getSimilarities(id);
//	//creat arrayList<String> movieIds = movieDatabase or with filter
//	 ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCrateria);
//	//                                 = movieDatabase.filterBy with "filter"
//	//iterate  through "movieId"
//	 for (String s : movieIDs) {
//		// a. double "ratingValue" equals to ...
//		 double ratingValue = 0.0;
//		//b. int count = ..
//		 int count = 0;
//		// c. iterate "numSimilarity" times
//		 for(int i = 0; i < numSimilarRaters; i++) {
//		      //  i. get Rating "rating" from similarities
//			 Rating rt = getSimilars.get(i);
//		      //  ii. get Rater "rater from RaterDatabase with id from "rating"
//			 Rater ra = RaterDatabase.getRater(id);
//		      // iii. if "rater has the rating from "movie id"
//			 if (ra.hasRating(s)) {
//				// a, multiplay "rating" value with the rating of " rater" and add to rating value
//					// ratingValue r = rating.getValue() * rater.getRating(movieId)
//				 ratingValue += rt.getValue() * ra.getRating(s);
//					// b, increament count
//				 count++;
//			}
//			//iv if SimilarRatingCount is grater or equal to minimalraters 
//		}
//		 if (count >= minimalRaters) {
//			// a, add to result
//			 result.add(new Rating(s, ratingValue/count));
//			}
//		//5. Sort result
//		 Collections.sort(result, Collections.reverseOrder());
//	 }
//		 return result;
		
		ArrayList<Rating> movieWeightedAverage = new ArrayList<Rating>();
        ArrayList<Rating> similarityList = getSimilarities(id);
        ArrayList<String> movieIDWithMinimalRaters = new ArrayList<String>();
        while (true) {
            if (similarityList.size() > numSimilarRaters)
                similarityList.remove(similarityList.size() - 1);
            else
                break;
        }
        for (String movieID : MovieDatabase.filterBy(filterCriteria)) {
            int numMinimalRaters = 0;
            for (Rating rating : similarityList) {
                if (RaterDatabase.getRater(rating.getItem()).hasRating(movieID)) {
                    numMinimalRaters++;
                }
            }
            if (numMinimalRaters >= minimalRaters)
                movieIDWithMinimalRaters.add(movieID);
        }
      //  System.out.println(similarityList.size());
       // System.out.println(movieIDWithMinimalRaters.size());

        for (String movieID : movieIDWithMinimalRaters) {
            double sumOfRating = 0;
            double numberOfRaters = 0;
            for (Rating rating : similarityList) {
                if (RaterDatabase.getRater(rating.getItem()).hasRating(movieID)) {
                    sumOfRating += (rating.getValue() * RaterDatabase.getRater(rating.getItem()).getRating(movieID));
                    numberOfRaters++;
                }
            }
            if (numberOfRaters > 0)
                movieWeightedAverage.add(new Rating(movieID, (sumOfRating / numberOfRaters)));
        }
        Collections.sort(movieWeightedAverage,Collections.reverseOrder());
        return movieWeightedAverage;
		
	}
	
	
}
