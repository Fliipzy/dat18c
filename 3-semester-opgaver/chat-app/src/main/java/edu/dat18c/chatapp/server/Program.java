package edu.dat18c.chatapp.server;

import edu.dat18c.chatapp.lib.server.ChatServer;
import edu.dat18c.chatapp.lib.server.interfaces.IChatServer;

/**
 * Program
 */
public class Program 
{
    public static void main(String[] args)
    {
        IChatServer chatServer = ChatServer.getInstance();
        chatServer.start(8080);
    }
}