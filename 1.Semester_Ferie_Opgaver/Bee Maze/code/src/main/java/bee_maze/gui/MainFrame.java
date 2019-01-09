package bee_maze.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bee_maze.util.Maze;
import bee_maze.util.Point;
import bee_maze.util.algorithms.MazeSolver;
import bee_maze.util.io.MazeLoader;

@SuppressWarnings("serial")
public class MainFrame extends JFrame 
{
    private static final int WIDTH = 500;
    private static final int HEIGHT = 550;

    private Maze maze;

    private JPanel southPanel;
    private JPanel displayPanel;

    private JFileChooser fileChooser;

    private JButton solveBtn;
    private JFrame frame;
    private Display display;

    private boolean hasSolved;

    public MainFrame(String title) 
    {
        frame = new JFrame("Bee Maze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setResizable(false);
        frame.setVisible(true);

        initJMenu();

        southPanel = new JPanel(new GridBagLayout());
        southPanel.setPreferredSize(new Dimension(1, 60));

        displayPanel = new JPanel(new GridBagLayout());

        solveBtn = new JButton("Solve");
        
        display = new Display();
        
        GridBagConstraints dpc = new GridBagConstraints();
        
        dpc.insets = new Insets(10, 10, 5, 10);
        dpc.fill = GridBagConstraints.BOTH;
        dpc.weightx = 0.5;
        dpc.weighty = 0.5;
        
        frame.add(displayPanel);
        displayPanel.add(display, dpc);
        frame.add(southPanel, BorderLayout.SOUTH);
        GridBagConstraints spc = new GridBagConstraints();
        
        spc.insets = new Insets(10, 10, 10, 10);
        spc.anchor = GridBagConstraints.EAST;
        spc.weightx = 0.5;
        spc.weighty = 0.5;
        spc.ipady = 10;
        southPanel.add(solveBtn, spc);
        solveBtn.setPreferredSize(new Dimension(20, 60));
        

        
        solveBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if (!hasSolved && maze != null) 
                {
                    ArrayList<Point> path = new ArrayList<Point>();
                    MazeSolver mazeSolver = new MazeSolver(maze);
                    
                    if (mazeSolver.pathExists(path)) 
                    {
                        hasSolved = true; 
                        display.paintPath(path);
                        solveBtn.setText("Clear");
                    }
                }
                else if (hasSolved) 
                {
                    //Clear maze data and repaint display
                    hasSolved = false;
                    maze = null;
                    display.repaint();
                    solveBtn.setText("Solve");
                }
            }
        });
    }

    private void initJMenu()
    {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open Maze...");
        openItem.addActionListener(new LoadMazeAction());
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ExitAppAction());

        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);
    }

    private class ExitAppAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            System.exit(0);
        }
    }

    private class LoadMazeAction implements ActionListener
    {
        String lastPath = null;

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if (lastPath == null) {
                fileChooser = new JFileChooser();
            }
            else
            {
                fileChooser = new JFileChooser(lastPath);
            }

            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int returnMsg = fileChooser.showOpenDialog(frame);

            if (returnMsg == JFileChooser.APPROVE_OPTION) 
            {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                maze = MazeLoader.getMazeFromFile(new File(path));
                lastPath = path;

                if (maze != null) 
                {
                    display.paintMaze(maze);
                } 
                else
                {
                    JOptionPane.showMessageDialog(frame, "Maze file could not be loaded correctly!", "File error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}