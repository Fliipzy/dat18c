package cesar_cryptography;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;


import cesar_cryptography.encryption.CaesarEncryption;

public final class Program {
    public static void main(String[] args) {

        System.out.println("Starting encryption of Alice In Wonderland.txt ...\n");

        Map<Character, Character> encryptionCipher = CaesarEncryption.getEncryptionCipher();
        Map<Character, Character> decryptionCipher = CaesarEncryption.getDecryptionCipher(encryptionCipher);

        File inputFile = new File("src\\main\\java\\cesar_cryptography\\data\\AliceInWonderland.txt");
        File outputFile = new File("src\\main\\java\\cesar_cryptography\\data\\AliceInWonderland-Encrypted.txt");

        //Read AliceInWonderland.txt and encrypt it, then write encrypted data to output file
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8);
            String unencryptedLine = null;
            String encryptedLine = null;

            while ((unencryptedLine = reader.readLine()) != null) {
                encryptedLine = CaesarEncryption.encryptText(unencryptedLine, encryptionCipher);
                writer.write(encryptedLine);
            }
            reader.close();
            writer.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        System.out.println("Encryption was successful!\n\nStarting decryption of AliceInWonderland-Decrypted.txt ...\n");

        File decryptedOutputFile = new File("src\\main\\java\\cesar_cryptography\\data\\AliceInWonderland-Decrypted.txt");
        
        //Read encrypted data from AliceInWonderland-Encrypted.txt, then decrypt the data and write it to new output file
        String unencryptedLine = null;
        String encryptedLine = null;
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader(outputFile));
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(decryptedOutputFile), StandardCharsets.UTF_8);

            while ((encryptedLine = reader.readLine()) != null) 
            {
                unencryptedLine = CaesarEncryption.decryptText(encryptedLine, decryptionCipher);
                writer.write(unencryptedLine);
            }
            reader.close();
            writer.close();
        } 
        catch (Exception e) 
        {
            System.out.println("unencrypted: " + unencryptedLine);
            System.out.println("encrypted: " + encryptedLine);
            e.printStackTrace();
        }


        System.out.println("Decryption was successful!\n\n" +
            "Look inside the folder \"src\\main\\java\\cesar_cryptography\\data\" and find AliceInWonderLand-Decrypted.txt");
    }
}
