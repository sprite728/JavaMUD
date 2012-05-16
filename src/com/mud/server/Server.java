package com.mud.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * @author Jared DiCioccio
 * <br><br>
 * The Server opens a socket connection, accepts connections from clients, and receives messages.
 * The messages it receives get parsed by the ServerController, which interprets the command, acts on it, and sends messages back to the client.
 * <br><br>
 * @param p : the port to bind to
 * @param sg: the server GUI reference, if the server's started from the GUI
 */
public class Server {

	private ServerSocket serverSocket;

	private String host;
	private int port;
	private static int defaultPort = 1555;
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
		ServerSocket serverSocket = null;
		try {
			System.out.println("Starting server...");
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost", port));

			while (running) {
				display("Waiting for incoming connections...");

				Socket socket = null;
				try {
					socket = serverSocket.accept();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (!running) {
					break;
				}

				ClientThread ct = new ClientThread(socket);
				clientList.add(ct);
				ct.start();
			}

			serverSocket.close();
			Iterator<ClientThread> iterator = clientList.iterator();
			while (iterator.hasNext()) {
				ClientThread t = (ClientThread) iterator.next();
				t.close();
				// TODO gracefully close client threads
			}
		} catch (IOException e) {
			System.out.println("Exception starting server" + e);
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
		ServerController sc;

		ClientThread(Socket socket) {
			this.socket = socket;
			date = sdf.format(new Date());
			sc = new ServerController();
			
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
					sc.parse(message);
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
