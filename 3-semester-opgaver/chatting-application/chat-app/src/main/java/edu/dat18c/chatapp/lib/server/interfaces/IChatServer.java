package edu.dat18c.chatapp.lib.server.interfaces;

import java.util.List;

import edu.dat18c.chatapp.lib.client.interfaces.IChatClient;

/**
 * IChatServer
 */
public interface IChatServer<T extends IChatClient>
{
    void start(short port);
    void close();
    void addClient(T client);
    void removeClient(T client);
    boolean isStarted();
    List<T> getClients();
}