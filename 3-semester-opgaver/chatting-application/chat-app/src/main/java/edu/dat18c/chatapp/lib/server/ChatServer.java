package edu.dat18c.chatapp.lib.server;

import java.util.Vector;
import edu.dat18c.chatapp.lib.client.ChatClientHandler;
import edu.dat18c.chatapp.lib.server.interfaces.IChatServer;

/**
 * ChatServer
 */
public class ChatServer implements IChatServer 
{
    private final int MAX_CLIENTS = 5; 

    private static ChatServer instance;

    private int port;
    private Vector<ChatClientHandler> clients = new Vector<ChatClientHandler>();
    private ChatServerHandler serverHandler;

    private ChatServer()
    { 
    }

    public static ChatServer getInstance()
    {
        if (instance == null) 
        {
            instance = new ChatServer();    
        }
        return instance;
    }

    @Override
    public void start(int port) 
    {
        serverHandler = new ChatServerHandler(port);
        serverHandler.start();
    }

    @Override
    public void close() 
    {
        serverHandler.interrupt();
    }

    /**
     * @return the server port
     */
    public int getPort() 
    {
        return port;
    }

}