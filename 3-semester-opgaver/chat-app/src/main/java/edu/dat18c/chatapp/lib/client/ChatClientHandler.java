package edu.dat18c.chatapp.lib.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ChatClientHandler
 */
public class ChatClientHandler extends Thread 
{
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private DataOutputStream output;
    private BufferedReader input;
    private String hostname;
    private String clientUsername;
    private boolean authenticated = false;

    public ChatClientHandler(Socket clientSocket, ServerSocket serverSocket) throws IOException
    {
        this.clientSocket = clientSocket;
        this.serverSocket = serverSocket;
        this.output = new DataOutputStream(clientSocket.getOutputStream());
        this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.hostname = clientSocket.getInetAddress().getLocalHost().toString();
    }

    @Override
    public void run() 
    {
        //Handle client requests
        while (!this.isInterrupted()) 
        {
           try 
           {
               //Receive request from client
               String received = input.readLine(); 
               String[] tokens = received.split("\\W | |:");
            
               //Handle request from client
               switch (tokens[0]) 
               {
                    case "JOIN":
                        handleJoinRequest(tokens);
                        break;
                    case "DATA":
                        handleDataRequest(tokens);
                        break;
                    case "IMAV":
                        handleIMAVRequest(tokens);
                        break;
                    case "QUIT":
                        handleQuitRequest();
                        break;
                    case "HELP":
                        handleHelpRequest();
                        break;
                   default:
                        handleUnknownCommand(tokens[0]);
                        break;
               }
           }
           catch (Exception e)
           {
               System.out.printf("Connection lost to %s\r\n", hostname);
               this.interrupt();
           }
        }
        try 
        {
            clientSocket.close();
        } 
        catch (IOException e) 
        {
            System.out.println(e);
        }
    }

    
    private void handleJoinRequest(String[] tokens)
    {
        //If tokens contains JOIN, USERNAME, SERVER-IP, SERVER-PORT  
        if (tokens.length > 1) 
        {
            //If USERNAME token is 12 characters or less
            if (tokens[1].length() > 12) 
            {             
                respondError("Username too long. Maximum length is 12 characters!");
            }
            else
            {
                clientUsername = tokens[1];
                System.out.printf("User %s has joined the chat!\r\n", clientUsername);
                respond("J_OK");
            }
        }
        else
        {
            respondError("Missing information!");
        }
    }

    private void handleDataRequest(String[] tokens)
    {
        if (authenticated) 
        {
            
        }
        else
        {
            respondError("'DATA' command requires client authentication. Authenticate by using JOIN command!");
        }
    }
    
    private void handleIMAVRequest(String[] tokens)
    {
        
    }
    
    private void handleQuitRequest() throws IOException
    {
        System.out.printf("%s disconnected from the server!\r\n", hostname);
        this.interrupt();
        this.clientSocket.close();
    }
    
    private void handleUnknownCommand(String command)
    {
        respondError(String.format("'%s' is not recognized as an command!", command));
    }

    private void handleHelpRequest()
    {
        respond("List of commands\r\n" +
                "JOIN <<user_name>>, <<server_ip>>:<<server_port>>\r\n" +
                "DATA <<user_name>>: <<message>>\r\n" + 
                "IMAV\r\n" +
                "QUIT");
    }

    private void respondError(String errorMessage)
    {
        respond(String.format("J_ER %s", errorMessage));
    }

    private void respond(String message)
    {
        try 
        {
            output.write((message + "\r\n").getBytes());
        } 
        catch (Exception e) 
        {
            System.out.println("I/O error occurred while writing to client!");
        }
    }
    
}