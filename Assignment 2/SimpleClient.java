import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5555);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            System.out.println("Connected to server.");
            out.println("Hello, server!");
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}