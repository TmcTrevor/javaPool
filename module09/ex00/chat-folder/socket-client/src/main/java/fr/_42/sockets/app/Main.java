package fr._42.sockets.app;

import fr._42.sockets.client.Client;

public class Main {

    public static void main(String[] args) {

        Client client = new Client(8081);

        client.startTheProcess();
    }
}
