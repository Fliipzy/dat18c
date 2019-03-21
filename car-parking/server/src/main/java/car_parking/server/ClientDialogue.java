package car_parking.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientDialogue 
{
    private DataInputStream input;
    private DataOutputStream output;

    public ClientDialogue(Socket socket) 
    {
        try 
        {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } 
        catch (IOException e) 
        {
            System.out.println("IOException: " + e.toString());
        }
    }

    public String getRequest()
    {
        try 
        {
            return input.readUTF();
        }
        catch (IOException e)
        {
            System.out.println("IOException: " + e.toString());
            return null;
        }
    }

    public void sendResponse(String s)
    {
        try 
        {
            output.writeUTF(s);
        } 
        catch (Exception e) 
        {
            System.out.println("IOException: " + e.toString());
        }
    }
}