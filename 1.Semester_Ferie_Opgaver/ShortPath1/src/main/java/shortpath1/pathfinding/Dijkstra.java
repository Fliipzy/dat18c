package shortpath1.pathfinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Dijkstra 
{
    private List<Vertex> visited;
    private List<Vertex> unvisited;

    public Dijkstra() 
    {
    }

    public void calculateShortestPaths(Vertex from)
    {
        visited = new ArrayList<Vertex>();
        unvisited = new ArrayList<Vertex>();

        from.setTCost(0);
        unvisited.add(from);

        while (!unvisited.isEmpty()) 
        {
            Vertex cheapest = getCheapestVertex();
            unvisited.remove(cheapest);
            for (Entry<Vertex, Integer> entry : cheapest.getNeighbours().entrySet()) {
                Vertex key = entry.getKey();
                Integer value = entry.getValue();
                if (!visited.contains(key) && cheapest.getTCost() + value < key.getTCost()) {
                    key.setTCost(cheapest.getTCost() + value);
                    key.setParent(cheapest);
                    unvisited.add(key);
                }
            }
        }
    }

    private Vertex getCheapestVertex()
    {
        Vertex cheapestVertex = null;
        int minimumValue = Integer.MAX_VALUE;

        for (Vertex vertex : unvisited) 
        {
            if (vertex.getTCost() < minimumValue) {
                minimumValue = vertex.getTCost();
                cheapestVertex = vertex;
            }
        }
        return cheapestVertex;
    }
}