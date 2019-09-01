package edu.dat18c.project_simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ProjectSimulation
 */
public class ProjectSimulation 
{
    private Project project;
    private SimulationOptions options;
    private Map<Phase, Integer> phaseCompletions;
    
    public ProjectSimulation(Project project, SimulationOptions options) 
    {
        this.project = project;
        this.options = options;
        initializePhaseCompletions();
    }

    public void start()
    {
        StringBuilder sb = new StringBuilder();
        long iterations = 0;
        double finishedPercent = 0;
        int estimatedIterations = getEstimatedIterations();

        sb.append("=== STARTING " + project.getName() + " ===\n\n");
        sb.append("Estimated finish time: " + (estimatedIterations == -1 ? "NEVER" :  estimatedIterations + " iterations"));

        while (finishedPercent < options.getStopPercent()) 
        {
            for (Phase p : project.getPhases()) 
            {
                if (p.getCurrentPercent() > 0) 
                {
                    //If project is ready to progress to next phase
                    if (phaseCompletions.get(p).equals(p.getIterationCost())) 
                    {
                        //Split resources to all phases
                        for (Map.Entry<Phase, Float> next : p.getNextPhases().entrySet()) 
                        {
                            double nextCurrentPercent = next.getKey().getCurrentPercent();
                            double projectSplitPercent = next.getValue();
                            next.getKey().setCurrentPercent(p.getCurrentPercent() + nextCurrentPercent * projectSplitPercent);
                        }
                        //reset phase iteration completeness
                        phaseCompletions.replace(p, 0);
                    }

                    phaseCompletions.replace(p, phaseCompletions.get(p) + 1);
                }
            }

            //Simulate iteration time
            if (options.getIterationSleepTime() != 0) 
            {
                try 
                {
                    Thread.sleep(options.getIterationSleepTime());
                } 
                catch (InterruptedException e) 
                {
                    e.printStackTrace();
                }
            }
            iterations++;
        }

        System.out.println(sb.toString());
    }

    private int getEstimatedIterations()
    {
        List<String> visited = new ArrayList<String>();
        int iterations = 0;
        for (Phase p : project.getPhases()) 
        {
            visited.add(Integer.toString(p.hashCode()));
            for (Map.Entry<Phase, Float> entry : p.getNextPhases().entrySet()) 
            {
                //Loop exists
                if (visited.contains(entry.getKey().hashCode())); 
                {
                    return -1;    
                }
            }
            iterations += p.getIterationCost();
        }
        return iterations;
    }

    private void initializePhaseCompletions()
    {
        phaseCompletions = new HashMap<Phase, Integer>();
        for (Phase p : project.getPhases()) 
        {
            phaseCompletions.put(p, 0);    
        }
    }
}