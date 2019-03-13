package car_parking.client;

import java.io.IOException;

import car_parking.client.util.UserInput;
import car_parking.client.vehicles.Garage;
import car_parking.client.vehicles.Vehicle;

public class Program 
{
    public static void main(String[] args) 
    {
        ParkingService parkingService = new ParkingService();
        Garage garage = new Garage();
        Short port = null;

        System.out.println("What port number does your parking lot have?");
        System.out.print("Port: ");

        port = UserInput.getShortInput();

        // connection loop
        while (port == null || !parkingService.connect(port)) {
            System.out.print("Try another port..\nPort: ");
            port = UserInput.getShortInput();
        }

        // main loop
        Short choice = null;
        while (true) 
        {
            //clearScreen();
            System.out.println("Welcome to the car service!\n\n" + "What can we do for you?\n"
                    + "1. Park a vehicle for me!\n"
                    + "2. I wanna leave!");

            choice = UserInput.getShortInput();

            switch (choice) {
            case 1: // Park!
                Vehicle vehicle = garage.getRandomCar();
                parkingService.parkVehicle(vehicle);
                break;
            case 2: // Exit
                parkingService.disconnect();
                System.exit(0);
                break;

            default:
                System.out.println("What?");
                break;
            }
        }
    }

    public static void clearScreen() 
    {
        try 
        {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } 
        catch (InterruptedException | IOException e) 
        {
            e.printStackTrace();
        }
    }
}
