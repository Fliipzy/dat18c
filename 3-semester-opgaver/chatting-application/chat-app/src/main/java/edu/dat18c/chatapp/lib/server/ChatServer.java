package edu.dat18c.chatapp.lib.server;

import java.util.ArrayList;
import java.util.List;

import edu.dat18c.chatapp.lib.client.interfaces.IChatClient;
import edu.dat18c.chatapp.lib.server.interfaces.IChatServer;

/**
 * ChatServer
 */
public class ChatServer implements IChatServer 
{
    private int port;
    private List<IChatClient> clients = new ArrayList<IChatClient>();

    @Override
    public void start() 
    {

    }

    @Override
    public void close() 
    {

    }

    @Override
    public List<IChatClient> getClients() 
    {
        return clients;
    }

    /**
     * @return the server port
     */
    public int getPort() 
    {
        return port;
    }

}