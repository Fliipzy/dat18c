package edu.dat18c.chatapp.server;

import edu.dat18c.chatapp.lib.server.ChatServer;
import edu.dat18c.chatapp.lib.server.interfaces.IChatServer;

/**
 * Program
 */
public class Program 
{
    public static void main(String[] args)
    {
        IChatServer chatServer = ChatServer.getInstance();
        chatServer.start(8080);

        while (chatServer.isRunning()) 
        {
            String command = System.console().readLine();
            String[] tokens = command.split(" ");

            if (tokens.length == 0) 
            {
                handleUnkownCommandError(command);
                continue;
            }

            switch (tokens[0]) 
            {
                case "LIST":
                    break;
                case "KICK":
                    break;
                case "CLOSE":
                    System.out.println("Closing chat server...");
                    chatServer.close();
                    break;
                default:
                    handleUnkownCommandError(tokens[0]);
                    break;
            }
        }
    }

    public static void handleUnkownCommandError(String cmd)
    {
        System.out.println(String.format("'%s' was not recognized as a command!", cmd));
    }
}