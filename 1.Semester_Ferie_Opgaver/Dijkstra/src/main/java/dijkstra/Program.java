package dijkstra;

import java.util.ArrayList;
import java.util.List;

import dijkstra.util.Map;
import dijkstra.util.Point;

public final class Program 
{
    public static void main(String[] args) 
    {  
        Map map = new Map(5,5);

        map.setStart(0, 0);
        map.setExit(5, 5);

        Dijkstra djik = new Dijkstra(map);
        List<Point> path = new ArrayList<Point>();

        djik.pathExists(path);

        for (Point p : path) {
            System.out.println(p);
        }
  
    }
}
