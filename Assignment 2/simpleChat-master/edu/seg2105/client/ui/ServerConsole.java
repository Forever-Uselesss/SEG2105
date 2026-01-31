package edu.seg2105.client.ui;

import edu.seg2105.client.common.ChatIF;
import edu.seg2105.edu.server.backend.EchoServer;

import java.io.*;
import java.util.Scanner;

public class ServerConsole implements ChatIF {
    private EchoServer server; // The server instance
    private Scanner fromConsole; // To read from the console

    // Constructor
    public ServerConsole(int port) {
        server = new EchoServer(port);
        fromConsole = new Scanner(System.in);
    }

    // Display messages to the server console
    @Override
    public void display(String message) {
        System.out.println(message);
    }

    // Accept user input and process commands or messages
    public void accept() {
        try {
            String message;
            while (true) {
                message = fromConsole.nextLine(); // Read input from the console

                if (message.startsWith("#")) {
                    handleCommand(message); // Process commands
                } else {
                    // Prefix the message and send to all clients
                    String serverMessage = "SERVER MSG> " + message;
                    display(serverMessage);
                    server.sendToAllClients(serverMessage);
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading from console: " + e.getMessage());
        }
    }

    // Handle server commands
    private void handleCommand(String command) {
        String[] commandParts = command.split(" ", 2); // Split command into parts
        String commandWord = commandParts[0].toLowerCase(); // Extract the command keyword

        try {
            switch (commandWord) {
                case "#quit":
                    server.close();
                    System.exit(0);
                    break;

                case "#stop":
                    server.stopListening();
                    display("Server stopped listening for new clients.");
                    break;

                case "#close":
                    server.close();
                    display("Server closed and all clients disconnected.");
                    break;

                case "#setport":
                    if (!server.isListening() && server.getNumberOfClients() == 0) {
                        if (commandParts.length < 2) {
                            display("No port specified.");
                        } else {
                            int port = Integer.parseInt(commandParts[1]);
                            server.setPort(port);
                            display("Port set to " + port);
                        }
                    } else {
                        display("Cannot set port while server is open or clients are connected.");
                    }
                    break;

                case "#start":
                    if (!server.isListening()) {
                        server.listen();
                        display("Server started listening for new clients.");
                    } else {
                        display("Server is already listening.");
                    }
                    break;

                case "#getport":
                    display("Current port: " + server.getPort());
                    break;

                default:
                    display("Unknown command: " + commandWord);
                    break;
            }
        } catch (Exception e) {
            display("Error processing command: " + e.getMessage());
        }
    }

    // Main method to start the server console
    public static void main(String[] args) {
        int port = EchoServer.DEFAULT_PORT; // Default port

        try {
            if (args.length > 0) {
                port = Integer.parseInt(args[0]); // Get port from command line
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid port number. Using default port " + port);
        }

        ServerConsole console = new ServerConsole(port);

        try {
            console.server.listen(); // Start the server.
            console.display("Server started. The server console waits for user input " + port);
            console.accept(); // Accept user input

        } catch (IOException e) {
            System.out.println("Error: Could not start server. " + e.getMessage());
        }
    }
}
