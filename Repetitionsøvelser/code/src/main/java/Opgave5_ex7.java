import java.util.Random;
import java.util.Scanner;

public class Opgave5_ex7
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        diceSum(scanner);
    }

    public static void diceSum(Scanner scanner)
    {
        System.out.print("Desired dice sum, in the range from 2-12: ");
        int sum = getNumericalValue(scanner);

        Random ran1 = new Random();
        Random ran2 = new Random();

        int roll1;
        int roll2; 

        while (true) 
        {
            roll1 = ran1.nextInt(6)+1;
            roll2 = ran2.nextInt(6)+1;

            System.out.println(roll1 + " and " + roll2 + " = " + (roll1+roll2) );

            if (roll1 + roll2 == sum) 
            {
                break;    
            }
        }
    }

    private static int getNumericalValue(Scanner scanner)
    {
        String value = scanner.next();

        while (true) {
            
            while (!isNumerical(value)) 
            {
                System.out.print("Input must be numerical, try again: ");
                value = scanner.next();
            }
    
            int parsedInt = Integer.parseInt(value);
    
            if (isInRange(parsedInt, 2, 12)) 
            {
                return parsedInt;
            } 
            else
            {
                System.out.print("Input must be in the range of 2-12, try again: ");
                value = scanner.next();
            }
        }
    }

    private static boolean isInRange(int value, int min, int max)
    {
        if (value < min || value > max) 
        {
            return false;
        }

        return true;
    }

    private static boolean isNumerical(String value)
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