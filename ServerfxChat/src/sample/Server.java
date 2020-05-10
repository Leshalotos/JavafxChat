package sample;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server implements ConnectionListener {

    private final ArrayList<Connection> connections = new ArrayList<>();
    private int PORT;
    private String name;

        public Server(String name, int PORT) {
            this.name = name;
            this.PORT = PORT;
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Server started...");
                System.out.println("Please wait or start the program in new window...");
                while (true) {
                    try {
                        new Connection(this, serverSocket.accept());
                    } catch (IOException e) {
                        System.out.println("Connection exception: " + e);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        @Override
        public synchronized void onConnectionReady (Connection connection){
            connections.add(connection);
            sendToAllConnections("User connected: " + connection);
        }
        @Override
        public synchronized void onReceiveString (Connection connection, String string){
            sendToAllConnections(string);
        }
        @Override
        public synchronized void onDisconneckt (Connection connection){
            connections.remove(connection);
            sendToAllConnections("User disconnected: " + connection);
        }
        @Override
        public synchronized void onException (Connection connection, Exception e){
            System.out.println("Connection exception: " + e);
        }
        private void sendToAllConnections (String string){
            System.out.println(string);
            for (int i = 0; i < connections.size(); i++) connections.get(i).sendString(string);
        }
}

