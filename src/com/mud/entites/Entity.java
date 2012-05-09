package com.mud.entites;

import java.util.HashMap;
import java.util.Map;

public abstract class Entity {
	
	public String name;
	public int level;
	public int health;
	public int energy;
	public Map<String, Integer> stats = new HashMap<String, Integer>();
	
	public abstract void attack();
	public abstract void move();
	public abstract void cast();
	public abstract void sleep();
	public abstract void die();
	public abstract void spawn();
	public abstract void despawn();
	public abstract void give();
	public abstract void take();
	public abstract void emote();
}
