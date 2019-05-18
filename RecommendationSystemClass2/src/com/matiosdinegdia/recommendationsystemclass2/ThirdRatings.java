package com.matiosdinegdia.recommendationsystemclass2;

import java.util.ArrayList;

public class ThirdRatings {
	
/*
 * Create a new class named ThirdRatings. Copy your code from SecondRatings into this class.
 * Movies will now be stored in the MovieDatabase instead of in the instance variable myMovies, 
 * so you will want to remove the private variable myMovies. The constructor will no longer have
 * a moviefile parameter—movies will be stored in the MovieDatabase class.
   ThirdRatings has only one private variable named myRaters to store an ArrayList of Raters.
   The default constructor should look like this:
 */
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this( "data/ratings_short.csv");
         
    }
    
   /*
    * A second constructor should have only one String parameter named ratingsfile. 
    * This constructor should call the method loadRaters from the FirstRatings 
    * class to fill the myRaters ArrayList.
    */
    public ThirdRatings(String ratingsfile) {
    	
    	FirstRatings first = new FirstRatings();
    	myRaters = first.loadRater(ratingsfile);
    }
    
    /*
     * In the SecondRatings class, write a public method named getRaterSize, which returns the
     *  number of raters that were read in and stored in the ArrayList of type Rater.
     */
    public int getRaterSize(){
    	
    	return myRaters.size();
    }
    
    /*
     You will need to modify getAverageRatings. Note that myMovies no longer exists. 
     Instead, you’ll need to get all the movies from the MovieDatabase class and 
     store them in an ArrayList of movie IDs. Thus, you will need to modify 
     getAverageRatings to call MovieDatabase with a filter, and in this case you can 
     use the TrueFilter to get every movie.
     */
    private double getAverageById(String movieId, int minimalRater) {
    	double sum = 0.0;
    	int count = 0;
    	
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
    
    public void testAverage() {
    	System.out.println("Average " + getAverageById("0068646",4));
    	System.out.println(getAverageRatings(4));
    }
    
    /*
     * You will need to modify getAverageRatings. Note that myMovies no longer exists. 
     * Instead, you’ll need to get all the movies from the MovieDatabase class and store 
     * them in an ArrayList of movie IDs. Thus, you will need to modify getAverageRatings 
     * to call MovieDatabase with a filter, and in this case you can use the TrueFilter 
     * to get every movie.
     */
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
    	
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    	ArrayList<Rating> allAverageRating = new ArrayList<Rating>();
    	
    	for (String currMovieId : movies) {
    		
			double averageRating = getAverageById(currMovieId, minimalRaters);
			allAverageRating.add(new Rating(currMovieId, averageRating));
		}
    	return allAverageRating;	
    }
    
    /* In the ThirdRatings class, write a public helper method named getAverageRatingsByFilter
     *  that has two parameters, an int named minimalRaters for the minimum number 
     *  of ratings a movie must have and a Filter named filterCriteria. This method 
     *  should create and return an ArrayList of type Rating of all the movies that 
     *  have at least minimalRaters ratings and satisfies the filter criteria. This 
     *  method will need to create the ArrayList of type String of movie IDs from the
     *   MovieDatabase using the filterBy method before calculating those averages.
     */
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
    	ArrayList<String> movieIds = MovieDatabase.filterBy(filterCriteria);
    	ArrayList<Rating> averageRatings = new ArrayList<Rating>();
    	for (String s : movieIds) {
			double ratingValue = getAverageById(s, minimalRaters);
			averageRatings.add(new Rating(s, ratingValue));	
		}
    	return averageRatings;
    }
}
