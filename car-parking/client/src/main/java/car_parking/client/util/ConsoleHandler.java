package car_parking.client.util;

import java.io.IOException;

public final class ConsoleHandler 
{
    private ConsoleHandler() {}
    
    public static void clear()
    {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) 
        {
            try 
            {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                return;
            } 
            catch (InterruptedException | IOException e) 
            {
                System.out.println("Exception: Couldn't clear console!");
            }
        }
        else
        {
            try 
            {
                Runtime.getRuntime().exec("clear");
                return;    
            }
            catch (IOException e) 
            {
                System.out.println("Exception: Couldn't clear console!");
            }
        }

    }
}