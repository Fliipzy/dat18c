package car_parking.client.vehicles; 

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
                outputStream.writeUTF(vehicleMessage); 
                outputStream.flush(); 
            }
            catch (IOException e)
            {
                System.out.println("IOException: " + e.getMessage()); 
            }
            return true; 
        }
        else 
        {
            return false; 
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