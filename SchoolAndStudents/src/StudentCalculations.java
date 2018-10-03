import java.util.Random;

public class StudentCalculations 
{
    //Here we declare and initialize an object of type Random
    //It's static because we can NOT use non-static objects and variables in static methods.
    private static Random random = new Random();


    public static double calculateBMI(Student student)
    {
        //returns Weight / Height^2
        return student.getWeight() / ( student.getHeight() * student.getWeight() );
    }

    public static void measureStudent(Student student)
    {
        //Uses the random class to randomly get a weight and height
        double newWeight = random.nextDouble()*100;
        double newHeight = random.nextDouble()*100;

        //Uses the method we created in student class to set the height and weight to the newly random generated ones
        student.setWeight(newWeight);
        student.setHeight(newHeight);
    }

    public static int countStudents(Student[] arrayOfStudents)
    {
        return arrayOfStudents.length;
    }
}
