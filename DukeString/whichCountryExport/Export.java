
/**
 * Write a description of Export here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Export {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        for(CSVRecord record : parser) {
            String export = record.get("Exports");
            if(export.contains(exportOfInterest)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    // Write a method named tester that will create your CSVParser
    // call each of the methods below in parts 2, 3, 4, and 5.
    // Each time you want to use the parser with another method, you will need
    // to reset the parser by calling fr.getCSVParser() again to get 
    // a new parser.
    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //listExporters(parser, "cocoa");
        //parser = fr.getCSVParser();
        //countryInfo(parser, "Nauru.");
       // parser = fr.getCSVParser();
       //listExportersTwoProducts(parser,"cotton", "flowers");
       // parser = fr.getCSVParser();
       // int count = numberOfExporters(parser,"sugar");
       // System.out.println(count);
       // parser = fr.getCSVParser();
         bigExporters(parser, "$999,999,999,999");
        
    }
    
    // Write a method named countryInfo that has two parameters, parser 
    // is a CSVParser and country is a String. This method returns a 
    // string of information about the country or returns “NOT FOUND” 
    // if there is no information about the country. The format of the 
    // string returned is the country, followed by “: “, followed by a 
    // list of the countries’ exports, followed by “: “, followed by the
    // countries export value.
    public String countryInfo(CSVParser parser, String country) {
        
        String export = "";
        System.out.print(country +": ");
        for(CSVRecord record : parser) {
            export = record.get("Country");
            if(export.contains(country)) {
                String aCountry = record.get("Exports");
                String value = record.get("Value (dollars)");
                System.out.print(aCountry);
                System.out.println(": "+ value);
            }
            else{
                System.out.println("NOT FOUND.");
                break;
            }
        }
        return export;
    }
    
    // Write a void method named listExportersTwoProducts that has three
    // parameters, parser is a CSVParser, exportItem1 is a String and 
    // exportItem2 is a String. This method prints the names of all the 
    // countries that have both exportItem1 and exportItem2 as export items. 
    // For example, using the file exports_small.csv, this method called with 
    // the items “gold” and “diamonds” would print the countries
    public void listExportersTwoProducts (CSVParser parser, String exportItem1,
                                            String exportItem2) {
                                                
         for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }                                        
    }
    
    // Write a method named numberOfExporters, which has two parameters, 
    // parser is a CSVParser, and exportItem is a String. This method 
    // returns the number of countries that export exportItem. For example, 
    // using the file exports_small.csv, this method called with the item 
    // “gold” would return 3.
    public int numberOfExporters (CSVParser parser, String exportItem) {
         
        int count = 0;
        for (CSVRecord record : parser) {
            String export = record.get ("Exports");
            if (export.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }
     
    // Write a void method named bigExporters that has two parameters, parser 
    // is a CSVParser, and amount is a String in the format of a dollar sign, 
    // followed by an integer number with a comma separator every three digits
    // from the right. An example of such a string might be “$400,000,000”. 
    // This method prints the names of countries and their Value amount for 
    // all countries whose Value (dollars) string is longer than the amount 
    // string. You do not need to parse either string value as an integer, 
    // just compare the lengths of the strings. For example, if bigExporters 
    // is called with the file exports_small.csv and amount with the string 
    // $999,999,999, then this method would print eight countries and their 
    // export values shown here:
    public void bigExporters (CSVParser parser, String amount) {
        for(CSVRecord record : parser) {
            String value = record.get ("Value (dollars)");
            if (value.length() > amount.length()) {
                String country = record.get("Country");
                System.out.println(country + " " + value);
                
            }
        }
        
    }
}
