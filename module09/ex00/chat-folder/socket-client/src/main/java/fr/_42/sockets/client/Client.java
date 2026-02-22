package fr._42.sockets.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {


    private  int port;

    public  Client(int port)
    {
        this.port = port;
    }

    public void startTheProcess()
    {
        try {

            Socket socket = new Socket("localhost", this.port);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Thread serverListener = new Thread(() -> {
                try {
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.exit(0); // cleanly kill the whole process
                }
            });
            serverListener.start();

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                out.println(scanner.nextLine());
            }
            socket.close();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }


}
