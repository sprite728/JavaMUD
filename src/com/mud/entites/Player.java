package com.mud.entites;

import java.util.HashMap;
import java.util.Map;

public class Player extends Entity{
	
	public String name;
	public int level;
	public int health;
	public int energy;
	public Map<String, Integer> stats = new HashMap<String, Integer>();
	
	Player(){
		stats.put("Strength", 0);
		stats.put("Intelligence", 0);
		stats.put("Agility", 0);
		stats.put("Endurance", 0);		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
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
	public void give() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void take() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void emote() {
		// TODO Auto-generated method stub
		
	}

}
