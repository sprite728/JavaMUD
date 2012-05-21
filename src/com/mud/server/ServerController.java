package com.mud.server;

import com.mud.datalayer.DAL;
import com.mud.entities.Player;
import com.mud.server.Server.ClientThread;
import com.mud.worldmodel.Room;
import com.mud.worldmodel.World;

/**
 * @author Jared DiCioccio <br>
 * <br>
 *         The ServerController parses messages received by Server and acts on
 *         them
 * 
 */
public class ServerController {
	static int playerCount;
	Server server;
	DAL dal;
	World world;

	public ServerController(Server s) {
		server = s;
	}

	public String parse(String input) {
		String output = "";
		return output;
	}

	/**
	 * 
	 * @return true if Graph of rooms is generated
	 * @return false if something goes wrong and the world is not generated
	 */
	protected boolean initializeWorld() {
		// TODO: This will generate the MUDs rooms list
		world = new World();
		return true;
	}

	/**
	 * 
	 * @param user
	 *            the user we're looking for
	 * @return the room he is in, null if he is not found
	 */
	protected Room findPlayer(String user) {
		for (ClientThread ct : server.clientList.values()) {
			if (ct.player.name == user) {
				return ct.player.currentRoom;
			}
		}
		return null;
	}

	/**
	 * @param user
	 *            a string of the username connecting
	 * @param passHash
	 *            a double hash of the password to validate in our database
	 * @returns true if the user/pass combo authenticates
	 * @returns false if we can't authenticate with the server (bad user/pass)
	 */
	public boolean authenticateUser(String user, String passHash) {
		return dal.authenticateUser(user, passHash);
	}

	/**
	 * 
	 * @param user
	 *            the user/character name
	 * @return a player object pulled from the database
	 */
	public Player getAuthenticatedPlayer(String user) {
		// TODO Auto-generated method stub
		return new Player();
	}

	public void removePlayer(Player player) {
		dal.savePlayer(player);
		player.currentRoom.removePlayer(player);
	}

}
