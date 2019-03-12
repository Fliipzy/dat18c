public class Opgave7 
{
    public static void main(String[] args) 
    {
        int[] numbers = {14, 1, 22, 17, 36, 7, -43, 5};
        System.out.println(countInRange(numbers, 4, 17));
    }

    private static int countInRange(int[] numbers, int min, int max)
    {
        int ticker = 0;

        for (int i = 0; i < numbers.length; i++) 
        {
            if (numbers[i] >= min && numbers[i] <= max) 
            {
                ticker++;    
            }
        }
        return ticker;
    }
}