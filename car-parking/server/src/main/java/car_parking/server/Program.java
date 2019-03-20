package car_parking.server;

public class Program 
{
    public static void main(String[] args)
     {
        ParkingLot parkingLot = new ParkingLot();
        System.out.print("Waiting for client to connect..");
        parkingLot.open();

    }
}
