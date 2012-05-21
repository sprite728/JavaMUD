package com.mud.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.collections.BidiMap;

import com.mud.entities.Player;
import com.mud.server.Server.ClientThread;

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
	// Will hold an array of client threads - one for each client that connects
	public HashMap<String, ClientThread> clientList;
	static Server server;

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
				// temporary connection.
				// if we authenticate the user, we replace that entry with a
				// proper entry <User,ClientThread>
				ClientThread ct = new ClientThread(socket);
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

	class ClientThread extends Thread {
		Socket socket;
		BufferedReader input;
		PrintStream output;
		private int id;
		private String userName;
		String date;
		ServerController sc;
		boolean authenticated = false;
		Player player = new Player();

		ClientThread(Socket socket) {
			this.socket = socket;
			date = sdf.format(new Date());
			sc = new ServerController();
			player.isAuthenticated = false;

			try {
				input = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				output = new PrintStream(socket.getOutputStream());
			} catch (IOException e) {
				System.out
						.println("Could not create Input/Output stream: " + e);
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
					sc.parse(message);
				} catch (IOException e) {
					// TODO catch exception getting object input in client
					// thread
					e.printStackTrace();
				}
			}
		}

		private void close() {
			// TODO gracefully close client thread
		}
	}

}
