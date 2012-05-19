package com.mud.items;

import com.mud.enums.ArmorMaterial;
import com.mud.enums.EquipmentSlot;

public class Armor extends Item{

	public Armor(String name, String description, int ilvl, int minLevel, EquipmentSlot slot, ArmorMaterial material) {
		super(name, description, ilvl, minLevel);
		this.slot = slot;
		this.material = material;
	}

	public EquipmentSlot slot;
	public ArmorMaterial material;

}
