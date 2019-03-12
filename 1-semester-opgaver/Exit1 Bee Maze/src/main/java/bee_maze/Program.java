package bee_maze;

import javax.swing.*;

import bee_maze.gui.MainFrame;

public final class Program 
{
    public static void main(String[] args) 
    {
            SwingUtilities.invokeLater(() ->
            {
                new MainFrame("Bee Maze");
            }
        );
    }
}
