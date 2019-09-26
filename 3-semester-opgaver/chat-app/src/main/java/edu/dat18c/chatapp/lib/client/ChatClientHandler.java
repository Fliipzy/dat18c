package edu.dat18c.chatapp.lib.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import edu.dat18c.chatapp.lib.server.ChatServer;

/**
 * The ChatClientHandler thread handles the tcp connection to the client socket.
 * The thread will be interrupted if connection to the client is lost.
 * @author Frederik Lundbeck Jørgensen
 */
public class ChatClientHandler extends Thread 
{
    /** The socket connected to the client machine. */
    private Socket clientSocket;
    /** The data output stream to the client. */
    private DataOutputStream output;
    /** The input stream that is being used to read from the client. */
    private BufferedReader input;
    /** The ChatClient object that holds all the client information. 
     * @see ChatClient*/
    private ChatClient chatClient;

    /**
     * Creates a new ChatClientHandler. The {@code clientSocket} field is set to the given socket parameter.
     * @param clientSocket The socket object to set the {@code clientSocket} field to.
     * @throws IOException If an I/O error occurs when initializing input & output streams to the socket.
     */
    public ChatClientHandler(Socket clientSocket) throws IOException
    {
        this.clientSocket = clientSocket;
        this.output = new DataOutputStream(clientSocket.getOutputStream());
        this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.chatClient = new ChatClient(clientSocket.getInetAddress());
    }

    /**
     * @return The data output stream to the client.
     */
    public DataOutputStream getOutput() 
    {
        return output;
    }

    /**
     * Starts the communication loop with the client. If connection is lost to the client, the loop will be terminated.
     */
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

                if (received != null) 
                {

                }

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
                        //TODO: implement
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
            catch (IOException | NullPointerException e)
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

        //Remove client from server client list
        ChatServer.getInstance().removeChatClient(chatClient);
    }
        
    /**
     * Handles the JOIN command logic.
     * @param tokens The received commands from client.
     */
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
        
    /**
     * Handles the DATA command logic.
     * @param The message to be send out to all other clients.
     */
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
    
    /**
     * Handles the QUIT command logic.
     * @throws IOException If an I/O error occurs while trying to close the client socket.
     */
    private void handleQuitRequest() throws IOException
    {
        this.interrupt();
        clientSocket.close();
    }
    
    /**
     * Handles the event where received command is not recognized.
     * @param command The unrecognized command.
     */
    private void handleUnknownCommand(String command)
    {
        respondError(String.format("'%s' is not recognized as a command!", command));
    }
    
    /**
     * Handles the HELP command logic. Sends all the available commands to client.
     */
    private void handleHelpRequest()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("JOIN <<user_name>>, <<server_ip>>:<<server_port>>\r\n");
        sb.append("DATA <<user_name>>: <<message>>\r\n");
        sb.append("IMAV\r\n");
        sb.append("QUIT");
        
        respond(sb.toString());
    }
    
    /**
     * Responsible for sending error messages to the client socket. Each message sent is appended 
     * with a carriage return and newline. 
     * @param errorMessage The error message to send over the data output stream.
     */
    public void respondError(String errorMessage)
    {
        respond(String.format("J_ER %s", errorMessage));
    }

    /**
     * Responsible for sending messages to the client socket. Each message sent is appended
     * with a carriage return and newline.
     * @param message The message to send over the data output stream.
     */
    public void respond(String message)
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