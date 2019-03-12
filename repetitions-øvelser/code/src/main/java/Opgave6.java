import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Opgave6 
{    
    public static void main(String[] args) 
    {
        try 
        {
            Scanner scanner = new Scanner(new File("tekst/Opgave6Tekst.txt"));
            collapseSpaces(scanner);
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File was not found!");
        }
    }

    public static void collapseSpaces(Scanner scanner)
    {
        ArrayList<String> words = new ArrayList<String>();

        scanner.useDelimiter(" ");

        while (scanner.hasNext()) 
        {
            String word = scanner.next();

            if (!word.equals("")) 
            {
                words.add(word);
            }
        }

        for (String word : words) 
        {
            System.out.print(word + " ");    
        }       
    }
}