package car_parking.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import car_parking.client.vehicles.Vehicle;

public class ParkingService 
{
    Socket socket = null;
    DataOutputStream outputStream = null;
    DataInputStream inputStream = null;

    public ParkingService() 
    {
        
    }

    public void parkVehicle(Vehicle vehicle)
    {
        if (socket == null) 
        {
            System.out.print("[ParkingService] You need to establish connection to a parking service first!");
            return;
        }

        if (outputStream == null) 
        {
            try 
            {
                outputStream = new DataOutputStream(socket.getOutputStream());
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }

        if (inputStream == null) 
        {
            try 
            {
                inputStream = new DataInputStream(socket.getInputStream());
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }    
        }
        
       try 
       {
           outputStream.writeUTF(vehicle.getModel() + "," + vehicle.getLicense());
           System.out.println("[ParkingService] The vehicle was parked at port " + socket.getPort() + "!");
       } 
       catch (IOException e) 
       {
            e.printStackTrace();
       }
    }

    public boolean connect(short parkingPort)
    {
        try 
        {
            System.out.println("\n[ParkingService] Trying to establish connection to parking lot!");
            socket = new Socket("localhost", parkingPort);
            System.out.println("[ParkingService] Connection was successfully!\n");
            return true;
        } 
        catch (Exception e) 
        {
            System.out.println("[ParkingService] Couldn't establish connection to parking lot!\n");
            return false;
        }
    }

    public void disconnect()
    {
        try 
        {
            System.out.println("\n[ParkingService] Closing connection to parking lot!");
            socket.close();
            System.out.println("[ParkingService] Connection was successfully closed!\n");
        } 
        catch (IOException e) 
        {
            System.out.println("[ParkingService] Connection not closed properly!\n");
        }
    }
}