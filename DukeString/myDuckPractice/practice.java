
/**
 * Write a description of practice here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class practice {
    public String findGene(String dna){
        //Start codon is "ATG"
        //Stop codon is "TAA";
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return "no ATG";
        }
        int stopIndex = dna.indexOf("ATT",startIndex+3);
        if(stopIndex == -1){
            return "no TAA";
        }
        result = dna.substring(startIndex, stopIndex+3);
        return result;
    }
    public void test(){
        String dna= "ATGTTTATAATAGTAATTAT";
        String result = findGene(dna);
        System.out.println(result);
    }

}
