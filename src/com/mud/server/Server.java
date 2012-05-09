package com.mud.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Server {

	private ServerSocket serverSocket;

	private String host;
	private int port;
	private static int defaultPort = 2000;
	private static int uniqueID;
	private boolean running;
	private SimpleDateFormat sdf;
	private ServerGUI sgui;
	// Will hold an array of client threads - one for each client that connects
	private ArrayList<ClientThread> clientList;

	public Server() {
		this(defaultPort);
	}

	public Server(int p) {
		this(p, null);
	}

	public Server(int p, ServerGUI sg) {
		sgui = sg;
		port = p;
		sdf = new SimpleDateFormat("HH:mm:ss");
		clientList = new ArrayList<ClientThread>();
	}

	/**
	 * @param msg
	 *            A string to print to console
	 */
	private void display(String msg) {
		System.out.println(msg);
	}

	public void start() {
		running = true;
		try {
			ServerSocket serverSocket = new ServerSocket();
		} catch (IOException e) {
			// TODO Catch exception opening server socket
			e.printStackTrace();
		}

		while (running) {
			display("Waiting for incoming connections...");
			try {
				Socket socket = serverSocket.accept();
				ClientThread ct = new ClientThread(socket);
				clientList.add(ct);
				ct.start();
			} catch (IOException e) {
				// TODO catch exception accepting new socket connection
				e.printStackTrace();
			}
		}

		try {
			serverSocket.close();
			Iterator iterator = clientList.iterator();
			while (iterator.hasNext()) {
				ClientThread t = (ClientThread) iterator.next();
				// TODO gracefully close client threads
			}
		} catch (IOException e) {
			// TODO catch exception closing server socket
			e.printStackTrace();
		}
	}

	public void stop() {
		running = false;
	}

	/**
	 * @param args
	 *            Port number to run server on
	 */
	public static void main(String[] args) {
		int portNumber = defaultPort;
		Server server = new Server(portNumber);
		server.start();
	}

	class ClientThread extends Thread {
		Socket socket;
		ObjectInputStream oInput;
		ObjectOutputStream oOutput;
		private int id;
		private String userName;
		String date;

		ClientThread(Socket socket) {
			this.socket = socket;
			date = sdf.format(new Date());
			try {
				oInput = new ObjectInputStream(socket.getInputStream());
				oOutput = new ObjectOutputStream(socket.getOutputStream());
				// when the client connects, the first thing sent will be the
				// username, so we grab that now
			} catch (IOException e) {
				System.out
						.println("Could not create Input/Output stream: " + e);
				e.printStackTrace();
			}

		}

		public void run() {
			boolean running = true;
			while (running) {
				try {
					String message = (String) oInput.readObject();
				} catch (IOException e) {
					// TODO catch exception getting object input in client
					// thread
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		private void close() {
			// TODO gracefully close client thread
		}
	}

}
