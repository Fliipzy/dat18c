package bee_maze.util.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import bee_maze.util.Maze;

public final class MazeLoader 
{
    private MazeLoader(){};

    public static Maze getMazeFromFile(File file)
    {
        try 
        {
            FileReader reader = new FileReader(file);
            int i;
            String data = "";

            while ((i = reader.read()) != -1) 
            {
                data += (char)i;
            }

            reader.close();
            return parseMazeData(data);
        } 
        catch (IOException e) 
        {
            return null;
        }
    }

    private static Maze parseMazeData(String data)
    {
        try 
        {
            String[] lineData = data.split("\n");
    
            int horizontal_y = Integer.parseInt(lineData[0].trim());
            int vertical_y = Integer.parseInt(lineData[horizontal_y + 1].trim());
    
            int horizontal_x = (int)lineData[1].chars().filter(c -> c == ',').count();
            int vertical_x = (int)lineData[horizontal_y+2].chars().filter(c -> c == ',').count();
    
            boolean[][] horizontal_lines = new boolean[horizontal_y][horizontal_x];
            boolean[][] vertical_lines = new boolean[vertical_y][vertical_x];
    
            for (int y = 0; y < horizontal_y; y++) {
                for (int x = 0; x < horizontal_x; x++) {
                    horizontal_lines[y][x] = lineData[y+1].split(",")[x+1].contains("0") ? true : false;
                }
            }
    
            for (int y = 0; y < vertical_y; y++) {
                for (int x = 0; x < vertical_x; x++) {
                    vertical_lines[y][x] = lineData[horizontal_y+1 + y+1].split(",")[x+1].contains("0") ? true : false;
                }
            }
    
            return new Maze(horizontal_lines, vertical_lines);
            
        } 
        catch (Exception e) 
        {
            return null;        
        }
    }
}