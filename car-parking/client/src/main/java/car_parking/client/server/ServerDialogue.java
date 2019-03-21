package car_parking.client.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import car_parking.client.log.interfaces.*;

public class ServerDialogue implements ILoggable
{
    private DataOutputStream output;
    private DataInputStream input;

    private ArrayList<ILog> logSubscribers = new ArrayList<ILog>();

    public ServerDialogue(Socket socket) 
    {
        try 
        {
            this.input = new DataInputStream(socket.getInputStream());
            this.output = new DataOutputStream(socket.getOutputStream());
        } 
        catch (IOException e) 
        {
            System.out.println("IOException: " + e.toString());
        }
    }

    /**
     * Tries to send a {@code String} s, to the server.
     * @param s The {@code String} message, that will sent to the server.
     * @return The {@code String} response from the server. <p>
     * In case an exception is thrown the method will return null
     */
    public String getResponse(String s)
    {
        try 
        {
            output.writeUTF(s);
            return input.readUTF();
        } 
        catch (Exception e) 
        {
            return null;
        }
    }

    /**
     * Tries to close the i/o data streams.
     * @return A {@code boolean} value based on whether 
     * or not, the streams, was closed successfully.
     */
    public boolean tryClose() 
    {
        try 
        {
            output.flush();
            output.close();
            input.close();
            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }

	@Override
    public void addLogSubscriber(ILog o) 
    {
		logSubscribers.add(o);
	}

	@Override
    public void removeLogSubscriber(ILog o) 
    {
		logSubscribers.remove(o);
	}

	@Override
    public void notifyLogs(String s) 
    {
        for (ILog log : logSubscribers) 
        {
            log.addLogEntry(s);
        }
	}

}