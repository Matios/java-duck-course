//package edu.duke;


import java.util.ArrayList;

import edu.duke.FileResource;

public class App {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		//FirstRatings fr = new FirstRatings();
		//fr.testLoadMovies();
		//fr.testLoadRaters();
		//MovieRunnerAverage mr = new MovieRunnerAverage();
		//mr.printAverageRatings();
		ThirdRatings tr = new ThirdRatings();
		FourthRatings fr = new FourthRatings();
		//tr.testAverage();
		//MovieRunnerWithFilters mr = new MovieRunnerWithFilters();
		MovieRunnerSimilarRatings mr2 = new MovieRunnerSimilarRatings();
	
		//mr2.printSimilarRatings();
		//mr2.printSimilarRatingsByGenre();
		//mr2.printAverageRatings();
		//mr2.printSimilarRatingsByDirector();
		//mr2.printSimilarRatingsByGenreAndMinutes();
		mr2.printSimilarRatingsByYearAfterAndMinutes();
	}
}
