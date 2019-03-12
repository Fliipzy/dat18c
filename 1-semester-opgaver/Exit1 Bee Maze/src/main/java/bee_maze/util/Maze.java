package bee_maze.util;

public class Maze 
{
    private boolean[][] horizontalLines;
    private boolean[][] verticalLines;

    private int horizontalLength;
    private int verticalLength;

    public Maze(boolean[][] horizontalLines, boolean[][] verticalLines) 
    {
        this.horizontalLines = horizontalLines;
        this.verticalLines = verticalLines;
        this.horizontalLength = horizontalLines.length;
        this.verticalLength = verticalLines.length;
    }

    public boolean[][] getHorizontalLines()
    {
        return horizontalLines;
    }
    public boolean[][] getVerticalLines()
    {
        return verticalLines;
    }

    public int getHorizontalLength()
    {
        return horizontalLength;
    }
    public int getVerticalLength()
    {
        return verticalLength;
    }

    public int getHorizontalRowLength()
    {
        return horizontalLines[0].length;
    }

    public int getVerticalRowLength()
    {
        return verticalLines[0].length;
    }

    @Override
    public String toString()
    {
        String s = "Horizontal edges \n";
        int line = 0;
        for (boolean[] array : horizontalLines) {
            s += "H" + line + ": ";
            for (boolean bool : array) {
                s += bool == true ? "0, " : "1, ";   
            }
            line++;
            s += "\n";
        }
        s += "\nVertical edges \n";
        line = 0;
        for (boolean[] array : verticalLines) {
            s += "V" + line + ": ";
            for (boolean bool : array) {
                s += bool == true ? "0, " : "1, ";
            }
            line++;
            s += "\n";
        }

        return s;
    }

    @Override
    public boolean equals(Object other)
    {
        try 
        {
            Maze maze = (Maze)other;
            return (horizontalLines == maze.horizontalLines && 
            verticalLines == maze.verticalLines) ? true : false;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }
}
