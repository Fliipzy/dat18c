package edu.dat18c.project_simulation;

/**
 * SimulationOptions
 */
public class SimulationOptions 
{
    private int iterationSleepTime = 0;
    private float stopPercent = 1f;
    private boolean runUpdates = true;
    
    public SimulationOptions() {}

    public SimulationOptions(int iterationSleepTime, float stopPercent, boolean runningUpdates) 
    {
        this.iterationSleepTime = iterationSleepTime;
        this.stopPercent = stopPercent;
        this.runUpdates = runningUpdates;
    }


    public int getIterationSleepTime() 
    {
        return iterationSleepTime;
    }

    public void setIterationSleepTime(int iterationSleepTime)
    {
        this.iterationSleepTime = iterationSleepTime;
    }

    public float getStopPercent() 
    {
        return stopPercent;
    }

    public void setStopPercent(float stopPercent) 
    {
        this.stopPercent = stopPercent;
    }
    
    public boolean getRunUpdates()
    {
        return runUpdates;
    }
    
    public void setRunUpdates(boolean runUpdates) 
    {
        this.runUpdates = runUpdates;
    }
}