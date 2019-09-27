package edu.dat18c.chatapp.lib.logging.interfaces;

import java.util.Collection;

/**
 * ILog
 */
public interface ILog 
{
    void update(ILogUpdate update);
    Collection<ILogUpdate> getUpdates();
}