package com.mud.items;

public class Item {
	public String name;
	public String description;
	public int ilvl;
	public int minLevel; // -1 for no level restriction
	
	public Item(String name, String description, int ilvl, int minLevel){
		this.name = name;
		this.description = description;
		this.ilvl = ilvl;
		this.minLevel = minLevel;
	}

}
