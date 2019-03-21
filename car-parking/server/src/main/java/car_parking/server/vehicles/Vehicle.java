package car_parking.server.vehicles;

public class Vehicle 
{
    protected String model;
    protected String license;

    public Vehicle(String model) 
    {
        this.model = model;
        this.license = generateLicensePlate();
    }

    public Vehicle(String model, String license) 
    {
        this.model = model;
        this.license = license;
    }

    public String getModel() 
    {
        return model;
    }

    public String getLicense() 
    {
        return license;
    }

    private String generateLicensePlate()
    {
        return Integer.toHexString(this.hashCode());
    }

    public static Vehicle tryParseFromString(String s)
    {
        try 
        {
            String[] data = s.split(", ");
            return new Vehicle(data[0], data[1]);
        } 
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("OutOfBounds: " + e.toString());
            return null;
        }
    }

    @Override
    public String toString() 
    {
        return String.format("Model: %s, License-plate: %s", model, license);
    }

    @Override
    public boolean equals(Object obj) 
    {
        return ((Vehicle)obj).license == this.license;
    }
}