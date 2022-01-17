package com.company.na3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client3 {
    private static Socket _socket;

    public static void main(String[] args) throws IOException {
        // Start
        _socket = new Socket("127.0.0.1", 6000);
        DataOutputStream dataOutputStream = new DataOutputStream(_socket.getOutputStream());

        // >>
        BufferedReader br = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
        dataOutputStream.writeBytes("Darek\n");
        dataOutputStream.flush();

        // <<
        System.out.println("Server responded: " + br.readLine());

        // >>
        dataOutputStream.writeBytes("Thank you 101401\n");
        dataOutputStream.flush();

        // End
        dataOutputStream.close();
        br.close();
        _socket.close();
    }
}
