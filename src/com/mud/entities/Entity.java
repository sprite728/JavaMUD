package com.mud.entites;

import java.util.HashMap;
import java.util.Map;

import com.mud.worldmodel.Room;

public abstract class Entity {
	

	
	public abstract void attack();	
	public abstract void cast();
	public abstract void sleep();
	public abstract void die();
	public abstract void spawn();
	public abstract void despawn();
	public abstract void emote(String action);
	public abstract void move(Room r);
}
