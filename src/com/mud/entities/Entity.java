package com.mud.entities;

import com.mud.worldmodel.Room;

public abstract class Entity {
	
	public String name;
	public int level;
	public int health;
	public int energy;
	public Room currentRoom;

	
	public abstract void attack();	
	public abstract void cast();
	public abstract void sleep();
	public abstract void die();
	public abstract void spawn(Room r);
	public abstract void despawn();
	public abstract void emote(String action);
	public abstract void move(Room r);
}
