package ru.bortnikov.kata;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(3345)) {
			Socket socket = serverSocket.accept();
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());

			while (!socket.isClosed()) {
				String entry = in.readUTF();
				out.writeUTF("reply - " + entry);
				out.flush();
			}

			close(in, out, socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void close(DataInputStream in, DataOutputStream out, Socket socket) throws IOException {
		in.close();
		out.close();
		socket.close();
	}

}