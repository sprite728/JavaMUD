package com.mud.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * @author Jared DiCioccio <br>
 * <br>
 *         The Server opens a socket connection, accepts connections from
 *         clients, and receives messages. The messages it receives get parsed
 *         by the ServerController, which interprets the command, acts on it,
 *         and sends messages back to the client. <br>
 * <br>
 * @param p
 *            : the port to bind to
 * @param sg
 *            : the server GUI reference, if the server's started from the GUI
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
	public HashMap<String, ClientThread> clientList;
	public static Server server;
	public ServerController sc;

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
		clientList = new HashMap<String, ClientThread>();
		sc = new ServerController(this);
	}

	/**
	 * @param msg
	 *            A string to print to console
	 */
	private void display(String msg) {
		System.out.println(msg);
	}

	public void msgAll(String msg) {
		for (ClientThread ct : server.clientList.values()) {
			ct.sendMessage(msg);
		}
	}

	public void msgUser(String user, String msg) {
		clientList.get(user).sendMessage(msg);
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

				// we create a new thread, and add it to the clientlist as a
				// temporary client
				// if we authenticate the user, we replace that entry with a
				// proper entry <User,ClientThread>
				ClientThread ct = new ClientThread(socket, sc, this);
				clientList.put("temporary", ct);
				ct.start();
			}

			serverSocket.close();
			Iterator<Entry<String, ClientThread>> iterator = clientList
					.entrySet().iterator();
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

		server = new Server(portNumber);
		server.start();
	}
}
