package car_parking.client.log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import car_parking.client.log.interfaces.ILog;

public final class CommunicationLog implements ILog
{
    private ArrayList<String> messages = new ArrayList<String>();
    private Calendar calender;
    private SimpleDateFormat sdf;

    public CommunicationLog() 
    {
        calender = Calendar.getInstance();
        sdf = new SimpleDateFormat("HH:mm");
        addLogEntry("Communication log created.");
    }

    public void display()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Socket communication log:\n\n");
        
        for (int i = 0; i < messages.size(); i++) 
        {
            sb.append("  │ " + messages.get(i) + "\n");
        }

        sb.append("\nEnd of log.");

        System.out.println(sb.toString());
    }

    @Override
    public ArrayList<String> getLogEntries()
    {
        return messages;
    }

    @Override
    public void addLogEntry(String s) 
    {
        messages.add(String.format("[%s] %s", sdf.format(calender.getTime()), s));
    }
}