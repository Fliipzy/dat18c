package datcomp1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import datcomp1.compression.huffman.HuffmanEncoding;

public final class Program 
{
    public static void main(String[] args) 
    {
        
        File inputFile = new File("datcomp1/data/AliceInWonderland.txt"); 
        File outputFile = new File("datcomp1/data/AliceInWonderland-Encoded.txt");
        File reconstructedFile = new File("datcomp1/data/AliceInWonderland-Reconstructed.txt");

        HuffmanEncoding huffEncoding = new HuffmanEncoding(inputFile);
          
        System.out.print("Encoding text file...");
          
        Map<Character, String> binaryMap = huffEncoding.getBinaryTable();
          
        System.out.print(" done!\n");

        try 
        {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.ISO_8859_1));
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));   
            String newline;
            String binaryline = "";

            while ((newline = reader.readLine()) != null) 
            {
                for (char c : newline.toCharArray()) 
                {
                    binaryline += binaryMap.get(c);

                    if (binaryline.length() >= 8)
                    {
                        String toWrite = binaryline.substring(0, 8);
                        writer.write(Integer.parseInt(toWrite, 2));
                        binaryline = binaryline.substring(8);
                    }
                }
            }
            int padding = 8 - binaryline.length();
            System.out.println(padding);
            System.out.println(binaryline);

            writer.write(padding);

            writer.close();
            reader.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

    }
}
