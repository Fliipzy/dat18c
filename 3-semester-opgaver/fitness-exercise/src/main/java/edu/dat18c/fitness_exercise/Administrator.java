package edu.dat18c.fitness_exercise;

public class Administrator extends Employee
{
    private int vacation;
    
    public Administrator(String name, String cpr, int salary, int hours, int vacation) 
    {
        super(name, cpr, salary, hours);
        this.vacation = vacation;
    }

    public int getVacation() 
    {
        return vacation;
    }
}