package shortpath3.utilities.geometry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Shape 
{
    private List<Point2D> points;

    public Shape(List<Point2D> points) 
    {
        this.points = points;
    }

    public List<Point2D> getPoints() 
    {
        return points;
    }

    public static Shape fromFile(File file)
    {
        ArrayList<Point2D> shapePoints = new ArrayList<>();
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String newline;
            float x, y;
            int cIndex;
            while ((newline = reader.readLine()) != null) 
            {
                cIndex = newline.indexOf(',');
                x = Float.parseFloat(newline.substring(0, cIndex));
                y = Float.parseFloat(newline.substring(cIndex));

                shapePoints.add(new Point2D(x, y));
            }

            reader.close();
            return new Shape(shapePoints);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return null;
    }
}