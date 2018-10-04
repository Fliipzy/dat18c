public class Program 
{
    public static void main(String[] args) 
    {
        NumberInterval myInterval = new NumberInterval(-2, 2);

        //To test if a given number interval is contained inside the original (min - max) interval, 
        //we are going to call the contains method from our NumberInterval objects.
        //This method returns a boolean type, so on the left side of the method call, we have to
        //make sure we have a boolean variable that can hold our data.
        //"boolean myBool =" is our boolean variable. myBool is going to hold the data returned from "myInterval.contains(0.0, 1.2);"

        boolean myBool = myInterval.contains(0.0, 1.2);

        boolean myOtherBool = myInterval.contains(0.0, 2.1);

        //Now let's print out the results

        System.out.println(myBool); // returns true because (0, 1.2) is inside the number interval (-2,2)
        System.out.println(myOtherBool); // returns false because (0, 2.1) is not inside the number interval (-2,2)


        //Let's test the equals method now.
        //This method is located inside the NumberInterval class and has a return type of boolean.

        //First we want to declare and initialize three new NumberInterval objects
        NumberInterval anotherInterval = new NumberInterval(-10, 120);
        NumberInterval yetAnotherInterval = new NumberInterval(-10000, 20000);
        NumberInterval lastInterval = new NumberInterval(-10, 120);

        //Now we create two boolean variables that will hold the returned boolean values from equals

        boolean isTheSame = anotherInterval.equals(yetAnotherInterval); //This method compares 'anotherInterval' to 'yetAnotherInterval'
        boolean isThisTheSame = lastInterval.equals(anotherInterval); //This compares 'lastInterval' to 'anotherInterval'

        //Let's print out the booleans 
        System.out.println(isTheSame); 
        System.out.println(isThisTheSame); 
    }
}