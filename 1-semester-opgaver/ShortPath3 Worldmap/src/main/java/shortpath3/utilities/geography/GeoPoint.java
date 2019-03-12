package shortpath3.utilities.geography;

public class GeoPoint 
{
    private float latitude;
    private float longitude;

    public GeoPoint(float latitude, float longitude) 
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() 
    {
        return latitude;
    }

    public void setLatitude(float latitude) 
    {
        this.latitude = latitude;
    }

    public float getLongitude() 
    {
        return longitude;
    }

    public void setLongitude(float longitude) 
    {
        this.longitude = longitude;
    }

    @Override
    public String toString()
    {
        return String.format("latitude(φ) = %s°, longitude(λ) = %s°", latitude, longitude);
    }

    @Override
    public boolean equals(Object obj)
    {
        try 
        {
            GeoPoint other = (GeoPoint)obj;
            if (other.latitude == this.latitude && other.longitude == this.longitude) 
            {
                return true;
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return false;
    }
}