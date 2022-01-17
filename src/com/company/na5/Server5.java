package com.company.na5;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server5 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6000);
        System.out.println("Started server at: " + serverSocket.getLocalSocketAddress());
        System.out.println(serverSocket.getInetAddress());

        int clients = 0;
        ArrayList<Socket> connections = new ArrayList<>();

        while (clients<3) {
            Socket connection = serverSocket.accept();
            connections.add(connection);
            new ServerThread(connection).start();
            clients++;
        }

        System.out.println("Stopping server...");

        for (Socket s : connections) {
            s.getOutputStream().close();
            s.getInputStream().close();
            s.close();
        }
        serverSocket.close();
    }

    static class ServerThread extends Thread {
        Socket connection;
        BufferedReader br;

        public ServerThread(Socket connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            try {
                // <<
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String value = br.readLine();
                System.out.println("Client sends: " + value);

                // >>
                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.writeBytes("Hello " + value + "\n");
                // <<
                System.out.println(br.readLine());
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
}
