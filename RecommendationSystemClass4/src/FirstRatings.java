

import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.*;
import java.io.*;

/*
 * Now change FirstRatings to use EfficientRater instead of PlainRater. 
 * You should now be able to compile FirstRatings and SecondRatings.
 * Try running your MovieRunnerAverage class. It should run as before, but much faster.
 */

public class FirstRatings {
	
/*
 * Write a method named loadMovies that has one parameter, a String named filename. 
 * This method should process every record from the CSV file whose name is filename, 
 * a file of movie information, and return an ArrayList of type Movie with all of the
 *  movie data from the file.
*/
	public ArrayList<Movie> loadMovies(String fileName) {
		
		ArrayList<Movie> movies = new ArrayList<Movie>();
		FileResource fr = new FileResource(fileName);
		int count = 0;
		
		for (CSVRecord record : fr.getCSVParser()) {
			int theMinutes;
			try {
				theMinutes = Integer.parseInt(record.get("minutes"));
			} catch (Exception e) {
				// TODO: handle exception
				theMinutes = 0;
			}
			count++;
			Movie movie = new Movie(record.get("id"),record.get("title") , record.get("year"), 
					record.get("genre"),record.get("director"), record.get("country"), record.get("poster"), theMinutes);
			
			//System.out.println(count +" "+record.get("id")+" "+record.get("title")+" "+year+" "+record.get("country")+" "+
				//				record.get("genre")+" "+theMinutes+ " "+ record.get("poster"));
			movies.add(movie);
		}
		//System.out.println(count+" "+movies.size());
		
		return movies;
	}
	
