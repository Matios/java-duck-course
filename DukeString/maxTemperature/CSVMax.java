
/**
 * Write a description of CSVMax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSoFar = null;
        for(CSVRecord currentRow : parser) {
           largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }
    
    public void testHottestInDay(){
        FileResource fr = new FileResource();//"weather-2014-weather-2015-01-01");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest temperature was "+ largest.get("TemperatureF")+
                           " at " + largest.get("TimeEST"));
    }
    public CSVRecord hottestInManyDays(){
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow,largestSoFar);
        }
        return largestSoFar;
    }
    public void testHottestInManyDays () {
        CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest temerature was "+ largest.get("TemperatureF")+ " at "
                            + largest.get("DateUTC"));
    }
    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar){
         if(largestSoFar == null){
                largestSoFar = currentRow;
            }
            else{
                double currentTem = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTem = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if(currentTem > largestTem) {
                    largestSoFar = currentRow;
                }
            }
         return largestSoFar;
    }
}
