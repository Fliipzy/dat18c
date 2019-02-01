package dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import dijkstra.util.Map;
import dijkstra.util.Point;

public final class Dijkstra
{
    private static final float DIAGONAL_COST = 1.414f;
    private static final float HORIZONTAL_COST = 1.0f;

    private Map map;

    public Dijkstra(Map map) 
    {
        this.map = map;
    }

    public boolean pathExists(List<Point> refList) 
    {
        setNeighbours();
        List<Point> openList = new ArrayList<Point>();
        List<Point> closedList = new ArrayList<Point>();

        Point start = map.getStart(), exit = map.getExit();

        closedList.add(start);
        openList.addAll(Arrays.asList(map.getStart().getNeighbours()));

        for (Point n : start.getNeighbours()) {
            n.setParent(start);
            n.setTravelCost(isHorizontalMove(start, n) ? HORIZONTAL_COST : DIAGONAL_COST);
        }
        
        while (!openList.isEmpty()) {

            Point cheapest = getCheapestPoint(openList);
            openList.remove(cheapest);
            closedList.add(cheapest);
            
            for (Point n : cheapest.getNeighbours()) {

                if (n.equals(exit)) {
                    n.setParent(cheapest);

                    refList.add(n);
                    Point p = n.getParent();
                    while (p != null) {
                        refList.add(p);
                        p = p.getParent();
                    }
                    return true;
                }

                if (n.getTravelCost() == 0) {
                    n.setParent(cheapest);
                    n.setTravelCost(cheapest.getTravelCost() + (isHorizontalMove(cheapest, n) ? HORIZONTAL_COST : DIAGONAL_COST));
                    openList.add(n);
                } 
                else if ((cheapest.getTravelCost() + (isHorizontalMove(cheapest, n) ? HORIZONTAL_COST : DIAGONAL_COST)) < n.getTravelCost())
                {
                    n.setTravelCost(cheapest.getTravelCost() + (isHorizontalMove(cheapest, n) ? HORIZONTAL_COST : DIAGONAL_COST));
                    n.setParent(cheapest);
                }
            }
        }

        return false;
    }

    private Point getCheapestPoint(List<Point> points)
    {
        return points.stream().min(Comparator.comparing(Point::getTravelCost)).get();
    }

    private boolean isHorizontalMove(Point start, Point n) 
    {
        return Math.abs(start.getX() - n.getX() + start.getY() - n.getY()) == 2 ? false : true;  
    }

    /**
     * Finds and sets every neighbour, to all points in the map.
     * <p>
     * If a neighbour point is non-traversable, it won´t be a part of the neighbours
     * array.
     */
    private void setNeighbours() 
    {
        int xMax = map.getSizeX();
        int yMax = map.getSizeY();

        for (int x = 0; x < xMax; x++) {
            for (int y = 0; y < yMax; y++) {

                if (!map.getPoints()[x][y].getTraversability()) 
                {
                    continue;
                }

                boolean right = false, left = false, top = false, bottom = false;
                List<Point> list = new ArrayList<Point>();

                //left
                if (x - 1 >= 0) {
                    left = true;
                    list.add(map.getPoints()[x-1][y]);
                }
                //right
                if (x + 1 < xMax) {
                    right = true;
                    list.add(map.getPoints()[x+1][y]);
                }
                //bottom
                if (y - 1 >= 0) {
                    bottom = true;
                    list.add(map.getPoints()[x][y-1]);
                }
                //top
                if (y + 1 < yMax) {
                    top = true;
                    list.add(map.getPoints()[x][y+1]);
                }
                //top-left
                if (top && left) {
                    list.add(map.getPoints()[x-1][y+1]);
                }
                //top-right
                if (top && right) {
                    list.add(map.getPoints()[x+1][y+1]);
                }
                //bottom-left
                if (bottom && left) {
                    list.add(map.getPoints()[x-1][y-1]);
                }
                //bottom-right
                if (bottom && right) {
                    list.add(map.getPoints()[x+1][y-1]);
                }
                //remove if neighbour isn´t traversable
                list.removeIf(p -> p.getTraversability() == false || p.equals(map.getStart()));
                Point[] array = Arrays.copyOf(list.toArray(), list.toArray().length, Point[].class);
                map.getPoints()[x][y].setNeighbours(array);
            }
        }
    }
}