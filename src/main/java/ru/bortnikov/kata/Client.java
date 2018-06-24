package ru.bortnikov.kata;

import java.io.*;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {

		try (Socket socket = new Socket("localhost", 3345);
			 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
			 DataInputStream ois = new DataInputStream(socket.getInputStream())) {

			while (!socket.isOutputShutdown()) {

				if (br.ready())
					continue;

				oos.writeUTF(br.readLine());
				oos.flush();

				System.out.println(ois.readUTF());

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}