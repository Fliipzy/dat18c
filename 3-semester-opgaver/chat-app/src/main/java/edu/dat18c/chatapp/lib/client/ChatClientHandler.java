package edu.dat18c.chatapp.lib.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import edu.dat18c.chatapp.lib.server.ChatServer;

/**
 * ChatClientHandler
 */
public class ChatClientHandler extends Thread 
{
    private Socket clientSocket;
    private DataOutputStream output;
    private BufferedReader input;
    private ChatClient chatClient;

    public ChatClientHandler(Socket clientSocket) throws IOException
    {
        this.clientSocket = clientSocket;
        this.output = new DataOutputStream(clientSocket.getOutputStream());
        this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.chatClient = new ChatClient(clientSocket.getInetAddress());
    }

    /**
     * @return the input
     */
    public DataOutputStream getOutput() 
    {
        return output;
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
               
                //If no tokens where received
                if (tokens.length == 0) 
                {
                   handleUnknownCommand(received);
                   continue;
                }
                
                //Handle request from client
                switch (tokens[0]) 
                {
                    case "JOIN":
                        handleJoinRequest(tokens);
                        break;
                    case "DATA":
                        handleDataRequest(received);
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
            catch (IOException e)
            {
                System.out.printf("Connection lost to %s\r\n", chatClient.getHostname());
                this.interrupt();
            }
        }
            
        try 
        {
            clientSocket.close();
        } 
        catch (IOException e) 
        {
            System.out.printf("Connection lost to %s\r\n", chatClient.getHostname());
        }

    }
        
    private void handleJoinRequest(String[] tokens)
    {
        if (chatClient.getAuthenticated()) 
        {
            respondError("Already joined the chat!");
            return;
        }
        
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
                //If server has capacity for one more client
                if (ChatServer.getInstance().addChatClient(chatClient, this)) 
                {
                    chatClient.setUsername(tokens[1]);
                    chatClient.setAuthenticated(true);
                    System.out.printf("User %s has joined the chat!\r\n", chatClient.getUsername());
                    respond("J_OK");
                }
                else
                {
                    respondError("Server is full");
                }
            }
        }
        else
        {
            respondError("Missing information!");
        }
    }
        
    private void handleDataRequest(String msg)
    {
        if (chatClient.getAuthenticated()) 
        {
            ChatServer.getInstance().broadcastToAll(chatClient, msg.substring(5));
        }
        else
        {
            respondError("'DATA' command requires authentication!");
        }
    }
        
    private void handleIMAVRequest(String[] tokens)
    {
        
    }
    
    private void handleQuitRequest() throws IOException
    {
        System.out.printf("%s disconnected from the server!\r\n", chatClient.getHostname());
        this.interrupt();
        clientSocket.close();
    }
    
    private void handleUnknownCommand(String command)
    {
        respondError(String.format("'%s' is not recognized as a command!", command));
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
            output.flush();
        } 
        catch (Exception e) 
        {
            System.out.println("I/O error occurred while writing to client!");
        }
    }
}