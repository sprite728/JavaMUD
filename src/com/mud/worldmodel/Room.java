package com.mud.worldmodel;

import java.util.ArrayList;

import com.mud.entities.Entity;
import com.mud.entities.Player;
import com.mud.items.Item;

public class Room {

	public int id;
	public String name;
	public String description;

	ArrayList<Entity> NPCs;
	ArrayList<Player> players;
	ArrayList<Item> items;

	public Room(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return String.valueOf(this.id) + " - " + this.name + ": "
				+ this.description;
	}

}
