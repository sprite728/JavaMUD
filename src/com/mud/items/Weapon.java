package com.mud.items;

import com.mud.enums.EquipmentSlot;
import com.mud.enums.WeaponMaterial;

public class Weapon extends Item {
	
	public Weapon(String name, String description, int ilvl, int minLevel) {
		super(name, description, ilvl, minLevel);
		this.slot = slot;
		this.material = material;
	}

	public WeaponMaterial material;
	public EquipmentSlot slot;

}
