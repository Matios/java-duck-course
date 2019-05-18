

import java.util.ArrayList;
import java.util.Collections;

/*
 * Create a new class named MovieRunnerWithFilters that you will use
 * to find the average rating of movies using different filters. 
 * Copy the printAverageRatings method from the MovieRunnerAverage
 * class into this class. You will make several changes to this method:
 */
public class MovieRunnerWithFilters {
	
	private String ratingFile;
	private ThirdRatings tr;
	private String movieFileName = "ratedmoviesfull.csv";
	
	/*
	 * Instead of creating a SecondRatings object, you will create a 
	 * ThirdRatings object. Note that this only has one parameter, the 
	 * name of a file with ratings data.
	 */
	public MovieRunnerWithFilters() {
		ratingFile = "data/ratings.csv";
		tr = new ThirdRatings(ratingFile);
	}
	
	//You’ll call the MovieDatabase initialize method with the moviefile to set 
	//up the movie database.
	//print the number of movies
	// Print the number of raters after creating a ThirdsRating object.
	public void movieInitializer() {
		MovieDatabase.initialize(movieFileName);
		System.out.println("Read data for "+ tr.getRaterSize() + " raters");
		System.out.println("Read data for "+ MovieDatabase.size()+" movies");
	}

public void printAverageRatings() {
	
		movieInitializer();
		
        //You will call getAverageRatings with a minimal number of raters to return
        //an ArrayList of type Rating.
        int minimalRaters = 35;
        ArrayList<Rating> ratings = tr.getAverageRatings(minimalRaters);
        
        //Print out how many movies with ratings are returned, then sort them, 
        //and print out the rating and title of each movie.
        int num = 0;
        System.out.println("Movies atleast  "+ minimalRaters+ " raters");
        Collections.sort(ratings);
        
        for (Rating rating : ratings) {
			double currValue = rating.getValue();
			if (currValue != 0) {
				num++;
				String currMovieID = rating.getItem();
				System.out.println(currValue + " " + MovieDatabase.getTitle(currMovieID));
			} 
			
		}
        System.out.println("There are "+ num+ " movies have at least "+ minimalRaters+" ratings");
	}
	
/*
 *  In the MovieRunnerWithFilters class, create a void method named printAverageRatings
 *  ByYear that should be similar to printAverageRatings, but should also create a 
 *  YearAfterFilter and call getAverageRatingsByFilter to get an ArrayList of type 
 *  Rating of all the movies that have a specified number of minimal ratings and came
 *  out in a specified year or later. Print the number of movies found, and for each 
 *  movie found, print its rating, its year, and its title. For example, if you run
 *  the printAverageRatingsByYear method on the files ratings_short.csv and ratedmovies
 *  short.csv with a minimal rater of 1 and the year 2000, you should see
 */
public void printAverageRatingsByYear() {
	
	movieInitializer();
	
	int minimalRater = 20;
	Filter filterYear = new YearAfterFilter(2000);
	ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRater, filterYear);
	Collections.sort(ratings);
	int num = 0;
	 
	 for (Rating rating : ratings) {
		double currValue = rating.getValue();
		if(currValue != 0.0) {
			num++;
			String currMovieId = rating.getItem();
			System.out.println(currValue + "  "+MovieDatabase.getYear(currMovieId)+"  "+ MovieDatabase.getTitle(currMovieId));
		}
	}
	System.out.println("There are "+ num + " movie has at least "+ minimalRater+" ratings and satisfy the filter");
}
/*
 *  In the MovieRunnerWithFilters class, create a void method named printAverageRatingsByGenre that should create a 
 *  GenreFilter and call getAverageRatingsByFilter to get an ArrayList of type Rating of all the movies that have 
 *  a specified number of minimal ratings and include a specified genre. Print the number of movies found, and for 
 *  each movie, print its rating and its title on one line, and its genres on the next line. For example, if you 
 *  run the printAverageRatingsByGenre method on the files ratings_short.csv and ratedmovies_short.csv with a 
 *  minimal rater of 1 and the genre “Crime”, you should see
 */
