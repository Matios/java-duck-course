package com.matiosdinegdia.recommendationsystemclass2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/*Create a new class named MovieRunnerAverage. In this class, create a void method named 
 * printAverageRatings that has no parameters. This method should:

*/
public class MovieRunnerAverage {
	
	/*
	 *  Create a SecondRatings object and use the CSV filenames of movie information and 
	 *  ratings information from the first assignment when calling the constructor.
	 *  Print the number of movies and number of raters from the two files by calling the 
	 *  appropriate methods in the SecondRatings class. Test your program to make sure it is
	 *  reading in all the data from the two files. For example, if you run your program on 
	 *  the files ratings_short.csv and ratedmovies_short.csv, you should see 5 raters and 5
	 *  movies.
	 */
	/*
	 * In the MovieRunnerAverage class in the printAverageRatings method, add code to 
	 * print a list of movies and their average ratings, for all those movies that have
	 * at least a specified number of ratings, sorted by averages. Specifically, this 
	 * method should print the list of movies, one movie per line (print its rating 
	 * first, followed by its title) in sorted order by ratings, lowest rating to 
	 * highest rating. For example, if printAverageRatings is called on the files 
	 * ratings_short.csv and ratedmovies_short.csv with the argument 3, then the 
	 * output will display two movies:
	 */
	public void printAverageRatings() {
		
		SecondRatings sr = new SecondRatings();
		//print the number of movies
        System.out.println("\nNumber of movies in file: " + sr.getMovieSize());
        //print the number of raters
        System.out.println("Number of raters in file: " + sr.getRaterSize());
        int minimalRaters = 1;
        ArrayList<Rating> ratings = sr.getAverageRatings(minimalRaters);
        System.out.println(ratings);
        Collections.sort(ratings);
        System.out.println(ratings);
        for (Rating rating : ratings) {
			double currValue = rating.getValue();
			if (currValue != 0) {
				String currMovieID = rating.getItem();
				System.out.println(currValue + " " + sr.getTitel(currMovieID));
			} 
			
		}
	}
	
	/*
	 *  In the MovieRunnerAverage class, write the void method getAverageRatingOneMovie, 
	 *  which has no parameters. This method should first create a SecondRatings object, 
	 *  reading in data from the movie and ratings data files. Then this method should 
	 *  print out the average ratings for a specific movie title, such as the movie 
	 *  “The Godfather”. If the moviefile is set to the file named ratedmovies_short.csv, 
	 *  and the ratingsfile is set to the file ratings_short.csv, then the average for
	 *  the movie “The Godfather” would be 9.0.
	 */
	public void getAveageRatingOneMovie() {
		SecondRatings sr = new SecondRatings();
		//String movieFileName = "ratedmoviesfull.csv";
		//String ratingFileName = "ratings.csv";
		
		int rating = 0;
		ArrayList<Rating> ratings = sr.getAverageRatings(rating);
		String movieTitel = "Vacation";
		String movieID = sr.getID(movieTitel);
		
		double value = -1;
		for (Rating currRating : ratings) {
			
			if (currRating.getItem().equals(movieID)) {
				value = currRating.getValue();
				System.out.println(currRating.getValue());
			}
		}
		System.out.println("The average rating for "+ movieTitel + " is "+value+ " .");
	}
}
