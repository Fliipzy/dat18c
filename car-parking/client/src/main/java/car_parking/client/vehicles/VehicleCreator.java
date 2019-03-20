package car_parking.client.vehicles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class VehicleCreator 
{
    private ArrayList<String> vehicles;
    private Random random;

    public VehicleCreator() 
    {
        initVehicleList();
        random = new Random();
    }

    private void initVehicleList() 
    {    
        Path path = Paths.get("car_parking\\client\\data", "car-models.txt");

        try 
        {
            vehicles = (ArrayList<String>) Files.readAllLines(path);
        } 
        catch (IOException e) 
        {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public Vehicle getRandomVehicle()
    {
        String model = vehicles.get(random.nextInt(vehicles.size()));
        return new Vehicle(model);
    }

    public Vehicle fromString(String model, String license)
    {
        return new Vehicle(model, license);
    }
}