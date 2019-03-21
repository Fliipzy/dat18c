package car_parking.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConnector 
{
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public ClientConnector(short port) 
    {
        try 
        {
            serverSocket = new ServerSocket(port);
        } 
        catch (Exception e) 
        {
            System.out.println("IOException: " + e.toString());
        }
    }

    public Socket getClientSocket() 
    {
        return clientSocket;
    }

    public void waitForClient()
    {
        try 
        {
            clientSocket = serverSocket.accept();
        } 
        catch (IOException e) 
        {
            System.out.println("IOException: " + e.toString());
        }
    }

    public boolean tryClose()
    {
        try 
        {
            clientSocket.close();
            serverSocket.close();
            return true;
        }
        catch (IOException e)
        {
            System.out.println("IOException: " + e.toString());
            return false;
        }
    }
}