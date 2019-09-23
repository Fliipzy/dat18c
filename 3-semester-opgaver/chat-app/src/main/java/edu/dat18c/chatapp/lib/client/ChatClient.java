package edu.dat18c.chatapp.lib.client;

import java.io.IOException;
import java.net.InetAddress;

/**
 * ChatClient
 */
public class ChatClient 
{
    private String username;
    private String hostname;
    private InetAddress ip;
    private boolean authenticated;
    
    public ChatClient() {}

    public ChatClient(InetAddress ip) throws IOException
    {
        this.ip = ip;
        this.hostname = ip.getLocalHost().toString();
    }

    public ChatClient(String username, InetAddress ip) throws IOException
    {
        this.ip = ip;
        this.username = username;
        this.hostname = ip.getLocalHost().toString();
        this.authenticated = true;
    }

    /**
     * @return the username
     */
    public String getUsername() 
    {
      return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) 
    {
      this.username = username;
    }

    /**
     * @return the hostname
     */
    public String getHostname() 
    {
      return hostname;
    }

    /**
     * @return the ip
     */
    public InetAddress getIP() 
    {
      return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIP(InetAddress ip) throws IOException
    {
      this.ip = ip;
      this.hostname = ip.getLocalHost().toString();
    }

    /**
     * @param authenticated the authenticated to set
     */
    public void setAuthenticated(boolean authenticated) 
    {
      this.authenticated = authenticated;
    }

    /**
     * @return the authenticated status
     */
    public boolean getAuthenticated()
    {
        return authenticated;
    }

    @Override
    public String toString() 
    {
        return String.format("%s, %s", username, hostname);
    }
}