	/*
	 * Write a void method named testLoadMovies that should do several things.
       Call the method loadMovies on the file ratedmovies_short.csv 
		and store the result in an ArrayList local variable . Print the number of movies, 
		and print each movie. You should see there are five movies in this file, which are
		 all shown above. After this works you should comment out the printing of the movies. 
		 If you run your program on the file ratedmoviesfull.csv, you should see there are 
		 3143 movies in the file.
		 
		Add code to determine how many movies include the Comedy genre. 
		In the file ratedmovies_short.csv, there is only one.
		Add code to determine how many movies are greater than 150 
		minutes in length. In the file ratedmovies_short.csv, there are two.
		
		Add code to determine the maximum number of movies by any director, 
		and who the directors are that directed that many movies. 
		Remember that some movies may have more than one director. 
		In the file ratedmovies_short.csv the maximum number of movies by any 
		director is one, and there are five directors that directed one such movie.
	 */
	public void testLoadMovies() {
		
		 //Call loadMovies on the rile 'ratedmovies_short.csv' and store to a local ArrayList variable
        ArrayList<Movie> movies = loadMovies("data/ratedmovies_short.csv");
        //ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        //print the number of movies
        System.out.println(movies.size() + " movies were loaded.");
        
        //print each movie
        /*
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i));
        }
        */
        //check how many movies include the Comedy genre
        int numComedy = 0;
        for (int i = 0; i < movies.size(); i++) {
            //Get current movie object
            Movie currMovie = movies.get(i);
            //Get genres of current movie and store it in a string
            String currGenres = currMovie.getGenres();
            //Check if 'Comedy' index exists in currGenres string
            int idxComedy = currGenres.indexOf("Comedy");
            //if index is equal to -1
            if (idxComedy == -1) {
                //go on to the next iteration
                continue;
            }
            //Otherwise, Comedy is listed in current movie's genre
            //Increment numComedy by one
            numComedy++;
        }
        
        System.out.println("There are " + numComedy + " Comedy movies in the file.");
        
        int numGrThan150Min = 0;
        for (int i = 0; i < movies.size(); i++) {
            //get current movie object
            Movie currMovie = movies.get(i);
            //Get minutes of current movie and store it in an integer
            int minutes = currMovie.getMinutes();
            //Check if minutes is greater than 150
            if (minutes > 150) {
                //if so, add to numGrThan150Min counter
                numGrThan150Min++;
            }
        }
        
        System.out.println("There are " + numGrThan150Min + " movies that are greater than 150 minutes in length in the file.");
        
        ArrayList<String> directors = new ArrayList<String>();
        ArrayList<Integer> directorCounts = new ArrayList<Integer>();
        
        //for each director in movies
        for (int i = 0; i < movies.size(); i++) {
            //Get director name as a string
            String currDirector = movies.get(i).getDirector();
            //Get index of currDirector in directors
            int checkIdx = directors.indexOf(currDirector);
            //If idx == -1, currDirector has not been added yet
            if (checkIdx == -1) {
                //add current director into directors array list
                directors.add(currDirector);
                //initialize the count to one
                directorCounts.add(1);
            } else {
             
               directorCounts.set(checkIdx, directorCounts.get(checkIdx) + 1);
            }
        }
        
        //Get max number in directorCounts
        int max = 0;
        for (int i = 0; i < directorCounts.size(); i++) {
            if (directorCounts.get(i) > max) {
                max = directorCounts.get(i);
            }
        }
        
        System.out.println("The maximum number of movies by any director is " + max);
        
        //Now that we have the max number in directorCount
        //We must store indices that have the max number into an ArrayList of integers called indices
        ArrayList<Integer> indices = new ArrayList<Integer>();
        
        for (int i = 0; i < directorCounts.size(); i++) {
            if (directorCounts.get(i) == max) {
                indices.add(i);
            }   
        }
        
        //Now, print out the name of each director that has a max number of movies directed in the file
        System.out.println("Directors that directed " + max + " amount of movies are as follows:");
        for (int i = 0; i < indices.size(); i++) {
            String herrDirektor = directors.get(indices.get(i));
            System.out.println(herrDirektor);
        }
	}
	
/*
 * In the FirstRatings class, write a method named loadRaters that has one parameter named filename. 
 * This method should process every record from the CSV file whose name is filename, a file of raters
 *  and their ratings, and return an ArrayList of type Rater with all the rater data from the file.
 */
	public ArrayList<Rater> loadRater(String filename){
		ArrayList<Rater> raters = new ArrayList<Rater>();
		FileResource fr = new FileResource(filename);
		double aRating ;
		int count = 0;
		
		for (CSVRecord record : fr.getCSVParser()) {
			int prevIdx = raters.size() - 1;
			String item = record.get("movie_id");
			String id = record.get("rater_id");
			int currId = Integer.parseInt(id);
			
			try {
				aRating = Double.parseDouble(record.get("rating"));
			} 
			catch (Exception e) {
				// TODO: handle exception
				aRating = 0.0;
			}
			
			int privID ;
			if (raters.size() == 0) {
				privID = -1;
			}
			else {
				privID = Integer.parseInt(raters.get(prevIdx).getID());
			}
			
			if (currId == privID) {
				raters.get(prevIdx).addRating(item, aRating);
			}
			else {
				//Rater rater = new PlainRater(id);
				Rater rater = new EfficientRater(id);
				rater.addRating(item, aRating);
				raters.add(rater);
			}
			
			if (count != Integer.parseInt(record.get("rater_id"))) {
				
				//System.out.println(record.get("rater_id")+ " "+ item+" "+aRating);
				//continue;
				count++;
			}
			
			//count++;	
		}
		
		//System.out.print(count+ " number of raters ");
		return raters;
	}
	
