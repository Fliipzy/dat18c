package edu.dat18c.chatapp.lib.server.interfaces;

import java.util.List;

import edu.dat18c.chatapp.lib.client.interfaces.IChatClient;

/**
 * IChatServer
 */
public interface IChatServer 
{
    void start();
    void close();
    List<IChatClient> getClients();
}