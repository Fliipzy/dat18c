public class School 
{
    public static void main(String[] args)
    {
        //An array of type Student is declared and initialized
        //The declaration part is the "Student[] students;". This part tells the computer to store the variable name students for later use/initialization.
        //The initialization part is the "= new Students[4];" This part now creates/initializes the data and stores it in computer memory.

        //The brackets in the array "[]" is how we set and retrieve data from our array
        //For example: If I want to display the name of the first student in the array i would write "System.out.println( students[0].getName )"
        //Remember arrays are zero indexed so if we want to retrieve or set a value for the first object we use 0.
        //The numbers used in the brackets [x] are called indexes. You can think of these as adresses of the data stored in the array.

        Student[] students = new Student[4]; 
        //When we initialize the array we have to put in a number in the end brackets to tell the program how many students it needs to make space for. "..new Student[4];".
        //That number is NOT zero indexed and will make space for 4 Students.


        //Now the array needs to be "populated" with data, in our case the data is of type Student
        //To populate the array we write "students[0]" and then "= new Student("Name", "Course");" 
        //That last part should seem familiar as we're just calling the constructor method in Student
        
        students[0] = new Student("Frederik", "DAT18C");
        students[1] = new Student("Marcus", "DAT18C");
        students[2] = new Student("Reynolds", "DAT18C");
        students[3] = new Student("Frederik", "DAT18C");


        //All the students now need to be printed out to console with their name and course
        //We have a method in Student that does exactly that, toString()
        //Instead of writing: "System.out.println(students[x].toString())" for each student, we want to create a foreach loop.

        //A foreach loop can be broken up in parts
        //1. Write the "for" keyword
        //2. Just like the "If" keyword, follow it up by writing "()".
        //3. Inside the parentheses we must first declare the type of data we want to iterate over. In our case it's Student
        //4. Now we have to choose the pointer name that will point/refer to all the Students in the array.
        //5. the pointer name is followed by a ":" colon, sometimes it helps to read this out loud as the word In. The whole line should be read as -
        //   "For each Student stud in students." This is to better understand what we're iterating through. 
        //6. The last part of the foreach loop syntax is the object name of the group of data (Arrays, Lists etc.) we want to iterate over. 
        //   In our case the name of our array object is students, so we write students.
        for (Student stud : students) 
        {
            //This scope of code will now execute "for each" student in students. 4 times in our case.
            //But for each iteration the stud will reference/point to a new Student object in our array.
            //First execution, stud = students[0]. second time, stud = students[1] and so on.

            //You can probably begin to see how foreach loops, in certain cases, can be a very good idea to iterate over data with
            //Instead of using a for loop where you have to tell the loop how many times to run. "for (int i = 0; i < 10; i++)"

            //Check out https://www.youtube.com/watch?v=eByBsjUazII for a much more detailed explanation of foreach loops.

            System.out.println(stud.toString());
            
            //While we're in the foreach loop i also want to measure the weight and height of the students
            //This can be done by calling the static method inside the StudentCalculations class.
            //Notice how when it's static I don't need to create an object of the StudentCalculation class first "StudentCalculations studCalc = new StudentCalculations();" to call the method
            //Just by typing in the class name, we will have access to the method.

            //Using static methods and variables can be a very good idea in some cases and really bad in others.
            //You should consider using the static keyword when creating Service/Utility classes such as Math
            //You won't need to have many instances/objects of Math, you only need the class to perform math.
            //https://www.baeldung.com/java-static, read to get a better understanding of statics.

            StudentCalculations.measureStudent(stud);
        }

        


        //Testing the equals method in Student class
        //The equals method returns true if both name and course are the same

        System.out.println(students[0].equals(students[1])); //Returns false because ("Frederik" =/= "Marcus")
        System.out.println(students[1].equals(students[2])); //Returns false because ("Marcus" =/= "Reynolds")
        System.out.println(students[0].equals(students[3])); //Returns true because ("Frederik" = "Frederik") and ("DAT18C" = "DAT18C")


        //Last thing to do is count how many students we have in our array
        //Lucky for us the StudentCalculations Class has a static method, that does exactly that! :-)

        int numberOfStudents = StudentCalculations.countStudents(students);

        //If you take a look inside the method in the StudentCalculation class you'll see how redundant this actually is
        //All we're doing is returning a variable that all arrays have called length, which is the number of members in the array.

        //You could just as easily have typed "int numberOfStudents = students.length;"
        //But the assignment said to create a method.

        System.out.println(numberOfStudents);
    }
}
