package dijkstra.util;

public class Point 
{
    private int x;
    private int y;

    private float travelCost;

    private Point parent;
    private Point[] neighbours;

    private boolean traversable = true;
    private boolean visited;

    public Point(int x, int y) 
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public Point getParent() {
        return parent;
    }
    public void setParent(Point p) {
        parent = p;
    }

    public Point[] getNeighbours() {
        return neighbours;
    }
    public void setNeighbours(Point[] neighbours){
        this.neighbours = neighbours;
    }

    public boolean getTraversability() {
        return traversable;
    }
    public void setTraversability(boolean traversable){
        this.traversable = traversable;
    }

    public boolean getVisited() {
        return visited;
    }
    public void setVisited(boolean visited){
        this.visited = visited;
    }

    public float getTravelCost() {
        return travelCost;
    }
    public void setTravelCost(float travelCost) {
        this.travelCost = travelCost;
    }

    @Override
    public boolean equals(Object other)
    {
        try 
        {
            Point p = (Point)other;
            return this.x == p.x && this.y == p.y ? true : false;    
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public String toString()
    {
        return String.format("(%s, %s)", x, y);
    }
}