package edu.dat18c.chatapp.lib.logging;

import java.util.Date;
import edu.dat18c.chatapp.lib.logging.interfaces.ILogUpdate;

/**
 * LogUpdate
 */
public class LogUpdate implements ILogUpdate
{
    private Date date;
    private String message;
    private Object fromObj;
    
    public LogUpdate(String message, Object fromObj) 
    {
        this.message = message;
        this.fromObj = fromObj;
        this.date = new Date();
    }

    @Override
    public Date getDate() 
    {
        return date;
    }

    @Override
    public String getMessage() 
    {
        return message;
    }

    @Override
    public Object getFromObj() 
    {
        return fromObj;
    }

}