package edu.dat18c.chatapp.lib.client.interfaces;

import java.net.InetAddress;

/**
 * ChatClient
 */
public interface IChatClient 
{
    void connect(InetAddress ip, short port);
    void disconnect();    
}