package ocsf.client;

import java.io.*;
import java.sql.Time;
import java.time.*;

public class SimpleClient extends AbstractClient {

    // Constructor
    public SimpleClient(String host, int port) {
        super(host, port);
    }

    // Handle messages received from the server
    @Override
    protected void handleMessageFromServer(Object msg) {
        System.out.println("Server response: " + msg);
    }

    public static void main(String[] args) {
        String host = "localhost"; // Server hostname
        int port = 5555; // Server port

        // Create the client
        SimpleClient client = new SimpleClient(host, port);
        Time startTime = Time.valueOf(LocalTime.now());

        try {
            // Open connection to the server
            client.openConnection();
            System.out.println("Connected to server.");

            while (startTime.getTime() + 1000 > Time.valueOf(LocalTime.now()).getTime()) {
                // Send a message to the server
                client.sendToServer("Hello, server!");
                startTime = Time.valueOf(LocalTime.now());
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            try {
                // Close the connection
                client.closeConnection();
            } catch (IOException e) {
                System.out.println("ERROR while closing connection: " + e.getMessage());
            }
        }
    }
}