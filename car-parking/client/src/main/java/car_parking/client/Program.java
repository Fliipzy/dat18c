package car_parking.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import car_parking.client.log.CommunicationLog;
import car_parking.client.util.ConsoleHandler;
import car_parking.client.util.InputHandler;
import car_parking.client.vehicles.Vehicle;
import car_parking.client.vehicles.VehicleCreator;
import car_parking.client.vehicles.VehicleParker;

public class Program 
{
    public static void main(String[] args) 
    {
        VehicleParker parker = VehicleParker.getInstance();
        VehicleCreator creator = new VehicleCreator();
        CommunicationLog log = new CommunicationLog();
        
        //Add log
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
        
        //Main loop
        while (true) 
        {
            ConsoleHandler.clear();
            promptMenu();

            switch (InputHandler.getByte()) 
            {
                case 1:
                    Vehicle vehicle = creator.getRandomVehicle();
                    parker.sendVehicle(vehicle);
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