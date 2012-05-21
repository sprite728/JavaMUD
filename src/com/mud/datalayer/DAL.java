package com.mud.datalayer;

import com.mud.entities.Player;

public class DAL {

	public boolean savePlayer(Player player) {
		// TODO: pack and save player to database
		return true;
	}

	public Player getPlayer(String user) {
		// TODO: Create new player object based on database
		return new Player();
	}

	public boolean authenticateUser(String user, String pass) {
		// TODO: Check database for user/pass
		return true;
	}

}
