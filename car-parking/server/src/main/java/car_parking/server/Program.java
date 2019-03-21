package car_parking.server;

import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

import car_parking.server.util.ConsoleHandler;
import car_parking.server.vehicles.ParkingLot;
import car_parking.server.vehicles.Vehicle;

public class Program 
{

    public static void main(String[] args)
    {
        ClientConnector connector = new ClientConnector((short)8080);
        ClientDialogue dialogue = null;
        ParkingLot parkingLot = new ParkingLot();


        //Waits for client to connect
        System.out.println("Waiting for client to connect..");
        connector.waitForClient();
        dialogue = new ClientDialogue(connector.getClientSocket());

        String request = null;
        String keyword = null;
        String data = null;
        String response = null;

        //Main loop
        while (true) 
        {
            ConsoleHandler.clear();
            displayServerInterface(parkingLot);

            //Get client request
            request = dialogue.getRequest();
            if (request.contains(" ")) 
            {
                keyword = request.substring(0, request.indexOf(" "));
                data = request.substring(keyword.length()+1, request.length());
            }
            else
            {
                keyword = request;
                data = null;
            }

            switch (keyword) 
            {
                case "SPACES":
                    response = Integer.toString(parkingLot.getFreeSpacesAmount());
                    break;

                case "PARK":
                    if(parkingLot.hasSpace())
                    {
                        if (data != null) 
                        {
                            Vehicle v = Vehicle.tryParseFromString(data);
                            parkingLot.parkVehicle(v);
                            response = "OK";
                            break;
                        }
                        response = "BAD_REQUEST";
                        break;
                    } 
                    response = "PARKING_LOT_FULL";
                    break;

                case "CARS":
                    if (parkingLot.getFreeSpacesAmount() < parkingLot.MAX_SPACES) 
                    {
                        response = "";
                        for (Vehicle v : parkingLot.getVehicles()) 
                        {
                            response += String.format("(%s, %s)", v.getModel(), v.getLicense());
                        }
                        break;
                    }
                    response = "EMPTY";
                    break;

                default:
                    response = "BAD_REQUEST";
                    break;
            }
            //Send response
            dialogue.sendResponse(response);
        }

    }

    //TODO: Clean this up
    public static void displayServerInterface(ParkingLot parkingLot)
    {
        System.out.println("PARKED CARS:\n");

        for (Vehicle c : parkingLot.getVehicles())
        {
            System.out.println(c.getModel());
        }
    }
}
