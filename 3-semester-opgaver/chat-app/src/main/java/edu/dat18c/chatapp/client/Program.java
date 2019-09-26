package edu.dat18c.chatapp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * This client side program
 * @author Frederik Lundbeck Jørgensen
 */
public class Program 
{
    private final static int USERNAME_MAX_CHAR = 12;

    private static Socket socket;
    private static BufferedReader serverInput;
    private static String serverAddress;
    private static int serverPort;

    public static void main(String[] args) 
    {
        boolean connected = false;
        boolean running = true;

        //While client app is running
        while(running)
        {
            clearScreenWIN();

            //Make sure the socket is connected to server before continuing to chat loop.
            while (true) 
            {
                printHeader("FRED-CHAT © 2019, v1.0", 1);
    
                serverAddress = System.console().readLine("Connect to chat server:\n\n" + "Server-IP:\t");
    
                //Make sure given port number can be passed to an integer.
                serverPort = readIntegerInput("Server-PORT:\t", "Server-PORT must be an integer value.");
            
                //Try to connect socket to server
                try 
                {
                    socket = new Socket(InetAddress.getByName(serverAddress), serverPort);
                    serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    connected = true;
                    break; //Connection was succesful! Now breaking out of the connection loop.
                }
                catch (IOException e) 
                {
                    System.out.println("Could not connect to chat server!\n" +
                                       "Try connecting again? y/n");
    
                    //Make sure user decides whether to try again or quit.
                    while (true) 
                    {
                        String answer = System.console().readLine();
    
                        if (answer.equalsIgnoreCase("y")) 
                        {
                            clearScreenWIN();
                            break;
                        }
                        else if (answer.equalsIgnoreCase("n"))
                        {
                            System.exit(0);
                        }
                        else 
                        {
                            System.out.println("Try connecting again? y/n");
                        }
                    }
                }
            }
            //If socket has a connection, we enter the connection loop.
            while (connected) 
            {
                clearScreenWIN();
                printHeader(String.format("Connected To [%s]", serverAddress), 1);
                
                printConnectedMenu();
    
                int opCode = readIntegerInput("Operation: ");
    
                switch (opCode) 
                {
                    case 1:
    
                        clearScreenWIN();
                        printHeader(String.format("Joining [%s] Chat", serverAddress), 1);
    
                        //Start JOIN loop
                        String username = readUsername(USERNAME_MAX_CHAR);
                        //Send JOIN request to server
                        String response = communicateWithServer("JOIN " + username);
    
                        if (response != null) 
                        {
                            String[] responseTokens = response.split(" ");
                            //Go into chat loop
                            if (responseTokens[0].equals("J_OK"))
                            {
                                //Clear screen and update header
                                clearScreenWIN();
                                printHeader(String.format("[%s] Chat", serverAddress), 1);
    
                                //Start chat listener thread
                                ChatListener chatListener = new ChatListener(socket);
                                chatListener.start();
    
                                //Stay in chat loop until command is ':quit'.
                                while (true) 
                                {
                                   String msg = System.console().readLine();

                                   //If user wants to quit chat room
                                   if (msg.equalsIgnoreCase(":quit")) 
                                   {
                                       System.out.println("Exiting chat..");
                                       try 
                                       {
                                           //Stop chat listener thread
                                           socket.getOutputStream().write(("QUIT" + " \r\n").getBytes());
                                           socket.shutdownInput();
                                           chatListener.interrupt();
                                       } 
                                       catch (IOException e) 
                                       {
                                           System.out.println("Error occurred while exiting chat!");
                                       }
                                       break;    
                                   }
                                   else
                                   {
                                       //Try writing message to server
                                       try 
                                       {
                                           socket.getOutputStream().write(("DATA " + msg + "\r\n").getBytes());
                                       } 
                                       catch (IOException e) 
                                       {
                                           System.out.println("Error occurred while trying to communicate with server!");
                                       }
                                   }
                                }    
                            }
                        }
                        break;
                    case 2:

                        try 
                        {
                            socket.close();
                            connected = false;
                        } 
                        catch (IOException e) 
                        {
                            System.out.println("Error occurred while disconnecting from server!");
                        }

                        break;
                    case 3:
                        System.exit(0);
                    default:
                        break;
                }
            }
        }

    }
    
    public static void printHeader(String headerTitle, int bottomPad)
    {
        int h_length = headerTitle.length()+2;
        String header = String.format("%s\n %s\n%s%s", "-".repeat(h_length), headerTitle, "-".repeat(h_length), "\n".repeat(bottomPad));
        System.out.println(header);
    }

    public static void clearScreenWIN()
    {
        try 
        {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } 
        catch (IOException | InterruptedException e) 
        {
            
        }
    }

    public static void printConnectedMenu()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[1] JOIN CHAT\n");
        sb.append("[2] DISCONNECT\n");
        sb.append("[3] QUIT\n");
        System.out.println(sb.toString());
    }

    public static String communicateWithServer(String output)
    {
        try 
        {
            socket.getOutputStream().write((output + "\r\n").getBytes());
            socket.getOutputStream().flush();
            return serverInput.readLine();
        } 
        catch (IOException e) 
        {
            return null;
        }
    } 

    public static int readIntegerInput()
    {
        while (true) 
        {
            try 
            {
                return Integer.parseInt(System.console().readLine());
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Input must be an integer value.");
            }
        }
    }

    public static int readIntegerInput(String formattedPrompt)
    {
        while (true) 
        {
            try 
            {
                return Integer.parseInt(System.console().readLine(formattedPrompt));
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Input must be an integer value.");
            }
        }
    }

    public static int readIntegerInput(String formattedPrompt, String formatExceptionPrompt)
    {
        while (true) 
        {
            try 
            {
                return Integer.parseInt(System.console().readLine(formattedPrompt));
            } 
            catch (NumberFormatException e) 
            {
                System.out.println(formatExceptionPrompt);
            }
        }
    }

    public static String readUsername(int maxLength)
    {
        while (true) 
        {
            String username = System.console().readLine("Username: ");
            if (username.length() <= 12) 
            {
                return username;
            }
            System.out.println("Maximum username length is 12 characters!"); 
        }
    }
}