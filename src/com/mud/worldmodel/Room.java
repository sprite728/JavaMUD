package com.mud.worldmodel;

import java.util.ArrayList;

import com.mud.entites.Entity;
import com.mud.entites.Item;
import com.mud.entites.Player;

public class Room {
	
	private int id;
	private String name;
	private String description;
	ArrayList<Entity> NPCs ;
	ArrayList<Player> players;
	ArrayList<Item> items;
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
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
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	

}
