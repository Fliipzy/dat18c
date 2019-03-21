package car_parking.client.vehicles; 

import java.io.DataInputStream;
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.InetAddress; 
import java.net.Socket; 
import java.util.ArrayList; 

import car_parking.client.log.interfaces.ILog; 
import car_parking.client.log.interfaces.ILoggable; 

public class VehicleParker implements ILoggable 
{
    private ArrayList <ILog> logSubscribers; 

    private Socket socket = null; 
    private DataOutputStream outputStream = null; 
    private DataInputStream inputStream = null;

    private static VehicleParker instance = null; 

    private VehicleParker()
    {
        logSubscribers = new ArrayList<ILog> (); 
    }

    public static VehicleParker getInstance()
    {
        if (instance == null)
        {
            instance = new VehicleParker(); 
        }
        return instance; 
    }

    public boolean findParkingSpot(short port)
    {
        try 
        {
            this.socket = new Socket(InetAddress.getByName("localhost"), port); 
            this.outputStream = new DataOutputStream(socket.getOutputStream()); 
            this.inputStream = new DataInputStream(socket.getInputStream());

            notifyLogs("Connection was successfully established to server!");

            return true; 
        }
        catch (IOException e)
        {
            return false; 
        }
    }

    public boolean sendVehicle(Vehicle vehicle)
    {
        if (socket.isConnected() && outputStream != null)
        {
            String vehicleMessage = vehicle.model + ", " + vehicle.license; 

            try 
            {
                outputStream.writeUTF("NEW_CAR " + vehicleMessage); 
                outputStream.flush(); 
                
                if (inputStream.readUTF().equals("OK"))
                {
                    notifyLogs(String.format("Vehicle (id: %s) was successfully sent to server!", vehicle.license));
                    return true;
                } 
                else
                {
                    notifyLogs(String.format("Vehicle (id: %s) could not be sent to server!", vehicle.license));
                    return false;
                }
            }
            catch (IOException e)
            {
                System.out.println("IOException: " + e.getMessage());
                return false; 
            }
        }
        return false;
    }

    public int getRemainingParkingSpaces()
    {
        try 
        {
            outputStream.writeUTF("SPACES_LEFT");

            String response = inputStream.readUTF();
            return Integer.parseInt(response);
        } 
        catch (IOException e) 
        {
			return -1;
		}
    }

    public void leaveParkingSpot()
    {
        try 
        {
            socket.close(); 
            outputStream.close(); 
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong trying to leave!"); 
        }
    }

    @Override
    public void addLogSubscriber(ILog l)
    {
        logSubscribers.add(l); 
    }

    @Override
    public void removeLogSubscriber(ILog l)
    {
        logSubscribers.remove(l); 
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