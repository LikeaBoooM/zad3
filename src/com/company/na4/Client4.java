package com.company.na4;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client4 {
    private static Socket _socket;

    public static void main(String[] args) throws IOException {
        // Start
        _socket = new Socket("127.0.0.1", 6000);
        DataOutputStream dataOutputStream = new DataOutputStream(_socket.getOutputStream());

        // >>
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input date: ");
        String date = scanner.next();
        dataOutputStream.writeBytes(date + "\n");
        dataOutputStream.flush();

        // <<
        BufferedReader br = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
        System.out.println("Server responded: " + br.readLine());

        // End
        dataOutputStream.close();
        br.close();
        _socket.close();
    }
}
