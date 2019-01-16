package bee_maze.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import bee_maze.util.Maze;
import bee_maze.util.Point;

@SuppressWarnings("serial")
public class Display extends JPanel 
{
    private static final int X_OFFSET = 100;
    private static final int Y_OFFSET = 100;

    private int xLineLength;
    private int yLineLength;

    public Display() 
    {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void paintPath(ArrayList<Point> path)
    {
        Graphics2D g = (Graphics2D)this.getGraphics();
        g.setColor(Color.RED);
        g.setStroke(new BasicStroke(3));

        for (int i = 0; i < path.size()-1; i++) 
        {
            Point p1 = localToScreenPos(new Point(path.get(i).getX(), path.get(i).getY()));
            Point p2 = localToScreenPos(new Point(path.get(i+1).getX(), path.get(i+1).getY()));
            
            g.drawLine(
                p1.getX() + (xLineLength / 2),
                p1.getY() - (yLineLength / 2), 
                p2.getX() + (xLineLength / 2), 
                p2.getY() - (yLineLength / 2));
        }                   
    }

    private Point localToScreenPos(Point local)
    {
        return new Point(
            local.getX() * xLineLength + (X_OFFSET / 2),
            getHeight()-2 - (local.getY()*yLineLength) - (Y_OFFSET / 2));
    }

    public void paintMaze(Maze maze)
    {
        Graphics2D g = (Graphics2D)this.getGraphics();
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        
        int vLength = maze.getVerticalLength(); 
        int hLength = maze.getHorizontalLength();

        int vRowLength = maze.getVerticalLines()[0].length;
        int hRowLength = maze.getHorizontalLines()[0].length;

        xLineLength = (getWidth() - X_OFFSET) / hRowLength; 
        yLineLength = (getHeight() - Y_OFFSET) / vRowLength;

        for (int y = hLength-1; y >= 0 ; y--) {
            for (int x = 0; x < hRowLength; x++) {

                if (maze.getHorizontalLines()[(hLength-1) - y][x] == false) 
                {
                    g.drawLine(
                        x * xLineLength + (X_OFFSET / 2), 
                        y * yLineLength + (Y_OFFSET / 2), 
                        x * xLineLength + xLineLength + (X_OFFSET / 2),
                        y * yLineLength + (Y_OFFSET / 2));
                }
            }
        }      
        
        for (int x = 0; x < vLength; x++) {
            for (int y = vRowLength-1; y >= 0 ; y--) {
                
                if (maze.getVerticalLines()[x][(vRowLength-1) - y] == false) {
                    
                    g.drawLine(
                        x * xLineLength + (X_OFFSET / 2), 
                        y * yLineLength + (Y_OFFSET / 2), 
                        x * xLineLength + (X_OFFSET / 2), 
                        y * yLineLength + yLineLength + (Y_OFFSET / 2));
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
    }
}