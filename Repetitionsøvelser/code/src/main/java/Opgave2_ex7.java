public class Opgave2_ex7 
{
    public static void main(String[] args) 
    {
        printNestedForLoops(5);
    }

    private static void printNestedForLoops(int num)
    {
        for (int i = 1; i <= num; i++) 
        {
            for (int j = 0; j < num-i; j++) 
            {
                System.out.print(" ");
            }
            System.out.print(i);
            System.out.println();
        }
    }
}