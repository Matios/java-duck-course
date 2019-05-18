
/*
 Create a new class named DirectorsFilter that implements Filter.
  The constructor should have one parameter named directors representing 
  a list of directors separated by commas (Example: "Charles Chaplin,Michael 
  Mann,Spike Jonze", and its satisfies method should return true if a movie 
  has at least one of these directors as one of its directors. Note that each 
  movie may have several directors.
 */
public class DirectorsFilter implements Filter {

	private String directors;
	
	public DirectorsFilter(String dir) {
		// TODO Auto-generated constructor stub
		directors = dir;
	}
	
	@Override
	public boolean satisfies(String id) {
		// TODO Auto-generated method stub
		String[] dir = directors.split(",");
		
		for (int i = 0; i < dir.length; i++) {
			if (MovieDatabase.getDirector(id).indexOf(dir[i]) != -1) {
				
					return true;
			}
		}
		return false;
	}

}
