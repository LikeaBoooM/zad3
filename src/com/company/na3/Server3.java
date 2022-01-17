package com.company.na3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server3 {
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
        dataOutputStream.writeBytes("Hello " + value + "\n");

        // <<
        System.out.println(br.readLine());

        // End
        dataOutputStream.close();
        br.close();
        connection.close();
        _serverSocket.close();
    }
}
