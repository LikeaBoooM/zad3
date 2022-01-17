package com.company.na4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server4 {
    private static ServerSocket _serverSocket;

    public static void main(String[] args) throws IOException {
        // Start
        _serverSocket = new ServerSocket(6000);
        System.out.println("Started server at: " + _serverSocket.getLocalSocketAddress());
        Socket connection = _serverSocket.accept();

        // <<
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String value = br.readLine();
        System.out.println("Client sends: " + value);

        // >>
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        if (value.equals("15.01.2022")) {
            String response = "Good request Darek\n";
            dataOutputStream.writeBytes(response);
            File file = new File("response.txt");
            if (!file.exists()) file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write(value + " - " + response);
            fw.close();
        }
        else {
            dataOutputStream.writeBytes("Bad request Darek\n");
        }

        // End
        dataOutputStream.close();
        br.close();
        connection.close();
        _serverSocket.close();
    }
}
