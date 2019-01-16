import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawingDisplay extends JPanel
{
    private Random random;
    private boolean isPainted = false;

    private long pointsInside = 0;
    private long pointsOutside = 0;

    private double currentBestApprox = 0;
    private double currentApprox = 0;

    public double getCurrentApprox()
    {
        return currentApprox;
    }
    public double getCurrentBestApprox()
    {
        return currentBestApprox;
    }
    public long getDotsPlaced()
    {
        return pointsInside + pointsOutside;
    }

    public DrawingDisplay() 
    {
        super();
        random = new Random();
        setBackground(Color.BLACK);
    }

    public void checkForBetterApprox()
    {
        currentApprox = 4.0 * ((double)pointsInside / (double)(pointsInside + pointsOutside));

        if (Math.abs(Math.PI - currentApprox) < Math.abs(Math.PI - currentBestApprox)) 
        {
            currentBestApprox = currentApprox; 
        }
    }

    public void placeRandomPoint(Graphics g)
    {
        double rx = (random.nextDouble() * 2) - 1; 
        double ry = (random.nextDouble() * 2) - 1;

        int lx = 11 + (int)(rx * ((getWidth()-25) / 2) + ((getWidth()-25) / 2));
        int ly = 10 + (int)(ry * ((getHeight()-25) / 2) + ((getHeight()-25) / 2));

        if (rx*rx + ry*ry < 1) 
        {
            pointsInside++;
            g.setColor(new Color(50, 50 + (205 - (int)((rx*rx + ry*ry)*255)),50));
            g.fillOval(lx, ly, 4, 4); 
        } 
        else
        {
            pointsOutside++;
            g.setColor(new Color(255 - (int)((rx*rx + ry*ry)*120),0,0));
            g.fillOval(lx, ly, 4, 4); 
        }

        checkForBetterApprox();
    }

    private void paintBackground(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.WHITE);
        g.drawRect(10, 10, getWidth()-21, getHeight()-22);
        g.drawOval(10, 10, getWidth()-21, getHeight()-22);
    }

	@Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        if (!isPainted) 
        {
            paintBackground(g);
            isPainted = true;
        }
    }
}