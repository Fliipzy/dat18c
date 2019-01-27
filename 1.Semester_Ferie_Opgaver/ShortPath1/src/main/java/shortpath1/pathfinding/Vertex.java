package shortpath1.pathfinding;

import java.util.HashMap;
import java.util.Map;

public class Vertex 
{
    private String name;
    private int tCost = Integer.MAX_VALUE;
    private Map<Vertex, Integer> neighbours;
    private Vertex parent;

    public Vertex(String name) 
    {
        this.name = name;
        neighbours = new HashMap<Vertex, Integer>();
    }
    
    public Vertex getParent() 
    {
        return parent;
    }
    public void setParent(Vertex parent) 
    {
        this.parent = parent;
    }

    public Map<Vertex, Integer> getNeighbours() 
    {
        return neighbours;
    }

    public String getName() 
    {
        return name;
    }

    public int getTCost() 
    {
        return tCost;
    }

    public void setTCost(int tCost) 
    {
        this.tCost = tCost;
    }

    public void addNeighbour(Vertex vertex, int distance)
    {
        neighbours.put(vertex, distance);
    }

    @Override 
    public String toString()
    {
        return name;
    }

}