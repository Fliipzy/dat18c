package shortpath1;

import java.util.ArrayList;
import java.util.Arrays;

import shortpath1.pathfinding.Dijkstra;
import shortpath1.pathfinding.Graph;
import shortpath1.pathfinding.Vertex;

public final class Program 
{
    public static void main(String[] args) 
    {
        Vertex vertA = new Vertex("A");
        Vertex vertB = new Vertex("B");
        Vertex vertC = new Vertex("C");
        Vertex vertD = new Vertex("D");

        Vertex vertE = new Vertex("E");
        Vertex vertF = new Vertex("F");
        Vertex vertG = new Vertex("G");
        Vertex vertH = new Vertex("H");

        Vertex vertI = new Vertex("I");
        Vertex vertJ = new Vertex("J");
        Vertex vertK = new Vertex("K");
        Vertex vertL = new Vertex("L");

        Vertex vertM = new Vertex("M");
        Vertex vertN = new Vertex("N");
        Vertex vertO = new Vertex("O");
        Vertex vertP = new Vertex("P");

        vertA.addNeighbour(vertB, 1);
        vertA.addNeighbour(vertE, 1);

        vertB.addNeighbour(vertA, 1);
        vertB.addNeighbour(vertF, 2);
        vertB.addNeighbour(vertC, 2);

        vertC.addNeighbour(vertB, 2);
        vertC.addNeighbour(vertG, 2);
        vertC.addNeighbour(vertD, 1);

        vertD.addNeighbour(vertC, 1);
        vertD.addNeighbour(vertH, 1);

        vertE.addNeighbour(vertA, 1);
        vertE.addNeighbour(vertF, 1);
        vertE.addNeighbour(vertI, 2);

        vertF.addNeighbour(vertE, 1);
        vertF.addNeighbour(vertB, 2);
        vertF.addNeighbour(vertG, 2);
        vertF.addNeighbour(vertJ, 1);

        vertG.addNeighbour(vertF, 2);
        vertG.addNeighbour(vertC, 2);
        vertG.addNeighbour(vertH, 3);
        vertG.addNeighbour(vertK, 1);

        vertH.addNeighbour(vertG, 3);
        vertH.addNeighbour(vertD, 1);
        vertH.addNeighbour(vertL, 2);

        vertI.addNeighbour(vertE, 2);
        vertI.addNeighbour(vertJ, 2);
        vertI.addNeighbour(vertM, 1);

        vertJ.addNeighbour(vertI, 2);
        vertJ.addNeighbour(vertF, 1);
        vertJ.addNeighbour(vertK, 2);
        vertJ.addNeighbour(vertN, 2);

        vertK.addNeighbour(vertJ, 2);
        vertK.addNeighbour(vertG, 1);
        vertK.addNeighbour(vertL, 3);
        vertK.addNeighbour(vertO, 2);

        vertL.addNeighbour(vertK, 3);
        vertL.addNeighbour(vertH, 2);
        vertL.addNeighbour(vertP, 2);

        vertM.addNeighbour(vertI, 1);
        vertM.addNeighbour(vertN, 1);

        vertN.addNeighbour(vertM, 1);
        vertN.addNeighbour(vertJ, 2);
        vertN.addNeighbour(vertO, 2);

        vertO.addNeighbour(vertN, 2);
        vertO.addNeighbour(vertK, 2);
        vertO.addNeighbour(vertP, 2);

        vertP.addNeighbour(vertO, 2);
        vertP.addNeighbour(vertL, 2);

        Graph graph = new Graph(Arrays.asList(vertA, vertB, vertC, vertD, vertE, vertF, vertG, vertH, vertI, vertJ, vertK, vertL, vertM, vertN, vertO, vertP));

        Dijkstra dijkstra = new Dijkstra();

        System.out.println("Starting dijkstra algorithm..");
        Long startTime = System.nanoTime();
        dijkstra.calculateShortestPaths(vertL);
        Long elapsedTime = System.nanoTime() - startTime;

        System.out.println(String.format("Finished! It took %s milliseconds!", (float)elapsedTime / 1_000_000));

        System.out.println("\n" +
            "So how many minutes will it take for the soldiers to reach all their defensive positions?\n\n" +
            "There are 7 defensive posts, so we take it that there's also 7 soldiers.\n" +
            "Which means the total time it will take is equal to the sum of all the fastest paths\n" +
            "(from the barrack to the individual defensive position).\n");

        int totalTime = timeFromPath(vertA) + timeFromPath(vertB) + timeFromPath(vertC) + timeFromPath(vertD) +
                        timeFromPath(vertE) + timeFromPath(vertI) + timeFromPath(vertM);

        System.out.println("\n" +
            "The total time it will take is " + totalTime + " minutes!");
    }

    private static int timeFromPath(Vertex from)
    {
        int totalTime = 0;
        String msg = from.getName();
        Vertex parent = from.getParent();
        totalTime += from.getNeighbours().get(parent);

        while (parent != null) {
            msg += " <- " + parent.getName();
            if (parent.getParent() != null) {
                totalTime += parent.getNeighbours().get(parent.getParent());
            }
            parent = parent.getParent();
        }

        System.out.println(msg + " = " + totalTime + " minutes!");
        return totalTime;
    }
}
