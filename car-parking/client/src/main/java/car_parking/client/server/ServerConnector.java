package car_parking.client;

import java.io.IOException;
import java.net.Socket;

public class ServerConnector 
{
    private Socket socket = null;

    public ServerConnector() {}

    public Socket getSocket() 
    {
        return socket;
    }

    /**
     * Tries to initialize socket to server
     * @return boolean value based on whether or not connection was established
     */
    public boolean tryConnect(short port)
    {
        try 
        {
            socket = new Socket("localhost", port);
            return true;
        } 
        catch (IOException e) 
        {
            return false;
        }
    }

    /**
     * Tries to close the socket.
     * @return A {@code boolean} value based on whether 
     * or not, the socket, was closed successfully.
     */
    public boolean tryClose()
    {
        try 
        {
            socket.close();
            return true;
        } 
        catch (IOException e) 
        {
            System.out.println("IOException: " + e.toString());
            return false;
        }
    }
}