package bee_maze.util.algorithms;

import java.util.ArrayList;
import java.util.Random;

import bee_maze.util.Maze;
import bee_maze.util.Point;
import bee_maze.util.collections.Stack;

public class MazeSolver 
{
    private Random random;
    private boolean[][] mazePoints;
    private Point startPoint;
    private Point exitPoint;
    private Stack<Point> stack;
    private Maze maze;
<<<<<<< HEAD
=======
    private long elapsedTime;
>>>>>>> 990bf5648e6e4fc3c196afceb5b6144980ab3afa

    public MazeSolver(Maze maze) 
    {
        this.maze = maze;
        random = new Random();
    }

<<<<<<< HEAD
    public boolean pathExists(ArrayList<Point> refPath)
    {
=======
    public long getElapsedTime()
    {
        return elapsedTime;
    }

    public boolean pathExists(ArrayList<Point> refPath)
    {
        long start = System.nanoTime();

>>>>>>> 990bf5648e6e4fc3c196afceb5b6144980ab3afa
        if (findStartAndExit()) 
        {
            mazePoints = new boolean[maze.getHorizontalRowLength()][maze.getVerticalRowLength()];

            stack = new Stack<Point>();
            stack.push(startPoint);
            mazePoints[startPoint.getX()][startPoint.getY()] = true;

            while (stack.getLength() >= 1) 
            {
                //Exit found
                if (stack.peek().equals(exitPoint)) 
                {
<<<<<<< HEAD
=======
                    elapsedTime = System.nanoTime() - start;

>>>>>>> 990bf5648e6e4fc3c196afceb5b6144980ab3afa
                    refPath.add(new Point(exitPoint.getX(), exitPoint.getY() + 1));

                    while (stack.getLength() > 0) 
                    {
                        refPath.add(stack.pop());    
                    }

                    refPath.add(new Point(startPoint.getX(), startPoint.getY() - 1));

                    return true;
                }
            
                Point next = getRandomNeighbour(stack.peek());

                if (next != null) 
                {
                    stack.push(next);
                    mazePoints[next.getX()][next.getY()] = true;
                } 
                //backtrack
                else
                {
                    while (stack.getLength() > 0) 
                    {
                        next = getRandomNeighbour(stack.peek());
                        
                        if (next != null) 
                        {
                            stack.push(next);
                            mazePoints[next.getX()][next.getY()] = true;
                            break;
                        } 
                        stack.pop();
                    }
                }
            }
        } 
        return false;
    } 

    private Point getRandomNeighbour(Point from)
    {
        ArrayList<Point> neighbours = new ArrayList<Point>();

        //right 
        if (from.getX() + 1 <= maze.getHorizontalRowLength()-1 && mazePoints[from.getX()+1][from.getY()] == false) 
        {
            if (!hasWallBetween(from, new Point(from.getX() + 1, from.getY()))) 
            {
                neighbours.add(new Point(from.getX() + 1, from.getY()));
            }
        }

        //left
        if (from.getX() - 1 >= 0 && mazePoints[from.getX()-1][from.getY()] == false) 
        {
            if (!hasWallBetween(from, new Point(from.getX() - 1, from.getY()))) 
            {
                neighbours.add(new Point(from.getX() - 1, from.getY()));
            }
        }
        //up
        if (from.getY() + 1 <= maze.getVerticalRowLength()-1 && mazePoints[from.getX()][from.getY()+1] == false) 
        {
            if (!hasWallBetween(from, new Point(from.getX(), from.getY() + 1))) 
            {
                neighbours.add(new Point(from.getX(), from.getY() + 1));
            }
        }
        //down
        if (from.getY() - 1 >= 0 && mazePoints[from.getX()][from.getY()-1] == false) 
        {
            if (!hasWallBetween(from, new Point(from.getX(), from.getY() - 1))) 
            {
                neighbours.add(new Point(from.getX(), from.getY() - 1));
            }
        }

        if (neighbours.size() == 0) 
        {
            return null;    
        } 
        else
        {
            return neighbours.get(random.nextInt(neighbours.size()));
        }
    }

    private boolean findStartAndExit() 
    {
        boolean startFound = false;
        boolean exitFound = false;

        for (int i = 0; i < maze.getHorizontalRowLength(); i++) 
        {
            if (maze.getHorizontalLines()[0][i] == true) 
            {
                startFound = true;
                startPoint = new Point(i, 0);
            }
            if (maze.getHorizontalLines()[maze.getHorizontalLength()-1][i] == true) 
            {
                exitFound = true;    
                exitPoint = new Point(i, maze.getHorizontalLength()-2);
            }
        }
        return (startFound && exitFound) ? true : false;
    }

    private boolean hasWallBetween(Point a, Point b)
    {
        //Vertical difference
        if (Math.abs(a.getY() - b.getY()) == 1) 
        {
            int y = a.getY() > b.getY() ? a.getY() : b.getY();

            if (maze.getHorizontalLines()[y][b.getX()] == false) 
            {
                return true;    
            }
        }
        //Horizontal difference
        else if (Math.abs(a.getX() - b.getX()) == 1)
        {
            int x = a.getX() > b.getX() ? a.getX() : b.getX();

            if (maze.getVerticalLines()[x][b.getY()] == false) 
            {
                return true;    
            }
        }
        return false;
    }
}