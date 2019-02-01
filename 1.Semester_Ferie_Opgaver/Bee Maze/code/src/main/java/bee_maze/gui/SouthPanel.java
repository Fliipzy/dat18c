package bee_maze.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import bee_maze.util.Maze;
import bee_maze.util.Point;
import bee_maze.util.algorithms.MazeSolver;

@SuppressWarnings("serial")
public class SouthPanel extends JPanel
{
    private JButton solveBtn;
    private JLabel solveTime;
    private JLabel solveTimeData;

    public SouthPanel() 
    {
        this.setPreferredSize(new Dimension(1, 90));
        this.setLayout(new GridBagLayout());
        

        Border innerBorder = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            "Statistics", 
            TitledBorder.LEFT, 
            TitledBorder.TOP,
            new Font("default", Font.BOLD, 12),
            Color.BLACK);

        Border outerBorder = BorderFactory.createEmptyBorder(0, 8, 5, 8);

        this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        solveBtn = new JButton("Solve");
        solveBtn.setName("solveBtn");
        solveBtn.setPreferredSize(new Dimension(70, 20));
        solveBtn.addActionListener(new SolveBtnAction());
        solveBtn.setEnabled(false);

        solveTime = new JLabel();
        solveTime.setText("Solve time (s): ");

        solveTimeData = new JLabel();
        solveTimeData.setText("0");
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        c.anchor = GridBagConstraints.EAST;
        c.gridx = 3;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.ipady = 10;
        this.add(solveBtn, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.weightx = 0.5;
        c.weighty = 0.5;
        this.add(solveTime, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 2;
        c.weightx = 0.5;
        c.weighty = 0.5;
        this.add(solveTimeData, c);
    }
    
    private boolean isSolved = false;
    private class SolveBtnAction implements ActionListener
    {
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Maze maze = MainFrame.instance.getMaze();
            
            if (!isSolved && maze != null) 
            {
                ArrayList<Point> path = new ArrayList<Point>(); 
                MazeSolver mazeSolver = new MazeSolver(maze);
                
                //If maze has a valid path from start to exit
                if (mazeSolver.pathExists(path)) 
                {
                    ((Display)MainFrame.instance.findComponentByName("display")).paintPath(path);
                    isSolved = true;
                    solveBtn.setText("Clear");
                    solveBtn.setEnabled(true);
                    System.out.println(mazeSolver.getElapsedTime());
                    solveTimeData.setText(Float.toString((float)mazeSolver.getElapsedTime() / 1_000_000_000));
                }
                
            } 
            else
            {
                isSolved = false;
                solveBtn.setText("Solve");
                solveBtn.setEnabled(false);
                solveTimeData.setText("0");
                MainFrame.instance.findComponentByName("display").repaint();
            }
		}  
    }
    
}