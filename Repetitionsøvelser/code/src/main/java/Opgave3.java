public class Opgave3 
{
    public static void main(String[] args) 
    {
        printNumbers(15);
        printNumbers(5);
    }

    public static void printNumbers(int maximumNumber)
    {
        for (int i = 1; i <= maximumNumber; i++) 
        {
            System.out.print("[" + i + "] ");
        }
        System.out.println();
    }
}