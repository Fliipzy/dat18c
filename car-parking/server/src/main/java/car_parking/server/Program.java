package car_parking.server;

public class Program 
{
    public static void main(String[] args)
     {
        ParkingLot parkingLot = new ParkingLot((short)8080);
        System.out.println("Waiting for client to connect..");
        parkingLot.waitForConnection();

        System.out.println("Client connected!");

        System.console().readLine();
    }
}
