package edu.dat18c.fitness_exercise;

/**
 * Instructor
 */
public class Instructor extends Employee 
{
    private String course;

    public Instructor(String name, String cpr, int salary, int hours, String course) 
    {
        super(name, cpr, salary, hours);
        this.course = course;
    }

    public String getCourse() 
    {
        return course;
    }

}