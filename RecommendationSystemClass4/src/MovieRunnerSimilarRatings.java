

import java.util.ArrayList;
import java.util.Collections; 

/*
 * Create a new class named MovieRunnerSimilarRatings. Copy 
 * the two methods printAverageRatings and printAverageRatingsByYearAfterAndGenre 
 * from MovieRunnerWithFilters to this new class and modify them to work with 
 * a FourthRatings object instead of a ThirdRatings object. You can copy more of 
 * the methods into your new Runner class, but these two should be enough to test 
 * that FourthRatings has been set up correctly. When you run these two you should
 *  get the same output you get when those methods run with the ThirdRatings object.
 */
	public class MovieRunnerSimilarRatings {

	private FourthRatings fr;
	private String movieFileName = "ratedmoviesfull.csv";
	private String ratingFileName = "rating.csv";
	
	public MovieRunnerSimilarRatings() {
	 // TODO Auto-generated constructor stub
		fr = new FourthRatings(movieFileName);
	}
	
 	public void movieInitializer() {
		
		MovieDatabase.initialize(movieFileName);
		RaterDatabase.initialize(ratingFileName);
		System.out.println("Read data for "+ RaterDatabase.size() + " raters");
		System.out.println("Read data for "+ MovieDatabase.size() + " movies");
		
	}
	
	public void printAverageRatings() {
		
		movieInitializer();
		
        //You will call getAverageRatings with a minimal number of raters to return
        //an ArrayList of type Rating.
        int minimalRaters = 35;
        ArrayList<Rating> ratings = fr.getAverageRatings(minimalRaters);
        
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
        System.out.println("There are "+ num + " movies have at least "+ minimalRaters+" ratings");
	}
	
	public void printAverageRatingsByYearAfterAndGenre() {
		
		movieInitializer();
		
		int minimalRaters = 8;
		Filter fY = new YearAfterFilter(1990);
		Filter fG = new GenreFilter("Drama");
		
		AllFilters filters = new AllFilters();
		filters.addFilter(fY);
		filters.addFilter(fG);
		
		ArrayList<Rating> ratings = fr.getAverageRatingsByFilter(minimalRaters, filters);
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
 * Write a void method printSimilarRatings that has no parameters. This method creates a new FourthRatings object, 
 * reads data into the MovieDatabase and RaterDatabase, and then calls getSimilarRatings for a particular rater ID, 
 * a number for the top number of similar raters, and a number of minimal rateSimilarRatings, and then lists 
 * recommended movies and their similarity ratings. For example, using the files ratedmoviesfull.csv and ratings.csv 
 * and the rater ID 65, the number of minimal raters 5, and the number of top similar raters set to 20, the movie 
 * returned with the top rated average is “The Fault in Our Stars”.
 */
 public void printSimilarRatings() {
	movieInitializer();
	String id = "337";
	int minimalRaters = 3;
	int numSimilarRaters = 10;
	ArrayList<Rating > sr = fr.getSimilarRatings(id, numSimilarRaters, minimalRaters);
	for (Rating rating : sr) {
		System.out.println(MovieDatabase.getTitle(rating.getItem())+ " "+rating.getValue());
	}
 }
 
 /*
  * Write a void method printSimilarRatingsByGenre that has no parameters. This method is similar to printSimilarRatings
  *  but also uses a genre filter and then lists recommended movies and their similarity ratings, and for each movie prints 
  *  the movie and its similarity rating on one line and its genres on a separate line below it. For example, using the files 
  *  ratedmoviesfull.csv and ratings.csv, the genre “Action”, the rater ID 65, the number of minimal raters set to 5, and the 
  *  number of top similar raters set to 20, the movie returned with the top rated average is “Rush”.
  */
 public void printSimilarRatingsByGenre() {
	 movieInitializer();
	 String id = "964";
	 int minimalRaters = 5;
	 int numSimilarRaters = 20;
	 Filter fc = new GenreFilter("Mystery");
	 ArrayList<Rating > sr = fr.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, fc);
	 for (Rating rating : sr) {
		 System.out.println(MovieDatabase.getTitle(rating.getItem())+ " "+rating.getValue());
	}
	 
 }

 /*
  * Write a void method printSimilarRatingsByDirector that has no parameters. This method is similar to printSimilarRatings 
  * but also uses a director filter and then lists recommended movies and their similarity ratings, and for each movie 
  * prints the movie and its similarity rating on one line and its directors on a separate line below it. For example, 
  * using the files ratedmoviesfull.csv and ratings.csv, the directors “Clint Eastwood,Sydney Pollack,David Cronenberg,
  * Oliver Stone”, the rater ID 1034, the number of minimal raters set to 3, and the number of top similar raters set to 
  * 10, the movie returned with the top rated average is “Unforgiven”.
  */
 public void printSimilarRatingsByDirector() {
	 movieInitializer();
	 String id = "120";
	 int minimalRaters = 2;
	 int numSimilarRaters = 10;
	 Filter df = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
	 ArrayList<Rating > sr = fr.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, df);
	 for (Rating rating : sr) {
		 System.out.println(MovieDatabase.getTitle(rating.getItem())+ " "+rating.getValue());
	}
 }
 
 /*
  *Write a void method printSimilarRatingsByGenreAndMinutes that has no parameters. This method is similar to printSimilarRatings 
  *but also uses a genre filter and a minutes filter and then lists recommended movies and their similarity ratings, and for each 
  *movie prints the movie, its minutes, and its similarity rating on one line and its genres on a separate line below it. For 
  *example, using the files ratedmoviesfull.csv and ratings.csv, the genre “Adventure”, minutes between 100 and 200 inclusive, 
  *the rater ID 65, the number of minimal raters set to 5, and the number of top similar raters set to 10, the movie returned 
  *with the top rated average is “Interstellar”.
  */
 public void  printSimilarRatingsByGenreAndMinutes() {
	 
	 	movieInitializer();
	    int minimalRaters = 3;
		Filter minutes = new MinutesFilter(80,160);
		Filter gF = new GenreFilter("Drama");
		
		AllFilters filters = new AllFilters();
		filters.addFilter(minutes);
		filters.addFilter(gF);
		
		 String id = "168";
		 int numSimilarRaters = 10;
		 
		 ArrayList<Rating > sr = fr.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, filters);
		 for (Rating rating : sr) {
			 System.out.println(MovieDatabase.getTitle(rating.getItem())+ " "+rating.getValue());
		}
 }
 
 /*
  * Write a void method printSimilarRatingsByYearAfterAndMinutes that has no parameters. This method is similar to printSimilarRatings 
  * but also uses a year-after filter and a minutes filter and then lists recommended movies and their similarity ratings, and for 
  * each movie prints the movie, its year, its minutes, and its similarity rating on one line. For example, using the files 
  * ratedmoviesfull.csv and ratings.csv, the year 2000, minutes between 80 and 100 inclusive, the rater ID 65, the number of 
  * minimal raters set to 5, and the number of top similar raters set to 10, the movie returned with the top rated average is 
  * “The Grand Budapest Hotel”.
  */
  public void printSimilarRatingsByYearAfterAndMinutes() {
	  movieInitializer();
	    int minimalRaters = 5;
		Filter minutes = new MinutesFilter(70,200);
		Filter gF = new YearAfterFilter(1975);
		
		AllFilters filters = new AllFilters();
		filters.addFilter(minutes);
		filters.addFilter(gF);
		
		 String id = "314";
		 int numSimilarRaters = 10;
		 
		 ArrayList<Rating > sr = fr.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, filters);
		 for (Rating rating : sr) {
			 System.out.println(MovieDatabase.getTitle(rating.getItem())+ " "+rating.getValue());
		}
  }
}
