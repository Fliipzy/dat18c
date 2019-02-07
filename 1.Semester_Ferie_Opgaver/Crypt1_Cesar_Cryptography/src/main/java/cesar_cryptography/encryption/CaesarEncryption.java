package cesar_cryptography.encryption;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class CaesarEncryption 
{
    private static final String SYMBOLS = " !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
    private static Random random = new Random();

    public static Map<Character, Character> getEncryptionCipher()
    {
        Map<Character,Character> cipher = new HashMap<Character,Character>();
        List<Character> charsLeft = new ArrayList<Character>();

        for (char c : SYMBOLS.toCharArray()) {
            charsLeft.add(c);
        }
        
        int rndIndex = 0;
        for (char c : SYMBOLS.toCharArray()) 
        {
            rndIndex = random.nextInt(charsLeft.size());
            cipher.put(c, charsLeft.get(rndIndex));
            charsLeft.remove(rndIndex);
        }

        return cipher;
    }

    public static Map<Character, Character> getDecryptionCipher(Map<Character, Character> encryptionCipher)
    {
        Map<Character, Character> decryptionCipher = new HashMap<Character, Character>();

        for (Entry<Character, Character> entry : encryptionCipher.entrySet()) {
            decryptionCipher.put(entry.getValue(), entry.getKey());
        }

        return decryptionCipher;
    }

    public static String encryptText(String text, Map<Character, Character> encryptCipher)
    {
        String encryptedText = "";

        for (Character c : text.toCharArray()) 
        {
            encryptedText += encryptCipher.get(c);
        }
        return encryptedText + "\n";
    }

    public static String decryptText(String EncryptedText, Map<Character,Character> decryptCipher)
    {
        String decipheredText = "";

        for (Character c : EncryptedText.toCharArray()) 
        {
            decipheredText += decryptCipher.get(c);
        }
        
        return decipheredText + "\n";
    }
}