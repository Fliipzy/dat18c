package car_parking.server;

public class Program 
{
    private static final int MAX_SPACES = 10;
    public static void main(String[] args)
     {
        ParkingLot parkingLot = new ParkingLot((short)8080);

        System.out.println("Waiting for client to connect..");
        parkingLot.waitForConnection();
        System.out.println("Client connected!");

        int spacesLeft = MAX_SPACES; 
        String request = "";
        String keyword = "";
        String response = "";

        while (true) 
        {
            request = parkingLot.getClientRequest();
            System.out.println(request);
            
            switch (request) 
            {
                case "NEW_VEHICLE":
                    if (spacesLeft > 0) 
                    {
                        parkingLot.sendResponse("OK");
                        spacesLeft--;
                    } 
                    else 
                    {
                        parkingLot.sendResponse("NO_SPACE");
                    }
                    break;
                case "SPACES_LEFT":
                    parkingLot.sendResponse(Integer.toString(spacesLeft));
                    break;

                case "CLIENT_DISCONNECTION":
                    parkingLot.close();
                    System.out.println("Press anything to close..");
                    System.console().readLine();
                    System.exit(0);
                    break;
                default:
                    parkingLot.sendResponse("ERROR");
                    break;
            }
        }
    }
}
