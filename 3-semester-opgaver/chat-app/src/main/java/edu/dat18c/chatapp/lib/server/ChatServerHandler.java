package edu.dat18c.chatapp.lib.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import edu.dat18c.chatapp.lib.client.ChatClientHandler;

/**
 * ServerHandler
 */
public class ChatServerHandler extends Thread 
{
    private ServerSocket serverSocket;

    public ChatServerHandler(int port) 
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

    /**
     * 
     * @throws IOException
     */
    public void close() throws IOException
    {
        serverSocket.close();
    }

    @Override
    public void run()
    {
        while (true) 
        {
            try 
            {
                //Listen for and accept new client sockets. Then start a new clientHandler thread. 
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected to server from " + clientSocket.getInetAddress().getLocalHost());
                ChatClientHandler clientHandler = new ChatClientHandler(clientSocket);
                clientHandler.start();
            } 
            catch (SocketException e)
            {
                break;
            }
            catch (IOException e) 
            {
                System.out.println("An I/O error occurred while waiting for client connection!");
            }
        }
    }
    
}