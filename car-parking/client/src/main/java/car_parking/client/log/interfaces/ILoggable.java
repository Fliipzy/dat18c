package car_parking.client.log.interfaces;

public interface ILoggable 
{
    void addLogSubscriber(ILog l);
    void removeLogSubscriber(ILog l);
    void notifyLogs(String s);
}