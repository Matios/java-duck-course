
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import edu.duke.*;
public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String word : fr.words()) {
            word = word.toLowerCase();
            /*
             * ignore punctuations
             * 
             * if(!Character.isLetter(word.charAt(0))) {
                    word = word.substring(1, word.length());
            }
            if(!Character.isLetter(word.charAt(word.length() - 1))) {
                word = word.substring(0, word.length() - 1);
            }*/
            // int index = myWords.indexOf(word);
            // if(index == -1) { //checks if the word is not yet in the ArrayList
                // myWords.add(word);
                // myFreqs.add(1);
            // } else { //if the word is already in the ArrayList
                // int value = myFreqs.get(index);
                // myFreqs.set(index, value + 1); //increment the value of the element based on the index
            // }
            // If word is unique
            if (!myWords.contains(word)) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int index = myWords.indexOf(word);
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq + 1);
            }
        }
    }
    
    public int findIndexOfMax() {
        int index = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > myFreqs.get(index)) {
                index = i;
            }
        }
        return index;
    }
    
    void tester() {
         findUnique();
        for (int k = 0; k < myWords.size(); k++) {
            System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
        }
        System.out.println("Number of unique words: " + myWords.size());
        int index = findIndexOfMax();
        System.out.println("The word that occurs the most often is " + myWords.get(index));
        System.out.println("It occurs " + myFreqs.get(index) + " times");
       
    }
}
