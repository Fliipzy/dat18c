package edu.dat18c.chatapp.lib.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import edu.dat18c.chatapp.lib.client.interfaces.IChatClient;
import edu.dat18c.chatapp.lib.server.interfaces.IChatServer;

/**
 * ChatServer
 */
public class ChatServer implements IChatServer<IChatClient> 
{
    private ServerSocket serverSocket;
    private List<IChatClient> clients = new ArrayList<IChatClient>();
    private short port;

    public ChatServer(){}

    public ChatServer(short port)
    {
        this.port = port;
        start(port);
    }

    @Override
    public void start(short port) 
    {
        try 
        {
            serverSocket = new ServerSocket(port);
            this.port = port;
        } 
        catch (IllegalArgumentException e)
        {
            System.out.println("Port was outside the valid range 0-65535!");
            return;
        }
        catch (IOException e) 
        {
            System.out.println("An I/O error occurred, while opening the socket!");
            return;
        }
        
        while (true) 
        {
            Socket clientSocket;
            try 
            {
                clientSocket = serverSocket.accept();
            } 
            catch (IOException e) 
            {
                System.out.println("I/O error occurred while waiting for connection! Closing server..");
                close();
                break;
            }

            

        }
    }

    @Override
    public void close() 
    {
        try 
        {
            serverSocket.close();
        } 
        catch (Exception e) 
        {
            System.out.println("I/O error occurred while closing the server!");
        }
    }

    @Override
    public void addClient(IChatClient client) 
    {
        clients.add(client);
    }

    @Override
    public void removeClient(IChatClient client) 
    {
        clients.remove(client);

    }

    @Override
    public boolean isStarted() 
    {
        return !serverSocket.isClosed();
    }

    @Override
    public List<IChatClient> getClients() 
    {
        return clients;
    }

    public short getPort()
    {
        return port;
    }

}