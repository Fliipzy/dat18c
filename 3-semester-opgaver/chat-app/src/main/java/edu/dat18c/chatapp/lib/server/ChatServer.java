package edu.dat18c.chatapp.lib.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.dat18c.chatapp.lib.client.ChatClient;
import edu.dat18c.chatapp.lib.client.ChatClientHandler;
import edu.dat18c.chatapp.lib.logging.interfaces.ILog;
import edu.dat18c.chatapp.lib.logging.interfaces.ILogUpdate;
import edu.dat18c.chatapp.lib.logging.interfaces.ILoggable;
import edu.dat18c.chatapp.lib.server.interfaces.IChatServer;

/**
 * The ChatServer class controls the server.
 * It's used as a singleton to prevent multiple I/O exceptions. The class
 * can be accessed through the static {@code getInstance()} {@link #getInstance() method}. 
 * @author Frederik Lundbeck Jørgensen
 * @see https://www.tutorialspoint.com/design_pattern/singleton_pattern.htm
 */
public class ChatServer implements IChatServer, ILoggable
{
    /** The maximum number of clients allowed on the server at the same time. */
    private final int MAX_CLIENTS = 5; 
    /** The only instance of the class.*/
    private static ChatServer instance;

    /** The port number of the server. */
    private int port;
    /** Tells whether the server is running. */
    private boolean running;
    /** The thread that handles new client accept calls. */
    private ChatServerHandler serverHandler;
    /** The map in which all the connected clients are stored. */
    private Map<ChatClient, ChatClientHandler> clients = new HashMap<ChatClient, ChatClientHandler>();  

    private List<ILog> logs = new ArrayList<ILog>();

    private ChatServer() {}

    /**
     * This static method returns the single instance of this class.
     * @return the single instance of {@code ChatServer}.
     */
    public static ChatServer getInstance()
    {
        if (instance == null) 
        {
            instance = new ChatServer();    
        }
        return instance;
    }

    /**
     * Getter for the server port number.
     * @return the server port number.
     */
    public int getPort() 
    {
        return port;
    }
    
    /**
     * The getter for the running state of the server. Returns true if server is running.
     */
    public boolean isRunning()
    {
        return running;
    }

    /**
     * Initializes the {@code ChatServerHandler} object and invokes it's start method.
     * When this method is called the server will start to listen for accept calls.
     */
    @Override
    public void start(int port) 
    {
        serverHandler = new ChatServerHandler(port);
        serverHandler.start();
        running = true;
    }

    /**
     * Terminates the currently running Java Virtual Machine (Application).
     */
    @Override
    public void close() 
    {
        System.exit(0);    
    }

    /**
     * This method will broadcast a message to all connected clients of the server.
     * It's meant to be invoked by the {@code ChatClientHandler} threads, whenever a
     * client wants to send a message to everyone.
     * @param fromClient the {@code ChatClient}, the message is sent from.
     * @param msg the message from the client.
     */
    public void broadcastToAll(ChatClient fromClient, String msg)
    {
        for (ChatClientHandler clientHandler : clients.values()) 
        {
            clientHandler.respond(fromClient.getUsername() + ": " + msg);
        }
    }

    /**
     * Adds a new {@code Map.Entry<ChatClient, ChatClientHandler>} to the hashmap, which holds the
     * clients.
     * @param client the entry key.
     * @param clientHandler the entry value.
     * @return a {@code boolean} value, telling whether the operation was successful.
     */
    public boolean addChatClient(ChatClient client, ChatClientHandler clientHandler)
    {
        if (clients.size() == MAX_CLIENTS)
        {
            return false;    
        }
        clients.put(client, clientHandler);
        return true;
    }

    /**
     * Removes a {@code Map.Entry<ChatClient, ChatClientHandler>} from the {@code clients} hashmap.
     * @param client the key value, which is used to find the entry to remove.
     * @return a {@code boolean} value, telling whether the operation was successful.
     */
    public boolean removeChatClient(ChatClient client)
    {
        clients.remove(client);
        System.out.println(String.format("Removed %s from client list", client.getUsername()));
        return true;
    }

    @Override
    public void addLog(ILog l) 
    {
        logs.add(l);
    }

    @Override
    public void removeLog(ILog l) 
    {
        logs.remove(l);
    }

    @Override
    public void notifyLogs(ILogUpdate update) 
    {
        for (ILog log : logs) 
        {
            log.update(update);
        }
    }
}