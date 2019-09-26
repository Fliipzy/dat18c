package edu.dat18c.chatapp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * This {@code Thread} class helps with listening for new messages being sent by the chat server.
 * @author Frederik Lundbeck Jørgensen
 */
public class ChatListener extends Thread 
{
    /** The input stream to the server. */
    private BufferedReader input;

    /**
     * Creates a new {@code ChatListener} object, and initializes it's socket field.
     * @param socket the endpoint for the server.
     */
    public ChatListener(Socket socket) 
    {
        try 
        {
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));    
        } 
        catch (IOException e) 
        {
            System.out.println("Error while initializing input stream to server!");
        }
    }

    @Override
    public void run() 
    {
        while (!isInterrupted()) 
        {
            try 
            {
                String recieved = input.readLine();
                System.out.println(recieved);
            } 
            catch (IOException e) 
            {
                break;
            }
        }
    }    
}