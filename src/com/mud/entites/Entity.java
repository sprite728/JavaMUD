package com.mud.entites;

public abstract class Entity {
	
	public String name;
	public int level;
	public int health;
	public int energy;
	
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
