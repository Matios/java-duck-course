/**
 * Write a description of Part1 here.
 * 
 * @author (Matios) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class Part1 {
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        int currIndex= dnaStr.indexOf(stopCodon, startIndex+3);
        int count =0;
        while(currIndex != -1){
            int diff = currIndex - startIndex;
            
            if(diff % 3 == 0){
                return currIndex;
            }
            else{
                currIndex = dnaStr.indexOf(stopCodon, currIndex+1);//was +1
            }
        }
       /// System.out.println(count);
        return -1;//dnaStr.length();
    }
    
    public String findGene(String dna,int where){
        
        int startIndex = dna.indexOf("ATG",where);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        //int temp = Math.min(taaIndex,tagIndex);
       // int minIndex = Math.min(temp,tgaIndex);
       int minIndex = 0;
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex= tgaIndex;
        }
        else{
            minIndex = taaIndex;
        }
        if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        if(minIndex == -1){
            return "";
        }
      return dna.substring(startIndex,minIndex+3);  
    }
    
    public void printAllGenes(String dna){
        int startIndex =0;
        int count =0;
        while( true){
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex +
            currentGene.length());
        }
       // System.out.println(count-1);
    }
    
    public void testFindStop(){
        //           01234567890123456789012345
        String dna ="xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna,0,"TAA");
        if(dex != 9)
        System.out .println("Error on 9");
         dex = findStopCodon(dna,9,"TAA");
        if(dex != 21)
        System.out .println("Error on 21");
         dex = findStopCodon(dna,11,"TAA");
        if(dex != -1)
        System.out .println("Error on 26");
        dex = findStopCodon(dna,0,"TAG");
        if(dex != -1)
        System.out .println("Error on 26 TAG");
        
        System.out.println("test finished");
    }
   
    public StorageResource getAllGenes(String dna){
        
        StorageResource geneList = new StorageResource();
        int startIndex=0;
        while(true){
            String currentGene = findGene(dna,startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + 
                         currentGene.length();
        }
        return geneList;
    
    }
    
    // Write a method testProcessGenes. 
    // This method will call your processGenes method on different test cases
    public void testProcessGenes() {
        StorageResource sr = new StorageResource();
        sr.add("gctgctgcc");
        sr.add("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        sr.add("Matios Dinegdia");
        sr.add("ATGCATTGCGGCTCTAA");
        sr.add("ATAACAA");
        processGenes(sr);
        System.out.println("Test finished");
    }
    
     public void testOn(String dna) {
        System.out.println("Testing printAllGenes on " + dna);
        printAllGenes(dna);
        int count=0;
        StorageResource genes = getAllGenes(dna);
        
        for(String g: genes.data()){
            System.out.println(g);
            count++;
        }
        System.out.println(count);
    }
    
    // Write the method cgRatio that has one String parameter dna, and 
    // returns the ratio of C’s and G’s in dna as a fraction of the entire strand of DNA. 
    // For example if the String were “ATGCCATAG” then cgRatio would return 
    // 4/9 or .4444444.
    double cgRatio (String dna) {
        String upperCaseDNA = dna.toUpperCase();
        int count = 0;
        int length = dna.length();
        
        for (int i = 0; i < length; i++) {
            if (upperCaseDNA.charAt(i) == 'G' || upperCaseDNA.charAt(i) == 'C') {
                count++;
            }
        }
        return (double)count / length;
    }
    
    // Write a method countCTG that has one String parameter dna, and 
    // returns the number of times the codon CTG appears in dna.
    int countCTG (String dna) {
        String upperCaseDNA = dna.toUpperCase();
        int count = 0;
        
        for (int i = 0 ; i < upperCaseDNA.length() - 2 ; i++) {
            int char1 = upperCaseDNA.charAt(i);
            int char2 = upperCaseDNA.charAt(i+1);
            int char3 = upperCaseDNA.charAt(i+2);
            
            if (char1 == 'C' && char2 == 'T' && char3 == 'G') {
                count++;
            }
        }
        return count;
    }
    
    // Write the void method processGenes that has one parameter sr, 
    // which is a StorageResource of strings. This method processes 
    // all the strings in sr to find out information about them. Specifically, 
    // it should:
    //   - print all the Strings in sr that are longer than 9 characters
    //   - print the number of Strings in sr that are longer than 9 characters
    //   - print the Strings in sr whose C-G-ratio is higher than 0.35
    //   - print the number of strings in sr whose C-G-ratio is higher than 0.35
    //   - print the length of the longest gene in sr
    void processGenes(StorageResource sr) {
        int countOver9 = 0, countCG = 0;
        int largest = 0;
        for (String s : sr.data()) {
            if (s.length() > 9) {
                System.out.println(s);
                countOver9++;
            }
            if (cgRatio(s) > 0.35) {
                System.out.println(s);
                countCG++;
            }
            if (s.length() > largest) {
                largest = s.length();
            }
        }
        System.out.println(countOver9);
        System.out.println(countCG);
        System.out.println(largest);
    }
    
    // We get a file dilogBox and select files. For
    // every file, print out the file name and print
    // out each word inside that file.
    public void fileTesting() {
        DirectoryResource dr = new DirectoryResource();
        StorageResource sr = new StorageResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            System.out.println(f.getName());
            
            for (String s : fr.words()) {
                sr.add(s);
                System.out.println(s);
            }
        }
        System.out.println(sr.size());
        
    }
    public void myTest(){
        
    }
    
    // getAllGenes(String dna)
    public void testFileResourcce(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        StorageResource storage = getAllGenes(dna);
        processGenes(storage);
    }
}
