package com.mud.server;

import com.mud.entities.Player;


/**
 * @author Jared DiCioccio
 * <br><br>
 * The ServerController parses messages received by Server and acts on them
 *
 */
public class ServerController {	
	
	public ServerController(){
		
	}
	
	public String parse(String input){
		String output = "";
		return output;
	}

	/**
	 * @param	user a string of the username connecting
	 * @param	passHash, a double hash of the password to validate in our database
	 * @returns the Player object of the player connecting, generated from stored database values
	 */
	public Player authenticatePlayer(String user, String passHash) {
		// TODO authenticate against database, generate Player() object with persisted database data and set isAuthenticated to true;
		Player p = new Player();
		p.isAuthenticated = true;
		return new Player();
	}
	

}
