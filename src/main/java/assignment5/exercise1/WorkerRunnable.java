package assignment5.exercise1;

import java.io.*;
import java.net.Socket;

public class WorkerRunnable implements Runnable{

    private Socket clientSocket;

    public WorkerRunnable(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            final InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            final BufferedReader inFromClient = new BufferedReader(inputStreamReader);

            final String input = inFromClient.readLine();
            System.out.println("Received from Client: " + input);

            final String output = input.toUpperCase();

            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());
            final BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.append(output).append('\n');
            bufferedWriter.flush();
            bufferedWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
