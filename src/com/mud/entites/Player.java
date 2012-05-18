package com.mud.entites;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.mud.worldmodel.Room;

public class Player extends Entity {

	public String name;
	public int level;
	public int health;
	public int energy;
	public Map<String, Integer> stats = new HashMap<String, Integer>();
	public ArrayList<Item> items;
	private Room currentRoom;

	public Player() {
		stats.put("Strength", 0);
		stats.put("Intelligence", 0);
		stats.put("Agility", 0);
		stats.put("Endurance", 0);
	}


	/**
	 * @param stat which stat to modify (Strength, Intelligence, Agility, Endurance)
	 * @param amount the amount to modify it by
	 */
	public void modifyStat(String stat, int amount) {
		int temp = stats.get(stat);
		stats.put(stat, temp + amount);
	}
	
	@Override
	public void attack() {
		// TODO Auto-generated method stub
	}

	@Override
	public void move(Room r) {
		this.currentRoom = r;
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sleep() {
		// TODO Auto-generated method stub

	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

	@Override
	public void spawn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void despawn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void give(Item item) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) == item) {
				items.remove(i);
				break;
			}
		}

	}

	@Override
	public void take(Item item) {
		items.add(item);
	}

	@Override
	public void emote(String action) {
		// TODO Auto-generated method stub

	}

}
