package edu.dat18c.chatapp.lib.logging.interfaces;

import java.util.Date;

/**
 * ILogUpdate
 */
public interface ILogUpdate 
{
    Date getDate();
    String getMessage();
    Object getFromObj();
}