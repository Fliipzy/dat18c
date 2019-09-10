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
        int iterations = 0;

        project.getStartPhase().setCurrentPercent(1.0);

        while (project.getFinalPhase().getCurrentPercent() < options.getStopPercent())
        {
            //print status
            for (Phase p : project.getPhases()) 
            {
                System.out.print(p.getName() + ": " + p.getCurrentPercent() + ", ");
            }
            System.out.println();

            if (iterations == 10) 
            {
                break;    
            }
            for (Phase p : (Phase[])project.getPhases().stream().filter(o -> o.getCurrentPercent() > 0).toArray(Phase[]::new)) 
            {
                if (!p.equals(project.getFinalPhase())) 
                {
                    if (phaseCompletions.get(p).equals(p.getIterationCost())) 
                    {
                        for (Map.Entry<Phase, Double> link : p.getNextPhases().entrySet()) 
                        {
                            double nextPhasePercent = link.getKey().getCurrentPercent(); 
                            link.getKey().setCurrentPercent(nextPhasePercent + (p.getCurrentPercent() * link.getValue()));
                        }    
                        phaseCompletions.replace(p, 0);
                        p.setCurrentPercent(0.0);
                        continue;
                    }
                    phaseCompletions.replace(p, phaseCompletions.get(p) + 1); 
                }
            }
            try 
            {
                Thread.sleep(options.getIterationSleepTime());
            } 
            catch (Exception e) 
            {
                //TODO: handle exception
            }
            iterations++;
        }

        //print status
        for (Phase p : project.getPhases()) 
        {
            System.out.print(p.getName() + ": " + p.getCurrentPercent() + ", ");
        }
        System.out.println();
    }

    private int getEstimatedIterations()
    {
        List<String> visited = new ArrayList<String>();
        int iterations = 0;
        for (Phase p : project.getPhases()) 
        {
            visited.add(Integer.toString(p.hashCode()));
            for (Map.Entry<Phase, Double> entry : p.getNextPhases().entrySet()) 
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