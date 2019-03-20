package car_parking.client.util;

public final class InputHandler 
{
    private InputHandler() {}
    
    public static byte getByte()
    {
        String input = System.console().readLine();
        try 
        {
            return Byte.parseByte(input);    
        } 
        catch (NumberFormatException e) 
        {
            return -1;
        }
    }

    public static short getShort()
    {
        String input = System.console().readLine();
        try 
        {
            return Short.parseShort(input);    
        } 
        catch (NumberFormatException e) 
        {
            return -1;
        }
    }
}