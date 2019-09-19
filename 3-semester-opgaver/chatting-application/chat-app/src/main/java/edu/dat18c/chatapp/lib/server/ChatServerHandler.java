package edu.dat18c.chatapp.lib.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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

    @Override
    public void run()
    {
        while (true) 
        {
            try 
            {
                //Listen for and accept new client sockets, then start a new clientHandler thread 
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected to server from " + clientSocket.getInetAddress().getLocalHost());
                ChatClientHandler clientHandler = new ChatClientHandler(clientSocket, this.serverSocket);
                clientHandler.start();
            } 
            catch (IOException e) 
            {
                System.out.println("An I/O error occurred while waiting for client connection!");
            }
        }
    }
    
}