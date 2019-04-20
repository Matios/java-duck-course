
/**
 * Write a description of ImageInversion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class ImageInversion {
    public ImageResource makeInversion(ImageResource image){
        ImageResource outImage = new ImageResource(image.getWidth(), image.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = image.getPixel(pixel.getX(), pixel.getY());
            int invertedRed = 255 - inPixel.getRed();
            int invertedBlue = 255 - inPixel.getBlue();
            int invertedGreen = 255 - inPixel.getGreen();
            pixel.setRed(invertedRed);
            pixel.setBlue(invertedBlue);
            pixel.setGreen(invertedGreen);
        }
        return outImage;  
    }
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            String fname = inImage.getFileName();
            String newName = "inverted-" + fname;
            ImageResource inverted = makeInversion(inImage);
            inverted.setFileName(newName);
            inverted.draw();
            inverted.save();
        }
    }
}
