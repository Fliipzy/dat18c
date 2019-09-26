package edu.dat18c.chatapp.lib.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import edu.dat18c.chatapp.lib.client.ChatClientHandler;

/**
 * This {@code Thread} class helps with waiting for new chat clients. It does so by calling the
 * accept method from the {@code ServerSocket} object, that blocks until a client connects.
 * @author Frederik Lundbeck Jørgensen
 * @see ServerSocket
 * @see Thread
 */
public class ChatServerHandler extends Thread 
{
    /** The server socket, which is used to accept new clients. */
    private ServerSocket serverSocket;

    /**
     * Creates a new {@code ChatServerHandler} object. The {@code ServerSocket} object will
     * be initialized once this constructor is used.
     * @param port the port number that the {@code ServerSocket} will listen at.
     */
    public ChatServerHandler(int port) 
    {
        try 
        {
            this.serverSocket = new ServerSocket(port);
        } 
        catch (IOException e)
        {
            System.out.println("An I/O error occurred while opening the socket!");
        }
    }

    /**
     * Closes the {@code ServerSocket}.
     * @throws IOException If an I/O error occurs while trying to close the server socket.
     */
    public void close() throws IOException
    {
        serverSocket.close();
    }

    /**
     * This method will enter an infinite loop until the {@code ClientSocket} is closed. 
     * While looping the {@code ServerSocket} will listen for clients and instantiate {@code ChatClientHandler} objects, 
     * which will handle the communication from the clients.
     */
    @Override
    public void run()
    {
        while (true) 
        {
            try 
            {
                //Listen for and accept new client sockets. Then start a new clientHandler thread. 
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected to server from " + clientSocket.getInetAddress().getLocalHost());
                ChatClientHandler clientHandler = new ChatClientHandler(clientSocket);
                clientHandler.start();
            } 
            catch (SocketException e)
            {
                break;
            }
            catch (IOException e) 
            {
                System.out.println("An I/O error occurred while waiting for client connection!");
            }
        }
    }
    
}