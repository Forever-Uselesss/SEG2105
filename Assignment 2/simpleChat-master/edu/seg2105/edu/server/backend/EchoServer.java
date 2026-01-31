package edu.seg2105.edu.server.backend;
// This file contains material supporting section 3.7 of the textbook:

// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 */
public class EchoServer extends AbstractServer {
  // Class variables *************************************************

  String loginKey = "loginID"; // Key for storing login ID
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;

  // Constructors ****************************************************

  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer(int port) {
    super(port);
  }

  // Instance methods ************************************************

  /**
   * This method handles any messages received from the client.
   *
   * @param msg    The message received from the client.
   * @param client The connection from which the message originated.
   */
  @Override
  public void handleMessageFromClient(Object msg, ConnectionToClient client) {
    if (msg instanceof String) {
      String message = (String) msg;

      // Handle #login command
      if (message.startsWith("#login")) {
        try {
          String loginID = message.substring(7).trim();
          if (loginID.isEmpty()) {
            client.sendToClient("ERROR: Login ID cannot be empty.");
            client.close();
            return;
          }

          client.setInfo(loginKey, loginID);
          System.out.println("Message received: " + message + " from null.");
          System.out.println(loginID + " has logged on.");
          sendToAllClients(loginID + " has logged on.");
        } catch (Exception e) {
          System.out.println("Error handling #login command: " + e.getMessage());
        }
        return;
      }

      // Ensure client is logged in before processing other messages
      String loginID = (String) client.getInfo(loginKey);
      if (loginID == null) {
        try {
          client.sendToClient("ERROR: You must log in first.");
          client.close();
        } catch (Exception e) {
          System.out.println("Error closing client connection: " + e.getMessage());
        }
        return;
      }

      // Prefix message with login ID and broadcast
      System.out.println("Message received: " + message + " from " + loginID);
      String prefixedMessage = loginID + ": " + message;
      sendToAllClients(prefixedMessage);
    }
  }

  /**
   * This method overrides the one in the superclass. Called
   * when the server starts listening for connections.
   */
  @Override
  protected void serverStarted() {
    System.out.println("Server listening for connections on port " + getPort());
  }

  /**
   * This method overrides the one in the superclass. Called
   * when the server stops listening for connections.
   */
  @Override
  protected void serverStopped() {
    System.out.println("Server has stopped listening for connections.");
  }

  // Class methods ***************************************************

  /**
   * This method is responsible for the creation of
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on. Defaults to 5555
   *                if no argument is entered.
   */
  public static void main(String[] args) {
    int port = DEFAULT_PORT;

    try {
      if (args.length > 0) {
        port = Integer.parseInt(args[0]);
      }
    } catch (NumberFormatException e) {
      System.out.println("Invalid port number. Using default port " + DEFAULT_PORT);
    }

    EchoServer server = new EchoServer(port);

    try {
      server.listen();
    } catch (Exception ex) {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }

  @Override
  public void clientConnected(ConnectionToClient client) {
    System.out.println("A new client has connected to the server.");
  }

  @Override
  public void clientDisconnected(ConnectionToClient client) {
    System.out.println("Client disconnected: " + client);
    super.clientDisconnected(client);
  }
}
// End of EchoServer class
