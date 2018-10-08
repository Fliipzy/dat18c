package Chapter7_Arrays;

public class Program 
{
    public static void main(String[] args) 
    {
        int[] numbers1 = new int[] { 74, 85, 102, 99, 101, 85, 56};

        System.out.println(lastIndexOf(numbers1, 85));

        int[] numbers2 = new int[] { 36, 12 ,25 ,19 ,46 ,31 ,22 };

        System.out.println(range(numbers2));
    }

    //Exercise 1

    public static int lastIndexOf(int[] numbers, int value)
    {
        int index = -1;

        for (int i = 0; i < numbers.length; i++) 
        {
            if (numbers[i] == value) 
            {
                index = i;    
            }
        }
        return index;
    }

    //Exercise 2

    public static int range(int[] numbers)
    {
        int min = getSmallestValue(numbers);
        int max = getBiggestValue(numbers);

        return max - min + 1;
    }

    public static int getSmallestValue(int[] numbers)
    {
        int shortestIndex = 0;

        for (int i = 0; i < numbers.length; i++) 
        {
            if (numbers[shortestIndex] > numbers[i]) 
            {
                shortestIndex = i;
            }
        }
        return numbers[shortestIndex];
    }

    public static int getBiggestValue(int[] numbers)
    {
        int biggestIndex = 0;

        for (int i = 0; i < numbers.length; i++) 
        {
            if (numbers[biggestIndex] < numbers[i]) 
            {
                biggestIndex = i;
            }
        }
        return numbers[biggestIndex];
    }

}