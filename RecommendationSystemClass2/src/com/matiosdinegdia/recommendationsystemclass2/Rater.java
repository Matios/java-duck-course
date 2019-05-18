package com.matiosdinegdia.recommendationsystemclass2;

import java.util.ArrayList;
/*
 * Create a new public interface named Rater. Add methods to this 
 * new interface by copying all the method signatures from the PlainRater 
 * class. Copy just the methods—do not include the constructors or the 
 * private instance variables. The first line of the interface should be:
 */
public interface Rater {
	public void addRating(String item, double rating);
	public boolean hasRating(String item);
	public String getID();
	public double getRating(String item);
	public int numRating();
	public ArrayList<String> getItemsRated();
	
}
