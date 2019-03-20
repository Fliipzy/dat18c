package car_parking.client.log.interfaces;

import java.util.ArrayList;

public interface ILog 
{
    void addLogEntry(String s);
    ArrayList<String> getLogEntries();
}