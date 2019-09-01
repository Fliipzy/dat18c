package edu.dat18c.fitness_exercise;

public class Employee extends Person 
{
    private int salary;
    private int hours;

    public Employee(String name, String cpr, int salary, int hours) 
    {
        super(name, cpr);
        this.salary = salary;
        this.hours = hours;
    }

    public int getSalary() 
    {
        return salary;
    }    

    public int getHours() 
    {
        return hours;
    }
}