
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part1 {
    String findSimpleGene(String dna){
        String match = "youtube.com";
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
                        // String lines="";
             for(String word: ur.words()){
                 if(word.indexOf(match) != -1){
                 System.out.println(word);}
                }
        String result = "";
        int startIndex =dna.indexOf("ATG");
        int stopIndex =dna.indexOf("TAA",startIndex+3);
        
        if(startIndex == -1){
            return "There is no ATG";
        }
        if(stopIndex == -1){
            return "there is no TAA";
        }
        
        result = dna.substring(startIndex, stopIndex+3);
        if(result.length() % 3 != 0){
            return "not devisibel by three";
        }
        return result;
    }
    public void testSimpleGene(){
        String dna = "ATATAGAGGAATGTAGGGTATAGGGTAATAGAG";
        System.out.println(findSimpleGene(dna));
        
         dna = "ATTGTATAA";
        System.out.println(findSimpleGene(dna));
        
         dna = "ATATAGAGGAATGTAGGGTATAGGGTAATAGAG";
        System.out.println(findSimpleGene(dna));
        
         dna = "ATATAGAGGAATGTAGGTATAGGGTAATAGAG";
        System.out.println(findSimpleGene(dna));
        
         dna = "ATATAGAGGAATGTAGGGTATAGGGTATAGAG";
        System.out.println(findSimpleGene(dna));
        
    }

}
class Part2{
    String findSimpleGene(String dna, String StartCodon, String StopCodon){
        
        String result = "";
        StartCodon = "ATG"; 
        StopCodon = "TAA";
        
        int startIndex =dna.indexOf(StartCodon);
        int stopIndex =dna.indexOf(StopCodon,startIndex+3);
        
        if(startIndex == -1){
            return "There is no ATG";
        }
        
        if(stopIndex == -1){
            return "there is no TAA";
        }
        
        result = dna.substring(startIndex, stopIndex+3);
        
        if(result.length() % 3 != 0){
            return "not devisibel by three";
        }
        
        return result;
    }
    public void testSimpleGene(){
        String dna = "ATATAGAGGAATGTAGGGTATAGGGTAATAGAG";
        System.out.println(findSimpleGene(dna,"ATG","TAA"));
        
         dna = "ATTGTATAA";
        System.out.println(findSimpleGene(dna,"ATG","TAA"));
        
         dna = "ATATAGAGGAATGTAGGGTATAGGGTAATAGAG";
        System.out.println(findSimpleGene(dna,"ATG","TAA"));
        
         dna = "ATATAGAGGAATGTAGGTATAGGGTAATAGAG";
        System.out.println(findSimpleGene(dna,"ATG","TAA"));
        
         dna = "ATATAGAGGAATGTAGGGTATAGGGTATAGAG";
        System.out.println(findSimpleGene(dna,"ATG","TAA"));
        
    }
}