	/*
	 * Write a void method named testLoadRaters that should do several things.

	   Call the method loadRaters on the file ratings_short.csv and store the result in a local ArrayList variable. 
	   Print the total number of raters. Then for each rater, print the rater’s ID and the number of ratings they 
	   did on one line, followed by each rating (both the movie ID and the rating given) on a separate line. 
	   If you run your program on the file ratings_short.csv you will see there are five raters. After it looks 
	   like it works, you may want to comment out the printing of each rater and their ratings. If you run your
	   program on the file ratings.csv, you should get 1048 raters.
	   
	   Add code to find the number of ratings for a particular rater you specify in your code. For example, 
	   if you run this code on the rater whose rater_id is 2 for the file ratings_short.csv, you will see they
	   have three ratings.
	   
       Add code to find the maximum number of ratings by any rater. Determine how many raters have this maximum
       number of ratings and who those raters are. If you run this code on the file ratings_short.csv, you will 
       see rater 2 has three ratings, the maximum number of ratings of all the raters, and that there is only 
       one rater with three ratings.
       
       Add code to find the number of ratings a particular movie has. If you run this code on the file 
       ratings_short.csv for the movie “1798709”, you will see it was rated by four raters.
       
       Add code to determine how many different movies have been rated by all these raters. If you
        run this code on the file ratings_short.csv, you will see there were four movies rated.
	 */
	public void testLoadRaters() {
		
		//String fileName = "data/ratings_short.csv";
		String fileName = "data/ratings.csv";
		ArrayList<Rater> raters = loadRater(fileName);
		
		//Hashset for non repeating rater IDs
        HashSet<String> raterIDs = new HashSet<String>();
        
        //ArrayList for keeping track of number of ratings
        ArrayList<Integer> numRatingsList = new ArrayList<Integer>();
        
        //for each rater
        for (int i = 0; i < raters.size(); i++) {
        	
            String raterID = raters.get(i).getID();
            //put raterID into hashset to avoid repeats
            if (raterIDs.add(raterID)) {
                //If added to hashset, update numRatings with the number of ratings for current raterID
                numRatingsList.add(raters.get(i).numRating());
            }
            
            //print the rater's ID
            //System.out.print("Rater ID: " + raterID);
            //print the number of ratings they did
            //System.out.println(". Number of ratings: " + raters.get(i).numRatings());
            
            //Get all movie IDs rated by the current rater
            /* Comment out because for large size of raters, it is unneccessary to print all of them
            ArrayList<String> movieIDs = raters.get(i).getItemsRated();
             
            for (int j = 0; j < movieIDs.size(); j++) {
                //print movie IDs and corresponding ratings
                System.out.println("For Movie with ID: " + movieIDs.get(j) + " the rating by rater " + raterID +
                                   " is " + raters.get(i).getRating(movieIDs.get(j)));
                
            }
            
            System.out.println("\n");
            */
            
        }
        
        System.out.println("In total, there are " + raterIDs.size() + " raters in the file.");
        
        //Find number of ratings for a particular rater specified in the code.
        
        int rater = 193;
        
        int ratings = numRatingsList.get(rater - 1); //Gets numRatings stored in arraylist
        
        System.out.println("Number of ratings from rater " + rater + " is " + ratings);
        
        /*
        System.out.println("Content inside raterIDs hash set:");
        for (String s : raterIDs) {
            System.out.println(s);
        }
        */
        System.out.println("Amount of IDs from file: " + raterIDs.size());
        
        /*
        System.out.println("Content inside numRatingsList ArrayList:");
        for (int k = 0; k < numRatingsList.size(); k++) {
            System.out.println(numRatingsList.get(k));
        }
        */
        System.out.println("Amount of ID ratings from file: " + numRatingsList.size());
        
        
        //Add code to find the maximum number of ratings by any rater
        int max = 0;
        for (int i = 0; i < raters.size(); i++) {
            if (raters.get(i).numRating() > max) {
                max = raters.get(i).numRating();
            }
        }
        
        System.out.println("The maximum number of ratings by any Rater is: " + max);
        
        //Now that we have the max number in raters
        //We must store indices that have the max number into an ArrayList of integers called indices
        ArrayList<Integer> indices = new ArrayList<Integer>();
        
        for (int i = 0; i < raters.size(); i++) {
            if (raters.get(i).numRating() == max) {
                indices.add(i);
            }
            
        }
        
        //Now, print out the name of each rater that has a max number of movies rated in the file
        System.out.println("Raters that have " + max + " number of ratings are as follows:");
        for (int i = 0; i < indices.size(); i++) {
            System.out.println("Rater " + raters.get(indices.get(i)).getID());
        }
        //Add code to find the number of ratings a particular movie has
        
        //ALSO
        //Add code to determine how many different movies have been rated by all these raters.
        //We can use a hashset to get unique strings for movie IDs.
        String testMovie = "1798709";
        HashSet<String> movieIds = new HashSet<String>();
        
        int counter = 0;
        //For each rater
        for (int i = 0; i < raters.size(); i++) {
            //Get arraylist of movie IDs
            ArrayList<String> ratedMovies = raters.get(i).getItemsRated();
            //System.out.println("On rater " + raters.get(i).getID());
            //Loop through ratedMovies
            for (int j = 0; j < ratedMovies.size(); j++) {
                //Get rated movie
                String ratedMovie = ratedMovies.get(j);
                
                //Add ratedMovie to hash set to find how many different movies have been rated in the file
                movieIds.add(ratedMovie);
                //Check if current ratedMovies ID matches testMovie ID
                if (ratedMovie.equals(testMovie)) {
                    //If so, increment counter by one
                    counter++;
                }
                //If not, do nothing
            }
        }
        
        System.out.println("The movie " + "\"" + testMovie + "\"" + " was rated by " + counter + " raters.");
        //1798709 should have 4 raters in ratings_short.csv
        
        System.out.println("In total, there were " + movieIds.size() + " movies rated");
        //There should be 4 movies rated in ratings_short.csv
		
	}
}
