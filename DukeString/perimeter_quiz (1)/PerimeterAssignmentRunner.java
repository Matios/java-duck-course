import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count =0;
        for(Point currPt : s.getPoints()){
            count++;
          }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
         // Start with totalPerim = 0
        double totalPerim = 0.0;
        double average=0.0;
        int count =0;
        for(Point currPt : s.getPoints()){
            count++;
          }
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            System.out.println("current distance "+ currDist);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        average=totalPerim/count;
        return average;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestDistance =0.0;
        Point prevPt = s.getLastPoint();
        for(Point currPt : s.getPoints()){
            double currDist = prevPt.distance(currPt);
            
            if( currDist > largestDistance){
                largestDistance = currDist;
            }
            prevPt =currPt;
            System.out.println("Largest distance "+ largestDistance);
        }
        return largestDistance;
    }

    public double getLargestX(Shape s) {
        // Put code here
       double xLargest=0.0;
       boolean firstPoint = true;
       
        for(Point currPoint: s.getPoints()){
            if(firstPoint){
                 xLargest =currPoint.getX();
                 firstPoint=false;
                }
           
            //System.out.println("Comparing " + currPoint.getX() + " to " +
              //  xLargest);
            if(currPoint.getX() > xLargest){
               // System.out.println("currPoint.getX() is larger than xLargest");
                xLargest =currPoint.getX();
                // System.out.println(xLargest);
            } //else System.out.println("currPoint.getX() is smaller than xLargest");
        }
        return xLargest;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        
        return 0.0;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int count = getNumPoints(s);
        double average =getAverageLength(s);
        double largest= getLargestSide(s);
        double xValue = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of points :"+ count);
        System.out.println("Average :"+ average);
        System.out.println("The largest side :"+largest);
        System.out.println("Largest X value "+xValue);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
       
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
