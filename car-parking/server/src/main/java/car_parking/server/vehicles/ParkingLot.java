package car_parking.server.vehicles;

import java.util.ArrayList;

public class ParkingLot 
{
    public static final int MAX_SPACES = 10;

    private int freeSpacesAmount = MAX_SPACES;
    private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    public ParkingLot() {}

    public int getFreeSpacesAmount() 
    {
        return freeSpacesAmount;
    }

    public ArrayList<Vehicle> getVehicles()
    {
        return vehicles;
    }

    public void parkVehicle(Vehicle vehicle)
    {
        if (hasSpace()) 
        {
            vehicles.add(vehicle);    
        }
    }

    public boolean hasSpace()
    {
        return freeSpacesAmount > 0;
    }
}