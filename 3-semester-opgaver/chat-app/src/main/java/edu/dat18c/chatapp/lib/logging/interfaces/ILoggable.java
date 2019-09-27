package edu.dat18c.chatapp.lib.logging.interfaces;

/**
 * ILoggable
 */
public interface ILoggable 
{
    void addLog(ILog l);
    void removeLog(ILog l);
    void notifyLogs(ILogUpdate update);
}