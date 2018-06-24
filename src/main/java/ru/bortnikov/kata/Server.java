package ru.bortnikov.kata;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(3345)) {
            Socket client = server.accept();
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());

            while (!client.isClosed()) {
                System.out.println("Server reading from channel");
                String entry = in.readUTF();
                System.out.println("READ from client message - " + entry);

                out.writeUTF("Server reply - " + entry + " - OK");
                out.flush();
            }

            in.close();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}