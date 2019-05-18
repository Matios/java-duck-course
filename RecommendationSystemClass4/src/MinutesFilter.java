

public class MinutesFilter implements Filter {
	
/*
 *  Create a new class named MinutesFilter that implements Filter. Its satisfies 
 *  method should return true if a movie’s running time is at least min minutes 
 *  and no more than max minutes.
 */
	private int minMinutes;
	private int maxMinutes;
	
	MinutesFilter(int min, int max){
		minMinutes = min;
		maxMinutes = max;
	}
	
	@Override
	public boolean satisfies(String id) {
		// TODO Auto-generated method stub
		int duration = MovieDatabase.getMinutes(id);
		return (duration >= minMinutes) && (duration <= maxMinutes);
	}
}
