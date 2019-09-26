package edu.dat18c.chatapp.lib.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * This class represents a chat client.
 * @author Frederik Lundbeck Jørgensen
 */
public class ChatClient 
{
    /** The clients username. */
    private String username;
    /** The clients hostname. */
    private String hostname;
    /** The clients IP address. */
    private InetAddress ip;
    /** Test whether client is chat authenticated. */
    private boolean authenticated;
    
    /**
     * Creates a new ChatClient object, without initializing it's fields.
     */
    public ChatClient() {}

    /**
     * Creates a new ChatClient object and initializes it's IP and hostname fields.
     * @param ip the {@code InetAddress} in which to set the client IP to.
     * @throws UnknownHostException if the local host name could not be resolved into an address.
     */
    public ChatClient(InetAddress ip) throws UnknownHostException
    {
        this.ip = ip;
        this.hostname = ip.getLocalHost().toString();
    }

    /**
     * Creates a new ChatClient object and initializes it's username, hostname and IP Address.
     * It also sets the authenticated {@code boolean} field to true.
     * @param username the {@code String} object, which the client username field will be set to.
     * @param ip the {@code InetAddress} object, which the client id field will be set to.
     * @throws UnknownHostException if the local host name could not be resolved into an address. 
     */
    public ChatClient(String username, InetAddress ip) throws UnknownHostException
    {
        this.ip = ip;
        this.username = username;
        this.hostname = ip.getLocalHost().toString();
        this.authenticated = true;
    }

    /**
     * Getter for the client username.
     * @return the client username.
     */
    public String getUsername() 
    {
      return username;
    }

    /**
     * Setter for the client username.
     * @param a {@code String} object, that the client username will be set to.
     */
    public void setUsername(String username) 
    {
      this.username = username;
    }

    /**
     * Getter for the client hostname.
     * @return the client hostname.
     */
    public String getHostname() 
    {
      return hostname;
    }

    /**
     * Getter for the client's InetAddress
     * @return the client ip.
     */
    public InetAddress getIP() 
    {
      return ip;
    }

    /**
     * Setter for the client's InetAddress. Also sets the client hostname.
     * @param ip the {@code InetAddress} object, that the client ip will be set to.
     * @throws UnknownHostException if the local host name could not be resolved into an address.
     */
    public void setIP(InetAddress ip) throws UnknownHostException
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

    /**
     * Converts the username and hostname to a {@code String}
     */
    @Override
    public String toString() 
    {
        return String.format("%s, %s", username, hostname);
    }
}