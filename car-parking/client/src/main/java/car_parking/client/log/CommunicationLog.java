package car_parking.client.log;

import java.util.ArrayList;

import car_parking.client.log.interfaces.ILog;

public final class CommunicationLog implements ILog
{
    private ArrayList<String> messages = new ArrayList<String>();

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
        messages.add(s);
    }
}