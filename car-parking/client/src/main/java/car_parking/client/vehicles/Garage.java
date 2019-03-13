package car_parking.client.vehicles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class Garage 
{
    private ArrayList<String> carList;
    private Random random;

    public Garage() 
    {
        initCarList();
        random = new Random();
    }

    private void initCarList() 
    {    
        Path path = Paths.get("car_parking\\client\\data", "car-models.txt");

        try 
        {
            carList = (ArrayList<String>) Files.readAllLines(path);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public Vehicle getRandomCar()
    {
        String model = carList.get(random.nextInt(carList.size()));
        return new Vehicle(model);
    }
}