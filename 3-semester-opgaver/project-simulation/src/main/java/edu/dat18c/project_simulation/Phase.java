package edu.dat18c.project_simulation;

import java.util.HashMap;
import java.util.Map;

/**
 * Phase
 */
public class Phase 
{
    private String name;
    private double currentPercent;
    private int iterationCost;
    private Map<Phase, Float> nextPhases = new HashMap<Phase, Float>();
    
    public Phase(String name, int iterationCost) 
    {
        this.name = name;
        this.iterationCost = iterationCost;
    }

    public void addNextPhase(Phase phase, Float percent)
    {
        this.nextPhases.put(phase, percent);
    }

    public Map<Phase, Float> getNextPhases() 
    {
        return nextPhases;
    }

    public String getName() 
    {
        return name;
    }

    public int getIterationCost() 
    {
        return iterationCost;
    }

    public double getCurrentPercent() 
    {
        return currentPercent;
    }

    public void setCurrentPercent(double currentPercent) 
    {
        this.currentPercent = currentPercent;
    }
}