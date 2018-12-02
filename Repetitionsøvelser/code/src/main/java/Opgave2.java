public class Opgave2 
{
    public static void main(String[] args) 
    {
        printNestedForLoop(7);
    }

    public static void printNestedForLoop(int upToNum)
    {
        for (int i = 1; i <= upToNum; i++) 
        {
            for (int j = 0; j < i; j++) 
            {
                System.out.print(i);
            }
            System.out.println();
        }
    }
}