package edu.dat18c.chatapp.server;

import edu.dat18c.chatapp.lib.server.ChatServer;
import edu.dat18c.chatapp.lib.server.interfaces.IChatServer;

/**
 * Program
 */
public class Program 
{
    private static IChatServer chatServer;

    public static void main(String[] args)
    {
        chatServer = ChatServer.getInstance();
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
                    
                    if (tokens.length > 1) 
                    {
                        handleKickCommand(tokens[0]);
                    }
                    else 
                    {
                        System.out.println("Missing username information. KICK <<username>>");
                    }
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

    private static void handleUnkownCommandError(String command)
    {
        System.out.println(String.format("'%s' was not recognized as a command!", command));
    }

    private static void handleKickCommand(String username)
    {
        
    }
}