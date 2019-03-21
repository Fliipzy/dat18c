package car_parking.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ParkingLot 
{
    private Short port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    public ParkingLot(short port) 
    {
        this.port = port;
    }

    public void waitForConnection()
    {
        try 
        {
            this.serverSocket = new ServerSocket(port);
            this.clientSocket = serverSocket.accept();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean close()
    {
        try 
        {
            clientSocket.close();
            serverSocket.close();
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return true;
        } 
        catch (IOException e) 
        { 
            return false; 
        }
    }
}