package edu.dat18c.project_simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Program 
{
    public static void main( String[] args )
    {
        //Create phases
        Phase p1 = new Phase("Communication", 1);
        Phase p2 = new Phase("Planning", 2);
        Phase p3 = new Phase("Modelling", 2);
        Phase p4 = new Phase("Construction", 4);
        Phase p5 = new Phase("Deployment", 3);
        
        //Create list and add phases
        List<Phase> phases = new ArrayList<Phase>();
        phases.addAll(Arrays.asList(p1, p2, p3, p4, p5));

        //Route phases together
        p1.addNextPhase(p2, 1f);
        p2.addNextPhase(p3, 1.8f);
        p2.addNextPhase(p1, 1.2f);
        p3.addNextPhase(p4, 1.8f);
        p3.addNextPhase(p3, 1.2f);
        p4.addNextPhase(p5, 1.8f);
        p4.addNextPhase(p1, 1.2f);

        //Create project
        Project project = new Project("Project Simulation", phases);

        //Set simulation options
        SimulationOptions options = new SimulationOptions();
        options.setIterationSleepTime(10);
        options.setRunUpdates(true);
        options.setStopPercent(0.99f);

        //Simulate project
        ProjectSimulation projectSimulation = new ProjectSimulation(project, options);
        projectSimulation.start();
    }
}
