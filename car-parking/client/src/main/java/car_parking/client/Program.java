package car_parking.client;

import car_parking.client.log.CommunicationLog;
import car_parking.client.util.ConsoleHandler;
import car_parking.client.util.InputHandler;
import car_parking.client.vehicles.*;

public class Program 
{
    public static void main(String[] args) 
    {

        VehicleParker parker = VehicleParker.getInstance();
        VehicleCreator creator = new VehicleCreator();
        CommunicationLog log = new CommunicationLog();
        
        //Add log as subscriber to parker
        parker.addLogSubscriber(log);

        //Get parking lot address
        Short port;
        System.out.print("What's the parking lot port number?\n");
        while (true) 
        {
            port = InputHandler.getShort();

            if (port > 1000 && parker.findParkingSpot(port)) 
            {
                System.out.println("Connection to parking lot has been established!");
                break;
            }
            System.out.println("Couldn't find anything at that port! try again..\n");
        }
        
        //Main menu loop
        while (true) 
        {
            ConsoleHandler.clear();
            promptMenu();

            switch (InputHandler.getByte()) 
            {
                case 1:

                    int spaces = parker.getRemainingParkingSpaces();
                    if (spaces > 0) 
                    {
                        Vehicle vehicle = creator.getRandomVehicle();
                        if (parker.sendVehicle(vehicle))
                        {
                            System.out.printf("%s was successfully parked!", vehicle.getModel());
                        }
                        else
                        {
                            System.out.println("Something went wrong trying to park!");
                        }
                    } 
                    else if (spaces > -1)
                    {
                        System.out.println("No more space in the parking lot!");
                    }
                    else
                    {
                        System.out.println("Couldn't contact parking lot!");
                    }

                    System.console().readLine();
                    break;

                case 2:
                    ConsoleHandler.clear();
                    log.display();
                    System.console().readLine();
                    break;

                case 3:
                    parker.leaveParkingSpot();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Not a valid operation!");
                    break;
            }
        }
    }

    public static void promptMenu()
    {
        System.out.println(
            "Choose client operation:\n" +
            "────────────────────────\n" +
            "1. Send new vehicle to server.\n" +
            "2. View client server log.\n" +
            "3. Exit program.\n"
        );
    }

}