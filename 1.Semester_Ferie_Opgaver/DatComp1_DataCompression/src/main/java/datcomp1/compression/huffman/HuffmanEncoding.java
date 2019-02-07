package datcomp1.compression.huffman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HuffmanEncoding
{
    private Map<Character, Integer> charFrequency;
    private Map<Character, String> binaryTable;

    public HuffmanEncoding(File textfile) 
    {
        countCharFrequency(textfile);
    }

    public Map<Character, Integer> getCharFrequency() 
    {
        return charFrequency;
    }

    public Map<Character, String> getBinaryTable()
    {
        binaryTable = new HashMap<Character, String>();

        ArrayList<HuffmanNode> openList = new ArrayList<HuffmanNode>();
        ArrayList<HuffmanNode> closedList = new ArrayList<HuffmanNode>();

        for (Entry<Character, Integer> entry : charFrequency.entrySet()) 
        {
            openList.add(new HuffmanNode(entry.getValue(), entry.getKey()));
        }

        while (openList.size() > 1) 
        {
            openList.sort((n1,n2) -> n1.compareTo(n2));

            HuffmanNode lowest = openList.get(0);
            HuffmanNode secondLowest = openList.get(1);
            HuffmanNode newCombined = new HuffmanNode(lowest.getFrequency() + secondLowest.getFrequency());

            newCombined.setRight(lowest);
            newCombined.setLeft(secondLowest);
            lowest.setRoot(newCombined);
            secondLowest.setRoot(newCombined);

            openList.add(newCombined);
            openList.removeAll(Arrays.asList(lowest, secondLowest));
            closedList.addAll(Arrays.asList(lowest, secondLowest));
        }

        setBinary(openList.get(0));
        return binaryTable;
    }

    private void setBinary(HuffmanNode node)
    {
        if (node.getLeft() == null && node.getLeft() == null) 
        {
            binaryTable.put(node.getCharacter(), node.getBinary().substring(4));
        } 
        else
        {
            node.getLeft().setBinary(node.getBinary() + "0");
            node.getRight().setBinary(node.getBinary() + "1");
            setBinary(node.getLeft());
            setBinary(node.getRight());
        }
    }

    private boolean countCharFrequency(File file) 
    {
        charFrequency = new HashMap<Character, Integer>();
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String nextLine;

            while ((nextLine = reader.readLine()) != null) 
            {
                //Important to add "\n" to the nextline string, otherwise newlines will get lost in encoding
                for (Character c : (nextLine + "\n").toCharArray()) 
                {
                    if (charFrequency.containsKey(c)) 
                    {
                        charFrequency.put(c, charFrequency.get(c) + 1);
                    } 
                    else
                    {
                        charFrequency.put(c, 1);
                    }
                }
            }
            reader.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}