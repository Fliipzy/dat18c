package bee_maze.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bee_maze.util.Maze;
import bee_maze.util.io.MazeLoader;

@SuppressWarnings("serial")
public class MainFrame extends JFrame 
{
    public static MainFrame instance = null;

    private static final int WIDTH = 500;
    private static final int HEIGHT = 580;

    private Maze maze = null;

    private SouthPanel southPanel;
    private JPanel displayPanel;

    private JFileChooser fileChooser;

    private JFrame frame;
    private Display display;

    private HashMap<String, Component> componentMap;

    public Maze getMaze()
    {
        return maze;
    }

    public MainFrame(String title) 
    {
        if (instance == null) 
        {
            instance = this;   
        }

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setResizable(false);
        frame.setVisible(true);
        

        initJMenu();

        displayPanel = new JPanel(new GridBagLayout());
        displayPanel.setName("displayPanel");

        display = new Display();
        display.setName("display");
        
        GridBagConstraints dpc = new GridBagConstraints();
        
        dpc.insets = new Insets(10, 10, 5, 10);
        dpc.fill = GridBagConstraints.BOTH;
        dpc.weightx = 0.5;
        dpc.weighty = 0.5;
        
        frame.add(displayPanel);
        displayPanel.add(display, dpc);

        southPanel = new SouthPanel();
        southPanel.setName("southPanel");
        frame.add(southPanel, BorderLayout.SOUTH);

        createComponentMap();
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
                    findComponentByName("solveBtn").setEnabled(true);
                } 
                else
                {
                    JOptionPane.showMessageDialog(frame, "Maze file could not be loaded correctly!", "File error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void createComponentMap()
    {
        componentMap = new HashMap<String, Component>();
        List<Component> components = getAllComponents(frame.getContentPane());

        for (Component comp : components) 
        {
            componentMap.put(comp.getName(), comp);
        }

    }

    private List<Component> getAllComponents(final Container c)
    {
        Component[] compArray = c.getComponents();
        List<Component> compList = new ArrayList<Component>();

        for (Component comp : compArray) {
            
            compList.add(comp);

            if (comp instanceof Container) 
            {
                compList.addAll(getAllComponents((Container) comp));
            }

        }                   

        return compList;
    }

    public Component findComponentByName(String name)
    {
        if (componentMap.containsKey(name)) 
        {
            return (Component)componentMap.get(name);
        }
        else
        {
            return null;
        }
    }
}