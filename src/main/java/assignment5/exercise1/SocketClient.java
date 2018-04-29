package assignment5.exercise1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketClient {

    public static void main(String... args) throws IOException {
        final BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        final Socket clientSocket = new Socket("localhost", 9000);

        final DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        final BufferedReader inFromServer =
                new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.println("INPUT: ");
        final String input = inFromUser.readLine();

        outToServer.writeBytes(input + "\n");
        final String output = inFromServer.readLine();

        System.out.println("RESPONSE FROM SERVER: " + output);
        clientSocket.close();
    }
}
