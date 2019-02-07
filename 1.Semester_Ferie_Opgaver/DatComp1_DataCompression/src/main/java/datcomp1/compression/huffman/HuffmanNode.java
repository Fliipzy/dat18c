package datcomp1.compression.huffman;

public class HuffmanNode implements Comparable<HuffmanNode>
{
    private HuffmanNode left, right, root;
    private int frequency;
    private Character c;
    private String binary;

    public HuffmanNode(int frequency) 
    {
        this.frequency = frequency;
    }

    public HuffmanNode(int frequency, Character c) 
    {
        this.frequency = frequency;
        this.c = c;
    }


    public String getBinary() 
    {
        return binary;
    }

    public void setBinary(String binary) 
    {
        this.binary = binary;
    }

    public int getFrequency() 
    {
        return frequency;
    }

    public Character getCharacter() 
    {
        return c;
    }

    public void setCharacter(Character c) 
    {
        this.c = c;
    }

    public HuffmanNode getLeft() 
    {
        return left;
    }

    public void setLeft(HuffmanNode left) 
    {
        this.left = left;
    }

    public HuffmanNode getRight() 
    {
        return right;
    }

    public void setRight(HuffmanNode right) 
    {
        this.right = right;
    }

    public HuffmanNode getRoot() 
    {
        return root;
    }

    public void setRoot(HuffmanNode root) 
    {
        this.root = root;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return (this.frequency - o.getFrequency());
    }
}