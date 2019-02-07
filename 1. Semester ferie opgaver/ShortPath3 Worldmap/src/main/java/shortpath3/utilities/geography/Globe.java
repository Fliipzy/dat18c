package shortpath3.utilities.geography;

public class Globe 
{
    public static final int EARTH_MEAN_RADIUS = 6371; //Metres

    /**
     * Using the <a href="https://en.wikipedia.org/wiki/Haversine_formula">Haversine formula</a>
     * to calculate the great circle distance between two geo-points
     * <p>This is the shortest distance over the earths surface, also called "as the crow flies" distance,
     * ignoring mountains etc.</p>
     */
    public static double getHaversineDistance(GeoPoint p1, GeoPoint p2)
    {
        double phi_p1 = toRadians(p1.getLatitude());
        double phi_p2 = toRadians(p2.getLatitude());
        double delta_phi = toRadians(p2.getLatitude() - p1.getLatitude());
        double delta_lambda = toRadians(p2.getLongitude() - p1.getLongitude());

        double a = Math.sin(delta_phi / 2) * Math.sin(delta_phi / 2) + 
            Math.cos(phi_p1) * Math.cos(phi_p2) * 
            Math.sin(delta_lambda / 2) * Math.sin(delta_lambda / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return EARTH_MEAN_RADIUS * c;
    }

    /**
     *  Returns the float parameter converted to radians.
     */ 
    private static float toRadians(float degrees)
    {
        return (float)(degrees * Math.PI / 180);
    }

}