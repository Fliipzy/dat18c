package chess_pathfinding.Pathfinding;

import chess_pathfinding.util.Position;

public class Vertex 
{
    private Position pos;
    private float gCost;
    private float tCost;
    private float totalCost;
    
    private Vertex parent;
    private Vertex[] neighbours;

    public Vertex(Position pos) 
    {
        this.pos = pos;
    }

    public Position getPos() 
    {
        return pos;
    }

    public float getGCost() 
    {
        return gCost;
    }
    public void setGCost(float gCost) 
    {
        this.gCost = gCost;
    }

    public float getTCost() 
    {
        return tCost;
    }
    public void setTCost(float tCost) 
    {
        this.tCost = tCost;
    }

    public float getTotalCost() 
    {
        return gCost + tCost;
    }
}