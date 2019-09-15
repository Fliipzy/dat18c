package edu.dat18c.chatapp.lib.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * ServerHandler
 */
public class ServerHandler extends Thread 
{
    private ServerSocket serverSocket;

    public ServerHandler(int port) 
    {
        try 
        {
            this.serverSocket = new ServerSocket(port);
        } 
        catch (IOException e)
        {
            System.out.println("An I/O error occurred while opening the socket!");
        }
    }

    @Override
    public void run()
    {
        
    }
    
}