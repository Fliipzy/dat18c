package shortpath3.utilities.geography;

import shortpath3.utilities.geometry.Shape;

public class Continent 
{
    private String name;
    private Shape shape;

    public Continent(String name) 
    {
        this.name = name;
    }
    public Continent(String name, Shape shape) 
    {
        this.name = name;
        this.shape = shape;
    }

    public String getName() 
    {
        return name;
    }

    public Shape getShape() 
    {
        return shape;
    }
    
}