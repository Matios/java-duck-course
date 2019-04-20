
/**
 * Write a description of VigenereBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    
    /**
     * Returns a String consisting of every totalSlices-th character from message, 
     * starting at the whichSlice-th character.
     * @parameter message - a String representing the encrypted message
     * @parameter whichSlice - an int indicating the index the slice should start from
     * @parameter totalSlices - an int indicating the length of the key
     */
    public String sliceString(String message, int whichSlice, int totalSlices) {
         StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i+= totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }
    
    public void testSliceString() {
        System.out.println(sliceString("abcdefghijklm", 0, 3));//.equals("adgjm"));
        System.out.println(sliceString("abcdefghijklm", 1, 3).equals("behk"));
        System.out.println(sliceString("abcdefghijklm", 2, 3).equals("cfil"));
        System.out.println(sliceString("abcdefghijklm", 0, 4).equals("aeim"));
        System.out.println(sliceString("abcdefghijklm", 1, 4).equals("bfj"));
        System.out.println(sliceString("abcdefghijklm", 2, 4).equals("cgk"));
        System.out.println(sliceString("abcdefghijklm", 3, 4).equals("dhl"));
        System.out.println(sliceString("abcdefghijklm", 0, 5).equals("afk"));
        System.out.println(sliceString("abcdefghijklm", 1, 5).equals("bgl"));
        System.out.println(sliceString("abcdefghijklm", 2, 5).equals("chm"));
        System.out.println(sliceString("abcdefghijklm", 3, 5).equals("di"));
        System.out.println(sliceString("abcdefghijklm", 4, 5).equals("ej"));
    };

    /**
     * Finds the shift for each index in the key, fills in the key (which is an array of 
     * integers) and return it. Makes use of the CaesarCracker class, as well as the 
     * sliceString method. 
     * @parameter encrypted - a String representing the encrypted message
     * @parameter klength - an int representing the key length 
     * @parameter mostCommon - an int representing the most common character in the language 
     * of the message
     */
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String sliced = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(sliced);
        }
        System.out.println(key.toString());
        return key;
    }
    
    public void testTryKeyLength() {
        FileResource fr = new FileResource();//"VigenereTestData/athens_keyflute.txt");
        String message = fr.asString();
        int klength = 4;//"flute".length();
        int[] key = tryKeyLength(message, klength, 'e');  // [5, 11, 20, 19, 4]
        //System.out.println(key.toString());
    }
    
    /**
     * Put everything together, so that you can create a new VigenereBreaker in BlueJ, 
     * call this method on it, and crack the cipher used on a message.
     */
    public void breakVigenere() {
        // Test this method on the text file athens_keyflute.txt, using key length 5. 
        // The first line should be "SCENE II. Athens. QUINCE'S house."
        FileResource fr = new FileResource("messages/secretmessage2.txt");
        String message = fr.asString();
        FileResource dictFile = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(dictFile);
        
        String decrypted = breakForLanguage(message, dictionary);
        System.out.println(decrypted);
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        // Make a new HashSet of Strings
        HashSet<String> words = new HashSet<String>();
        // Read each line in fr (which should contain exactly one word per line)
        for (String word : fr.words()) {
            // Convert line to lowercase
            word = word.toLowerCase();
            // Put the line into the HashSet
            words.add(word);
        }
        return words;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int realWords = 0;
        String[] words = message.split("\\W+"); 
        for (String word : words){
            word = word.toLowerCase();
            if(dictionary.contains(word)){
             realWords++;
            }
        }
        return realWords;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxRealWords = 0; 
        int finalKeyLength = 1;
        String decryptionWithMostRealWords = "";
        // For each key length from 1 to 100,
        // NOTE: There's nothing special about 100, could iterate all the way to encrypted.length()
        for (int keylength = 1; keylength <= 100; keylength++) {
            // Decrypt the message
            int[] key = tryKeyLength(encrypted, keylength, 'e');
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            // Count how many real words decrypted message contains
            int realWords = countWords(decrypted, dictionary);
            if (realWords > maxRealWords) {
                maxRealWords = realWords;
                decryptionWithMostRealWords = decrypted;
                finalKeyLength = keylength;
            }
        }
        System.out.println("Message contains " + maxRealWords + " valid words");
        System.out.println("Message decoded with keylength of " + finalKeyLength);
        return decryptionWithMostRealWords;
    }
}

