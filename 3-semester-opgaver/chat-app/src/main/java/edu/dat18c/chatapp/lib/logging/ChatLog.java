package edu.dat18c.chatapp.lib.logging;

import java.util.Queue;
import edu.dat18c.chatapp.lib.logging.interfaces.ILog;
import edu.dat18c.chatapp.lib.logging.interfaces.ILogUpdate;

/**
 * ChatLog
 */
public class ChatLog implements ILog 
{
    private Queue<ILogUpdate> updates;

    @Override
    public void update(ILogUpdate update) 
    {
        updates.add(update);
    }

    /**
     * @return the updates
     */
    public Queue<ILogUpdate> getUpdates() 
    {
      return updates;
    }
}