package car_parking.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ParkingLot 
{
    ServerSocket serverSocket;
    Socket clientSocket;
    DataOutputStream outputStream;
    DataInputStream inputStream;

    public ParkingLot() 
    {
    }

    public void open()
    {
        try 
        {
            this.serverSocket = new ServerSocket(8000);
            this.clientSocket = serverSocket.accept();
        } 
        catch (Exception e)
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
            outputStream.close();
            inputStream.close();
            return true;
        } 
        catch (Exception e) 
        { 
            return false; 
        }
    }
}