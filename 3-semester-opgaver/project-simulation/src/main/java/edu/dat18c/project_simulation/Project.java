package edu.dat18c.project_simulation;

import java.util.List;

/**
 * Project
 */
public class Project 
{
    private String name;
    private List<Phase> phases;
    
    public Project(String name, List<Phase> phases) 
    {
        this.name = name;
        this.phases = phases;
    }

    public String getName() 
    {
        return name;
    }

    public List<Phase> getPhases() 
    {
        return phases;
    }
}