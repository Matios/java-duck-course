/**
 * Write a description of Patron here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Patron {
    private String firstName;
    private String lastName;
    private ArrayList<LibraryMaterial> book;
    
    public Patron(String first, String last, ArrayList<LibraryMaterial> b){
        firstName = first;
        lastName = last;
        book = b;
    }
    public String getFirstname(){
         return firstName;
    }
    public String getLastName(){
         return lastName;
    }
    public ArrayList<LibraryMaterial> getBook(){
         return book;
    }
     
}
