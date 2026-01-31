// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package edu.seg2105.client.backend;

import ocsf.client.*;

import java.io.*;

import edu.seg2105.client.common.*;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 */
public class ChatClient extends AbstractClient {
  // Instance variables **********************************************
  boolean loggedIn = false;

  /**
   * The interface type variable. It allows the implementation of
   * the display method in the client.
   */
  ChatIF clientUI;

  // Constructors ****************************************************

  /**
   * Constructs an instance of the chat client.
   *
   * @param host     The server to connect to.
   * @param port     The port number to connect on.
   * @param clientUI The interface type variable.
   */

  public ChatClient(String host, int port, ChatIF clientUI)
      throws IOException {
    super(host, port); // Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
  }

  // Instance methods ************************************************

  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) {
    clientUI.display(msg.toString());

  }

  /**
   * This method handles all data coming from the UI
   *
   * @param message The message from the UI.
   */
  public void handleMessageFromClientUI(String message) {
    try {
      if (message.startsWith("#")) {
        handleCommand(message);

      } else {
        sendToServer(message);
      }
    } catch (IOException e) {
      clientUI.display("Could not send message to server.  Terminating client.");
      quit();
    }
  }

  /**
   * This method handles the commands entered by the user.
   *
   * @param message The command from the UI.
   */
  private void handleCommand(String command) {
    String[] commandParts = command.split(" ", 2);
    String commandWord = commandParts[0].toLowerCase();

    switch (commandWord) {
      case "#quit":
        quit();
        break;
      case "#logoff":
        try {
          closeConnection();
        } catch (IOException e) {
          clientUI.display("Could not log off from server.");
        }
        break;
      case "#sethost":
        if (isConnected()) {
          clientUI.display("Cannot change host while connected.");
        } else {
          if (commandParts.length < 2) {
            clientUI.display("No host specified.");
          } else {
            String newHost = commandParts[1];
            setHost(newHost);
            clientUI.display("Host set to " + newHost);
          }
        }
        break;
      case "#setport":
        if (isConnected()) {
          clientUI.display("Cannot change port while connected.");
        } else {
          if (commandParts.length < 2) {
            clientUI.display("No port specified.");
          } else {
            try {
              int newPort = Integer.parseInt(commandParts[1]);
              setPort(newPort);
              clientUI.display("Port set to " + newPort);
            } catch (NumberFormatException e) {
              clientUI.display("Invalid port number.");
            }
          }
        }
        break;
      case "#login":
        if (isConnected()) {
          if (loggedIn) {
            clientUI.display("Already logged in.");
          } else {
            try {
              sendToServer("#login " + commandParts[1]);
              loggedIn = true;
            } catch (IOException e) {
              clientUI.display("Could not log in to server.");
            }
          }
          clientUI.display("Already connected to server.");
        } else {
          try {
            openConnection();
          } catch (IOException e) {
            clientUI.display("Could not connect to server.");
          }
        }
        break;
      case "#gethost":
        clientUI.display("Current host: " + getHost());
        break;
      case "#getport":
        clientUI.display("Current port: " + getPort());
        break;
      default:
        clientUI.display("Unknown command: " + commandWord);
    }
  }

  /**
   * This method terminates the client.
   */
  public void quit() {
    try {
      closeConnection();
    } catch (IOException e) {
    }
    System.exit(0);
  }

  /**
   * implementation of hook method called each time an exception is thrown by the
   * client's
   * thread that is waiting for messages from the server. The method may be
   * overridden by subclasses.
   * 
   * @param exception
   *                  the exception raised.
   */
  @Override
  public void connectionException(Exception exception) {
    clientUI.display("Connection error: " + exception.getMessage());
  }

  /**
   * Implement Hook method called after the connection has been closed. The
   * default
   * implementation does nothing. The method may be overriden by subclasses to
   * perform special processing such as cleaning up and terminating, or
   * attempting to reconnect.
   */
  @Override
  protected void connectionClosed() {
    clientUI.display("Connection closed.");
  }
}
// End of ChatClient class