public void printAverageRatingsByGenre() {
	
	movieInitializer();
	
	int minimalRater = 20;
	String genre = "Comedy";
	Filter filterGenre = new GenreFilter(genre);
	ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRater, filterGenre);
	Collections.sort(ratings);
	
	int count = 0;
	for(Rating rating : ratings) {
		double value = rating.getValue();
		if (value != 0.0) {
			count++;
			String movieId = rating.getItem();
			System.out.println(value + " "+MovieDatabase.getTitle(movieId));
			System.out.println(" " +MovieDatabase.getGenres(movieId));
		}
	}
	System.out.println(count +" Movies and "+minimalRater+ " ratings that setisfy the filter.");
}


	/*
	 * n the MovieRunnerWithFilters class, create a void method named printAverageRatingsByMinutes 
	 * that should create a MinutesFilter and call getAverageRatingsByFilter to get 
	 * an ArrayList of type Rating of all the movies that have a specified number of 
	 * minimal ratings and their running time is at least a minimum number of minutes 
	 * and no more than a maximum number of minutes. Print the number of movies found, 
	 * and for each movie print its rating, its running time, and its title on one line. 
	 * For example, if you run the printAverageRatingsByMinutes method on the files 
	 * ratings_short.csv and ratedmovies_short.csv with a minimal rater of 1, minimum 
	 * minutes of 110, and maximum minutes of 170, then you should see
	 */
	public void printaverageRatingByMinutes() {
		
		movieInitializer();
		
		int minimalRater = 5;
		int minminutes = 105;
		int maxminutes = 135;
		Filter filterMinutes = new MinutesFilter(minminutes, maxminutes);
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRater, filterMinutes);
		Collections.sort(ratings);
		
		int num = 0;
		for (Rating r : ratings) {
			double currValue = r.getValue();
			if (currValue != 0.0) {
				num++;
				String currMovieId = r.getItem();
				System.out.println(currValue + " " +" Time: "+ MovieDatabase.getMinutes(currMovieId)
									+ " " + MovieDatabase.getTitle(currMovieId));
			}
		}
		System.out.println("There are "+ num +" movie hava at Least "+ minimalRater+ " ratings and satisfy the filter");
	}
	
	/*
	 * In the MovieRunnerWithFilters class, create a void method named printAverageRatingsByDirectors
	 *  that should create a DirectorsFilter and call getAverageRatingsByFilter to get an ArrayList 
	 *  of type Rating of all the movies that have a specified number of minimal ratings and include 
	 *  at least one of the directors specified. Print the number of movies found, and for each 
	 *  movie print its rating and its title on one line, and all its directors on the next line. 
	 *  For example, if you run the printAverageRatingsByDirectors method on the files 
	 *  ratings_short.csv and ratedmovies_short.csv with a minimal rater of 1 and the directors 
	 *  set to "Charles Chaplin,Michael Mann,Spike Jonze", you should see:
	 */
	public void printAverageRatingdByDirectors() {
		
		movieInitializer();
		
		int minimalRater = 4;
		String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
		Filter filterDirectors = new DirectorsFilter(directors);
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRater, filterDirectors);
		Collections.sort(ratings);
		
		int count = 0;
		for (Rating currRating : ratings) {
			double currValue = currRating.getValue();
			if (currValue != 0.0) {
				count++;
				String currMovieId = currRating.getItem();
				System.out.println(currValue + "" + MovieDatabase.getTitle(currMovieId));
				System.out.println(" "+ MovieDatabase.getDirector(currMovieId));
			}
		}
		System.out.println("There are "+ count+ " movie have at least "+ minimalRater+ " that setisfy filter.");
	}
	
	/*
	 *In the MovieRunnerWithFilters class, create a void method named printAverageRatingsByYearAfterAndGenre 
	 *that should create an AllFilters object that includes criteria based on movies that came out in a 
	 *specified year or later and have a specified genre as one of its genres. This method should call 
	 *getAverageRatingsByFilter to get an ArrayList of type Rating of all the movies that have a specified 
	 *number of minimal ratings and the two criteria based on year and genre. Print the number of movies 
	 *found, and for each movie, print its rating, its year, and its title on one line, and all its genres 
	 *on the next line. For example, if you run the printAverageRatingsByYearAfterAndGenre method on the 
	 *files ratings_short.csv and ratedmovies_short.csv with a minimal rater of 1, the year set to 1980, 
	 *and the genre set to “Romance”, then you should see: 
	 */
	public void printAverageRatingsByYearAfterAndGenre() {
		
		movieInitializer();
		
		int minimalRaters = 8;
		Filter fY = new YearAfterFilter(1990);
		Filter fG = new GenreFilter("Drama");
		
		AllFilters filters = new AllFilters();
		filters.addFilter(fY);
		filters.addFilter(fG);
		
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filters);
		Collections.sort(ratings);
		
		int count =0;
		for (Rating currRating : ratings) {
			double currValue = currRating.getValue();
			if (currValue != 0.0) {
				count++;
				String currMovieID = currRating.getItem();
				System.out.println(currValue + " "+MovieDatabase.getYear(currMovieID) + MovieDatabase.getTitle(currMovieID));
				System.out.println(" "+ MovieDatabase.getGenres(currMovieID));
			}
		}
		System.out.println("There are "+ count + " movies have at least "+ minimalRaters+ " ratings and satisfy the filter");
	}
	
	/*
	 * In the MovieRunnerWithFilters class, create a void method named printAverageRatingsByDirectorsAndMinutes that should
	 *  create an AllFilters object that includes criteria based on running time (at least a specified minimum number of 
	 *  minutes and at most a specified maximum number of minutes), and directors (at least one of the directors in a 
	 *  list of specified directors—directors are separated by commas). This method should call getAverageRatingsByFilter 
	 *  to get an ArrayList of type Rating of all the movies that have a specified number of minimal ratings and the two 
	 *  criteria based on minutes and directors. Print the number of movies found, and for each movie, print its rating, 
	 *  its time length, and its title on one line, and all its directors on the next line. For example, if you run the 
	 *  printAverageRatingsByDirectorsAndMinutes method on the files ratings_short.csv and ratedmovies_short.csv with a 
	 *  minimal rater of 1, minimum minutes set to 30, maximum minutes set to 170, and the directors set to "Spike 
	 *  Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola", then you should see:
	 */
	public void printAverageRatingsByDirectorsAndMinutes() {
		
		movieInitializer();
		
		int minimalRaters = 3;
		int maxMinutes = 180;
		int minMinutes = 90;
		
		String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
		
		Filter dir = new DirectorsFilter(directors);
		Filter minutes = new MinutesFilter(minMinutes, maxMinutes);
		
		AllFilters filters = new AllFilters();
		filters.addFilter(dir);
		filters.addFilter(minutes);
		
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filters);
		Collections.sort(ratings);
		
		int count = 0;
		for(Rating currRating : ratings) {
			double currValue = currRating.getValue();
			if (currValue != 0.0) {
				count++;
				String currMovieID = currRating.getItem();
				System.out.println(currValue + " "+MovieDatabase.getMinutes(currMovieID) + MovieDatabase.getTitle(currMovieID));
				System.out.println(" "+ MovieDatabase.getDirector(currMovieID));
			}
		}
		System.out.println("There are "+ count + " movies have at least "+ minimalRaters+ " ratings and satisfy the filter");
	}
	
}
