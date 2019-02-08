package shortpath3;

import shortpath3.utilities.geography.GeoPoint;
import shortpath3.utilities.geography.Globe;

public final class Program 
{
    public static void main(String[] args) 
    {
        GeoPoint kea37 = new GeoPoint(55.7038933f, 12.537625f);
        GeoPoint noerrebroSt = new GeoPoint(55.7006567f, 12.5378079f); 

        Double distance = Globe.getHaversineDistance(kea37, noerrebroSt);

        System.out.println(distance + " km");
    }
}
