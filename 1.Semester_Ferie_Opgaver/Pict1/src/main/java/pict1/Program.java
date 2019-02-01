package pict1;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.imageio.ImageIO;

import pict1.utilities.ImageAdjustment;

public final class Program {

    private static final String PATH = "pict1\\data\\";

    public static void main(String[] args) {

        File file = new File(PATH + "Rubber-duck.jpg");
        Scanner scanner = new Scanner(System.in);
        Color c = null;
        String choice = null;

        System.out.println("What color should the duck be tinted?");

        while (true) {

            System.out.print("Input color name > ");
            choice = scanner.next();
            
            try 
            {
                //using reflection to get color field object and casting it to color
                c = (Color)Class.forName("java.awt.Color").getField(choice).get(null);
            } 
            catch (Exception e) 
            {
                
            }
            

            if (c == null) 
            {
                System.out.println("I don't know that color, sorry! try again..");
                continue;
            }
            scanner.close();
            break;
        }

        try 
        {
            System.out.println("Trying to load the rubber duck!");
            BufferedImage image = ImageIO.read(file);
            System.out.println("Succeeded!");

            System.out.println("Trying to tint the image " + choice + "!");
            image = ImageAdjustment.getTintedImage(image, c);
            System.out.println("I succeeded again!!");

            ImageIO.write(image, "jpg", new File(PATH + "Rubber-duck-edit.jpg"));

            Desktop.getDesktop().open(new File(PATH + "Rubber-duck-edit.jpg"));
            
        } 
        catch (Exception e) 
        {
            System.out.println("ERROR MESSAGE: Something clearly went wrong...");
        }
    }
}
