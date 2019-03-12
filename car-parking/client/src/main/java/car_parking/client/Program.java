package car_parking.client;

import car_parking.client.util.UserInput;

public class Program 
{
    public static void main(String[] args)
    {
        ParkingService parkingService = new ParkingService();
        Short port = null;

        System.out.println("What port number does your parking lot have?");
        System.out.print("Port: ");

        port = UserInput.getShortInput();

        //connection loop
        while (port == null || !parkingService.connect(port))
        {
            System.out.print("Try another port..\nPort: ");
            port = UserInput.getShortInput();
        }

        //main loop
        

    }


}
