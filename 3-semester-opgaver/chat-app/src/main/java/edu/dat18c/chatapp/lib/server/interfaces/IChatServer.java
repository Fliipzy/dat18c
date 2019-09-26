package edu.dat18c.chatapp.lib.server.interfaces;

import java.util.List;

/**
 * IChatServer
 * @author Frederik Lundbeck Jørgensen
 */
public interface IChatServer 
{
    void start(int port);
    void close();
    boolean isRunning();
}