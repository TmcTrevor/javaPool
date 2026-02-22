package fr._42.sockets.server;


import fr._42.sockets.models.User;
import fr._42.sockets.services.UsersService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

public class Server {

    private int port;
    private BufferedReader in;
    private PrintWriter out;
    private UsersService usersService;


    public Server(int port, UsersService usersService)
    {
        this.port = port;
        this.usersService = usersService;

    }

    public void startProcess()
    {
        try (ServerSocket serverSocket = new ServerSocket(this.port);
             Socket clientSocket = serverSocket.accept()
        )
        {
            this.in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println("Hello from Server!");
            String command = in.readLine(); // waits for client to send something
            System.out.println("Client sent: " + command);
            if (command.equals("signUp")) {
                signUp();
            }
            else {
                out.println("wrong Command : Try later with signUp");
            }
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void signUp() throws IOException
    {
            System.out.println("hello");

            out.println("Enter username");
            String username = in.readLine();
            out.println("Enter password");
            String passowrd = in.readLine();
            usersService.signUp(username, passowrd);
            Optional<User> optionalUser = usersService.getUserByUsername(username);
            optionalUser.ifPresent(user -> System.out.println("user out " + user));
            out.println("Successful!");
    }



}
