package shortpath3.utilities.geometry;

public class Point2D 
{
    private float x;
    private float y;
    
    public Point2D(float x, float y) 
    {
        this.x = x;
        this.y = y;
    }

    public float getX() 
    {
        return x;
    }

    public void setX(float x) 
    {
        this.x = x;
    }

    public float getY() 
    {
        return y;
    }

    public void setY(float y) 
    {
        this.y = y;
    }

    @Override
    public String toString()
    {
        return String.format("(%s, %s)", x, y);
    }

    @Override 
    public boolean equals(Object obj)
    {
        try 
        {
            Point2D other = (Point2D)obj;
            if (other.getX() == this.x && other.getY() == this.y) 
            {
                return true;
            }    
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return false;
    }
}