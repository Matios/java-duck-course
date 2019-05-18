//package edu.duke;
package com.matiosdinegdia.recommendationsystemclass2;

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
		//tr.testAverage();
		MovieRunnerWithFilters mr = new MovieRunnerWithFilters();
		//mr.printAverageRatings();
		//mr.printAverageRatingsByYear();
		//mr.printAverageRatingsByGenre();
		//mr.printaverageRatingByMinutes();
		//mr.printAverageRatingdByDirectors();
		//mr.printAverageRatingsByYearAfterAndGenre();
		mr.printAverageRatingsByDirectorsAndMinutes();
	}
}
