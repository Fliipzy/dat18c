package car_parking.client.util;

public final class UserInput 
{
    private UserInput() {}
    
    public static String getInput()
    {
        return System.console().readLine();
    }

    public static Short getShortInput()
    {
        String s = getInput();
        try
        {
            return Short.valueOf(s);
        } 
        catch (NumberFormatException e)
        {
            return null;
        }
    }
}