import java.util.Scanner;

public class Opgave5_ex12 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        printAverage(scanner);
    }

    public static void printAverage(Scanner scanner)
    {
        System.out.println("Please enter as many numbers you want, to get the average, type in a negative number i.e \"-1\"");

        System.out.print("Type a number: ");
        int value = parseStringToInt(scanner);
        int numTicker = 1;
        int sum = value;

        while (value >= 0) 
        {
            System.out.print("Type a number: "); 
            value = parseStringToInt(scanner);

            if (value < 0) {
                break;
            }

            sum += value;
            numTicker++;
        }

        System.out.println("Average is " + ((float)sum/numTicker));
    }

    private static int parseStringToInt(Scanner scanner)
    {
        String value;

        while (true) 
        {
            value = scanner.next();
            try 
            {
                int intValue = Integer.parseInt(value);
                return intValue;
            } 
            catch (Exception e) 
            {
                System.out.print("Input must be numerical, try again: ");
            }
        }
    }
}