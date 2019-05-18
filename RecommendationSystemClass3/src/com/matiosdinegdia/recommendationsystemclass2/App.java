//package edu.duke;
package com.matiosdinegdia.recommendationsystemclass2;

import java.util.ArrayList;

import edu.duke.FileResource;

public class App {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		;
		String fileName = "data/ratings.csv";
		String anID = "ID 0";
		String aTitle = "Aqua man";
		String aYear = "2019";
		String theGenres = "Cartoon, Spiderhero";
		FirstRatings fr = new FirstRatings();
		fr.testLoadMovies();
	ArrayList<String> x = new ArrayList<String>();
		x.add("matios, Hagos, lily");
		x.add("yared");
		x.add("Henok");
		x.add("Elyas");
		System.out.println(x);
		for (int i = 0; i < x.size(); i++) {
			System.out.println(x.get(i)+ " ");
		}
	}

}
