public class Opgave4_ex18 
{
    public static void main(String[] args) 
    {
        System.out.println(wordCount(" this is a        sentence"));
    }   
    
    public static int wordCount(String sentence)
    {
        String[] unfilteredWords = sentence.split(" ");
        int ticker = 0;

        for (int i = 0; i < unfilteredWords.length; i++) 
        {
            if (!unfilteredWords[i].equals("")) 
            {
                ticker++;    
            }
        }
        return ticker;
    }
}