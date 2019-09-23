package edu.dat18c.chatapp.lib.server;

import java.util.HashMap;
import java.util.Map;
import edu.dat18c.chatapp.lib.client.ChatClient;
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
    private boolean running;
    private ChatServerHandler serverHandler;
    private Map<ChatClient, ChatClientHandler> clients = new HashMap<ChatClient, ChatClientHandler>();  

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

    /**
     * @return the server port
     */
    public int getPort() 
    {
        return port;
    }
    
    public boolean isRunning()
    {
        return running;
    }

    @Override
    public void start(int port) 
    {
        serverHandler = new ChatServerHandler(port);
        serverHandler.start();
        running = true;
    }

    @Override
    public void close() 
    {
        System.exit(0);    
    }

    public void broadcastToAll(ChatClient fromClient, String msg)
    {
        for (ChatClientHandler clientHandler : clients.values()) 
        {
            try 
            {
                clientHandler.getOutput().write((fromClient.getUsername() + ": " + msg + "\r\n").getBytes());
            } 
            catch (Exception e) 
            {
                //TODO: handle exception
            }
        }
    }

    public boolean addChatClient(ChatClient client, ChatClientHandler clientHandler)
    {
        if (clients.size() == MAX_CLIENTS)
        {
            return false;    
        }
        clients.put(client, clientHandler);
        return true;
    }

    public boolean removeChatClient(ChatClient client)
    {
        clients.remove(client);
        return true;
    }

}