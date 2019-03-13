package car_parking.server;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Program 
{
    public static void main(String[] args) 
    {
        DataInputStream inputStream = null;
        DataOutputStream outputStream = null;
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        System.out.println("Opening parking lot..\n");
        try 
        {
            serverSocket = new ServerSocket(8000);
            System.out.println("The parking lot was successfully opened!\n\nWaiting for customer..");

            clientSocket = serverSocket.accept();
            System.out.println("Customer has arrived!");

            //Set streams
            inputStream = new DataInputStream(clientSocket.getInputStream());
            outputStream = new DataOutputStream(clientSocket.getOutputStream());
        } 
        catch (Exception e) 
        {
            System.out.println("Something went wrong, while we tried to open the parking lot!");
        }

        //Listen and display loop
        while(true)
        {
            try 
            {
                String msg = inputStream.readUTF();
                System.out.println(msg);
            } 
            catch (IOException e) 
            {
                //Connection lost, break
                break;
            }
        }

        //Close sockets and streams
        try 
        {
            serverSocket.close();
            clientSocket.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
