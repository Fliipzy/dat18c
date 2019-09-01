package edu.dat18c.fitness_exercise;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Program 
{
    public static void main(String[] args)
    {
        PersonFileReader personFileReader = new PersonFileReader(new File("persons.txt"));
        List<Person> persons = personFileReader.readFile();

        /*List<Person> persons = new ArrayList<Person>()
        {
            {
                add(new Member("Fred", "110193-8888", false));
                add(new Member("Nicklas", "140593-1234", false));
                add(new Member("Marcus", "221193-8080", true));
                add(new Administrator("Jarl", "130366-1123", 36000, 32, 5));
                add(new Administrator("Asger", "021055-2321", 36000, 32, 5));
                add(new Instructor("Larman", "180758-1702", 20050, 15, "Object oriented strength training"));
                add(new Instructor("Chad", "081176-1690", 19500, 10, "Spring gymnastics"));
            }
        };*/


        StringBuilder sb = new StringBuilder();

        //FITNESS EMPLOYEES
        sb.append("FITNESS EMPLOYEES\n\n");
        sb.append(String.format("%s\t %s\t\t %s\t %s\t %s\t %s\n", "Name", "Cpr", "Hours", "Salary", "Vacation", "Course"));
        sb.append("*".repeat(90) + System.lineSeparator());

        for (Person p : persons) 
        {
            if (p instanceof Employee) 
            {
                if (p instanceof Administrator) 
                {
                    Administrator a = (Administrator)p;
                    sb.append(String.format("%s\t %s\t %s\t %s\t %s\n", a.getName(), a.getCpr(), a.getHours(), a.getSalary(), a.getVacation()));
                    continue;
                }
                else if (p instanceof Instructor)
                {
                    Instructor i = (Instructor)p;
                    sb.append(String.format("%s\t %s\t %s\t %s\t %s\t\t %s\n", i.getName(), i.getCpr(), i.getHours(), i.getSalary(), "", i.getCourse()));
                    continue;
                }

                Employee e = (Employee)p;
                sb.append(String.format("%s\t %s\t %s\t %s\n", e.getName(), e.getCpr(), e.getHours(), e.getSalary()));
            }
        }
        sb.append("\n" + "=".repeat(90) + "\n\n");

        //FITNESS MEMBERS
        sb.append("FITNESS MEMBERS\n\n");
        sb.append(String.format("%s\t %s\t\t %s\t %s\n", "Name", "Cpr", "Membertype", "Fee"));
        sb.append("*".repeat(90) + System.lineSeparator());

        for (Person p : persons) 
        {
            if (p instanceof Member) 
            {
                Member m = (Member)p;
                sb.append(String.format("%s\t %s\t %s\t\t %s\n", m.getName(), m.getCpr(), m.getMembershipType(), m.getMonthlyFee()));  
            }    
        }
        sb.append("\n" + "=".repeat(90) + "\n\n");

        //FITNESS EMLOYEES & MEMBERS
        sb.append("FITNESS EMPLOYEES & MEMBERS\n\n");
        sb.append(String.format("%s\t %s\n", "Name", "Cpr"));
        sb.append("*".repeat(90) + System.lineSeparator());

        for (Person p : persons) 
        {
            sb.append(String.format("%s\t %s\n", p.getName(), p.getCpr()));
        }
        sb.append("\n" + "=".repeat(90) + "\n\n");

        System.out.println(sb.toString());
        
    }
}
