

import java.util.ArrayList;
import java.util.Random;

public class RecommendationRunner implements Recommender {

	@Override
	public ArrayList<String> getItemsToRate() {
		
		//1, creat a new Arraylist<string> MovieDatabase filter with the Filter True Filter "movieId"
		ArrayList<String> md = MovieDatabase.filterBy(new TrueFilter());
		//2, Creat an empty ArrayList<string> result
		ArrayList<String> result = new ArrayList<String>();
		//3, created a new Random Object
		Random rand = new Random();
		//4, iterate 15 items with for loop
		for (int i = 0; i < 10; i++) {
       //	    a, Add a movie id to result using Random .NextInt with a max of movieIds.size
			int num = rand.nextInt( MovieDatabase.size());
			result.add(md.get(num));
		}
		
		//5, return result
		return result;
	}

	@Override
	public void printRecommendationsFor(String webRaterID) {
		
//		int maxNum = 20;
//		MovieDatabase.initialize("ratedmoviesfull.csv");
//        FourthRatings fourth = new FourthRatings();
//        ArrayList<Rating> result = fourth.getSimilarRatings(webRaterID, 10, 3);
//        int size = result.size();
//        if (size == 0){
//            System.out.println("Recommendation List:");
//            System.out.println("Not Found");
//        } else {
//            if (size > maxNum){
//                size = maxNum;
//            }
//            
//          Rating rating = null;
//          String id = "";
//          String title = "";
//          double value = 0.0;
//            System.out.println("<table> <tr>  <th>Title</th> <th>Rating Value</th> </tr>");
//            for (int k=0; k<size ; k++){
//                 rating = result.get(k);
//                 id = rating.getItem();
//                 title = MovieDatabase.getTitle(id);
//                 value = rating.getValue();
//                String poster = "<img src=\"" + MovieDatabase.getPoster(id) + "\"/>";
//               // System.out.println( "<tr><td>" + title + "</td> <td>" + Double.toString(value) + "</td> </tr>" + 
//            	//				"</table>");
//        }
//            System.out.println( "<tr><td>" + title + "</td> <td>" + Double.toString(value) + "</td> </tr>" + 
//					"</table>");
//      }
		
		   
	     
	       int numSimilarRaters = 10;
	       int minimalRaters = 3;
	       int  maxRecNum = 20;
		    MovieDatabase.initialize("ratedmoviesfull.csv");
	        FourthRatings fourth = new FourthRatings();
	        ArrayList<Rating> result = fourth.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);
	        int num = result.size();
	        if (num == 0){
	            System.out.println("Recommendation List:");
	            System.out.println("Not Found");
	        } else {
	            if (num > maxRecNum){
	                num = maxRecNum;
	            }
	            String header = ("<table> <tr> <th>Poster</th> <th>Title</th> <th>Rating Value</th> </tr>");
	            String body = "";
	            for (int k=0; k<num; k++){
	                Rating currRating = result.get(k);
	                String currMovieID = currRating.getItem();
	                // System.out.println(MovieDatabase.getTitle(currMovieID) + " : " + currRating.getValue());
	                String currMovieTitle = MovieDatabase.getTitle(currMovieID);
	                double currRatingValue = currRating.getValue();
	                String currGenre = MovieDatabase.getGenres(currMovieID);
	                String poster = "<img src=\"" + MovieDatabase.getPoster(currMovieID) + "\"/>";
	                body += printOut(currMovieTitle, currRatingValue, poster);
	            }
	            System.out.println(header + body + "</table>");
	        }
	}
	private String printOut(String title, double value, String poster){
        return ("<tr> <td>"+poster+"</td> <td>" + title + "</td> <td>" + Double.toString(value) + "</td> </tr>");
    }

}
