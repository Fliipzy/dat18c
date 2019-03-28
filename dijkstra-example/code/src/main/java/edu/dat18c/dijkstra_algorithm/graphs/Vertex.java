package edu.dat18c.dijkstra_algorithm.graphs;

import java.util.HashMap;

public class Vertex 
{
    private String name;
    private float tCost;
    private Vertex parent;
    private HashMap<Vertex, Integer> neighbours;

    public Vertex(String name) 
    {
        this.name = name;
        this.tCost = Float.MAX_VALUE;
        neighbours = new HashMap<Vertex, Integer>();
    }

    public String getName() { return name; }

    public HashMap<Vertex, Integer> getNeighbours() { return neighbours; }

    public Vertex getParent() { return parent; }

    public void setParent(Vertex parent) { this.parent = parent; }

    public float getTCost() 
    {
        return tCost;
    }

    public void setTCost(float tCost) 
    {
        this.tCost = tCost;
    }
}