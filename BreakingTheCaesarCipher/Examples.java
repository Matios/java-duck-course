
/**
 * Write a description of Examples here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Examples {
    public void dnaFingerprints(String s) {
        int cc = 0, cg = 0, ca = 0, ct = 0;
        for(int k=0; k < s.length(); k++){
            char ch = s.charAt(k);
            if(ch == 'c'){
                cc +=1;
            }
            if(ch == 'g'){
                cg +=1;
            }
            if(ch == 'a'){
                ca +=1;
            }
            if(ch == 't'){
                ct +=1;
            }
        }
    }
    
    public void textFingerPrint(String s) {
       String alpha = "abcdefghijklmnopqrstuvwxyz"; 
       int[] counter = new int[26];
       for(int k =0; k < s.length(); k++){
           //System.out.println(alpha.charAt(k));
           char ch = s.charAt(k);
           int index = alpha.indexOf(Character.toLowerCase(ch));
           if (index != -1){
               counter[index] += 1;
           }
       }
       for (int k=0; k < counter.length; k++){
           System.out.println(alpha.charAt(k)+"\t"+ counter[k]);
       }
    }
    
    public void test(){
        textFingerPrint("my name matios fff fff fff fff");
    }
    
    public void simpleSimulate (int rolls) {
        Random rand = new Random();
        int twos = 0;
        int twelves = 0;
        
        for(int k=0; k < rolls; k++){
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            if(d1 + d2 == 2){
                twos += 1;
            }else if(d1 + d2 == 12){
                twelves += 1;
            }
        }
        System.out.println("2's = \t" + twos + "\t" + 100.0 * twos/rolls);
        System.out.println("12's = \t" + twelves + "\t" + 100.0 * twelves/rolls);
    }
    
    public void simulate (int rolls) {
        Random rand = new Random();
        int [] counts = new int [13];
        
        for(int k=0; k < rolls; k++){
         int d1 = rand.nextInt(6) + 1;
         int d2 = rand.nextInt(6) + 1;
         System.out.println("roll is " +d1+ "+" + d2 + "=" + (d1+d2));
         counts[d1+d2] += 1;
        }
        for(int k=2; k <= 12; k++){
          System.out.println(k + "'s=\t" + counts[k] + "\t" + 100.0 * counts[k]/rolls);  
        }
    }
    
    public int indexOf(String[] list, String word){
        for (int k=0; k<list.length ; k++){
            if (list[k].equals(word)){
            return k;
            }
        }
        return -1;
    }
    
    public void countWords(FileResource resource, String[] common, int[] counts){
        for(String word : resource.words()){
          word = word.toLowerCase();
          //System.out.println(word);
          int index = indexOf(common,word);
          if(index != -1){
              counts[index] += 1;
          }
        }
    }
    public String[] getCommon(){
        FileResource resource = new FileResource("common.txt");
        String[] common = new String[20];
        int index = 0;
        for(String s : resource.words()){
            common[index] = s;
            index += 1;
        }
        return common;
    }
    
    void countShakespeare(){
        //String[] plays = {"macbeth.txt","likeit.txt","caesar.txt", "errors.txt"
          //   , "hamlet.txt", "romeo.txt"};
        String[] plays = {"small.txt"};
        String[] common = getCommon();
        int[] counts = new int[common.length];
        for(int k=0; k < plays.length; k++){
            FileResource resource = new FileResource("data/" + plays[k]);
            if("data/"+plays[k] == null){
                System.out.println("File not found.");
                break;
            }
            //System.out.println(plays[k]);
            countWords(resource,common,counts);
            System.out.println("done with " + plays[k]);
        }
        for(int k=0; k < common.length; k++){
            System.out.println(common[k] + "\t" + counts[k]);
        }
    }
    
    public String decrypt(String encrypted){
       // CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        //return cc.encrypt(encrypted,26-dkey);
        return "";
    }
    public int[] countLetters(String encripted){
        int[] common = new int[20];
        return common;
    }
    public int maxIndex(int[] num) {
        
        return 0;
    }
}
    class Lines{
    public ArrayList<String> lines;
    public void Lines(){
        lines = new ArrayList<String>();
        
    }
    public void filesToLines(){
        FileResource fr = new FileResource();
        String contents = fr.asString();
       // String contents = fr.asString();
        
    }
    public void testSplit(){
       String testString = "Hello:my:name"; 
       System.out.println(testString.split("\n"));//[0]);
       //for(String s : split){
          // System
       //}
       
    }
    public void hashMap(){
    
    }
}
