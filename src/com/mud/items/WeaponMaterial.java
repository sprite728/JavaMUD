package com.mud.items;

public enum WeaponMaterial {
	BRONZE (0),
	COPPER (5), 
	SILVER (10),
	GOLD (15), 
	PLATINUM (20),
	MITHRIL (30);
	
	public final int modifier;
	WeaponMaterial(int modifier){
		this.modifier = modifier;		
	}
}
