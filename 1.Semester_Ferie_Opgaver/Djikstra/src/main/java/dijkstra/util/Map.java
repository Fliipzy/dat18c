package dijkstra.util;

public class Map 
{
    private int sizeX, sizeY;

    private Point start, exit;

    private Point[][] points;

    public Map(int sizeX, int sizeY) 
    {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        initBlocks();
    }

    public Point[][] getPoints()
    {
        return points;
    }

    public int getSizeX()
    {
        return sizeX;
    }

    public int getSizeY()
    {
        return sizeY;
    }

    public Point getStart()
    {
        return start;
    }
    
    public void setStart(int x, int y)
    {
        start = points[x][y]; 
    }

    public Point getExit()
    {
        return exit;
    }
    
    public void setExit(int x, int y)
    {
        exit = points[x][y];
    }

    private void initBlocks()
    {
        points = new Point[sizeX][sizeY];
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                points[x][y] = new Point(x, y);
            }
        }
    }
}