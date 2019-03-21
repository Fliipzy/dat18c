package car_parking.client;

import car_parking.client.log.CommunicationLog;
import car_parking.client.util.ConsoleHandler;
import car_parking.client.util.InputHandler;
import car_parking.client.vehicles.*;
import car_parking.client.server.*;

public class Program 
{
    public static void main(String[] args) 
    {
        ServerConnector connector = new ServerConnector();
        ServerDialogue dialogue = null;
        VehicleCreator creator = new VehicleCreator();
        CommunicationLog log = new CommunicationLog();
        
        //Connection loop
        Short port;
        System.out.print("What's the parking lot port number?\n");
        while (true) 
        {
            port = InputHandler.getShort();

            if (port > 1000 && connector.tryConnect(port)) 
            {
                System.out.println("Connection to parking lot has been established!");
                dialogue = new ServerDialogue(connector.getSocket());
                dialogue.addLogSubscriber(log);
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
                //Send new vehicle to parking lot
                case 1:
                    int spaces = Integer.parseInt(dialogue.getResponse("SPACES_LEFT"));
                    if (spaces > 0) 
                    {
                        Vehicle vehicle = creator.getRandomVehicle();
                        String data = String.format("PARK %s, %s", vehicle.getModel(), vehicle.getLicense());

                        if (dialogue.getResponse(data).equals("OK"))
                        {
                            System.out.printf("%s was successfully parked!", vehicle.getModel());
                        }
                        else
                        {
                            System.out.println("Something went wrong trying to park!");
                        }
                    } 
                    else
                    {
                        System.out.println("Couldn't contact parking lot!");
                    }
                    System.console().readLine();
                    break;

                //View client server log
                case 2:
                    ConsoleHandler.clear();
                    log.display();
                    System.console().readLine();
                    break;

                //Exit program
                case 3:
                    dialogue.getResponse("CLIENT_DISCONNECT");
                    connector.tryClose();
                    dialogue.tryClose();
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