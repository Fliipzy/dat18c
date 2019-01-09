package bee_maze.util;

public class Point 
{
    private int x;
    private int y;

    public Point(int x, int y) 
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }

    @Override
    public boolean equals(Object other)
    {
        try 
        {
            Point otherPoint = (Point)other;
            return (x == otherPoint.x && y == otherPoint.y) ? true : false;    
        } 
        catch (Exception e) 
        {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return String.format("(%s, %s)", x, y);
    }
}