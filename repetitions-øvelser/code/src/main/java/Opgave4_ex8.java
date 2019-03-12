import java.util.Arrays;
import java.util.Scanner;

public class Opgave4_ex8
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        smallestLargest(scanner);
    }

    public static void smallestLargest(Scanner scanner)
    {
        System.out.println("How many numbers do you want to enter?");
        String value = scanner.next();

        getNumericalValue(value, scanner);

        int[] numbers = new int[Integer.parseInt(value)];

        for (int i = 1; i <= numbers.length; i++) 
        {
            System.out.print("Number " + i + ": ");
            String number = scanner.next();
            numbers[i-1] = getNumericalValue(number, scanner);
        }

        Arrays.sort(numbers);

        System.out.println("Smallest = " + numbers[0]);
        System.out.println("Highest = " + numbers[numbers.length-1]);
    }

    private static int getNumericalValue(String value, Scanner scanner)
    {
        while (!canParseStringToInt(value)) 
        {
            System.out.print("Input must be numerical, try again: ");
            value = scanner.next();
        }

        return Integer.parseInt(value);
    }

    private static boolean canParseStringToInt(String value)
    {
        for (int i = 0; i < value.length(); i++) 
        {
            if (!Character.isDigit(value.charAt(i))) 
            {
                return false;
            }
        }
        return true;
    }
}