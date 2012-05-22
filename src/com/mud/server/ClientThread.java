package com.mud.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;

import com.mud.entities.Player;

public class ClientThread extends Thread {
	Socket socket;
	BufferedReader input;
	PrintStream output;
	private int id;
	private String userName;
	String date;
	ServerController sc;
	boolean authenticated = false;
	Player player;
	Server server;

	public ClientThread(Socket socket, ServerController sc, Server server) {
		this.socket = socket;
		this.server = server;
		this.date = new SimpleDateFormat("HH:mm:ss").format(new Date());
		this.sc = sc;
		player = new Player();
		player.isAuthenticated = false;
		try {
			input = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			output = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Could not create Input/Output stream: " + e);
			e.printStackTrace();
		}

	}

	public void sendMessage(String msg) {
		output.println(msg);
	}

	public void run() {
		boolean running = true;

		while (!player.isAuthenticated) {
			try {
				output.println("Username: ");
				String u = input.readLine();
				output.println("Password: ");
				// TODO: Double hash this:
				String p = input.readLine();
				if (sc.authenticateUser(u, p)) {
					player = sc.getAuthenticatedPlayer(u);
					output.println("Welcome back!");
					for (Entry<String, ClientThread> entry : server.clientList
							.entrySet()) {
						if (this.socket.equals((entry.getValue()))) {
							server.clientList.remove(entry.getKey());
							server.clientList.put(u, this);
							this.userName = u;
							sc.playerCount++;
						}
					}

				} else {
					output.println("Wrong user/pass");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		while (running) {
			try {
				String message = input.readLine();
				String reply = sc.parse(message);
				output.println(reply);
			} catch (IOException e) {
				// TODO catch exception getting object input in client
				// thread
				e.printStackTrace();
			}
		}
	}

	public void close() {
		sc.playerCount--;
		server.clientList.remove(this.userName);
		sc.removePlayer(this.player);
		try {
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
