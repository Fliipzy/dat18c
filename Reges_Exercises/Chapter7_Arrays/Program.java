package Chapter7_Arrays;

import java.util.Arrays;
import java.util.HashMap;

public class Program 
{
    public static void main(String[] args) 
    {
        //Exercise 1 test
        int[] numbers1 = new int[] { 74, 85, 102, 99, 101, 85, 56 };

        System.out.println(lastIndexOf(numbers1, 85));

        //Exercise 2 test
        int[] numbers2 = new int[] { 36, 12 ,25 ,19 ,46 ,31 ,22 };

        System.out.println(range(numbers2));

        //Exercise 3 test
        int[] numbers3 = new int[] { 14, 1, 22, 17, 36, 7, -37, 5 };

        System.out.println(countInRange(numbers3, 4, 17));

        //Exercise 4 test
        double[] numbers4 = new double[] { 16.1, 12.3, 22.2, 14.4 };
        double[] numbers5 = new double[] { 1.5, 4.3, 7.0, 19.5, 25.1, 46.2 };

        System.out.println(isSorted(numbers4));
        System.out.println(isSorted(numbers5));

        //Exercise 5 test
        int[] numbers6 = new int[] { 27, 15, 15, 11, 27 };
        
        System.out.println(mode(numbers6));

        //Exercise 6 test
        int[] numbers7 = new int[] { 1, -2, 4, -4, 9, -6, 16, -8, 25, -10 };

        System.out.println(stdev(numbers7));

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

    //Method that returns the smallest value of a given int array 

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

    //Method that returns the biggest value of a given int array

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


    //Exercise 3

    public static int countInRange(int[] numbers, int min, int max)
    {
        int count = 0;

        for (int i = 0; i < numbers.length; i++) 
        {
            if (numbers[i] >=  min && numbers[i] <= max) 
            {
                count++;    
            }
        }  
        return count;   
        
    }

    //Exercise 4

    public static boolean isSorted(double[] numbers)
    {
        for (int i = 1; i < numbers.length; i++) 
        {
            if (numbers[i] < numbers[i-1]) 
            {
                return false;
            }    
        }
        return true;
    }

    //Exercise 5

    public static int mode(int[] numbers)
    {
        HashMap<Integer, Integer> occurrences = new HashMap<Integer, Integer>();

        for (int i = 0; i < numbers.length; i++) 
        {
            if (occurrences.containsKey(numbers[i])) 
            {
                int oldValue = occurrences.get(numbers[i]);
                occurrences.replace(numbers[i], oldValue, oldValue + 1);
            } 
            else 
            {
                occurrences.put(numbers[i], 0);
            }
        }

        int mostOccurencesIndex = 0;

        for (int i = 0; i < numbers.length; i++) 
        {
            if (occurrences.get(numbers[mostOccurencesIndex]) <= occurrences.get(numbers[i])) 
            {
                if (numbers[mostOccurencesIndex] > numbers[i]) 
                {
                    mostOccurencesIndex = i;
                }
            }
        }

        return numbers[mostOccurencesIndex];
    }

    //Exercise 6

    public static double stdev(int[] numbers)
    {
        int sum = 0;

        for (int i = 0; i < numbers.length; i++) 
        {
            sum += numbers[i];
        }

        double average = (double)sum / numbers.length;

        double sumOfSquares = 0;

        for (int i = 0; i < numbers.length; i++) 
        {
            sumOfSquares += Math.pow(numbers[i] - average, 2);
        }

        return Math.sqrt(sumOfSquares / (numbers.length-1));
    } 

}