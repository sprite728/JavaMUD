package com.mud.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mud.enums.EquipmentSlot;
import com.mud.items.Armor;
import com.mud.items.Item;
import com.mud.items.Weapon;
import com.mud.worldmodel.Room;

public class Player extends Entity {

	public Map<String, Integer> stats = new HashMap<String, Integer>();
	public Map<EquipmentSlot, Item> equipment;
	public ArrayList<Item> inventory;

	public Player() {
		stats.put("Strength", 0);
		stats.put("Intelligence", 0);
		stats.put("Agility", 0);
		stats.put("Endurance", 0);
	}

	/**
	 * @param armor
	 *            the piece of armor to equip
	 * @return false if player's level does not meet min level criteria
	 * @return true if item gets equipped
	 */
	public boolean equipArmor(Armor armor) {
		EquipmentSlot slot = armor.slot;
		if (this.level < armor.minLevel) {
			return false;
		}
		this.equipment.remove(slot);
		this.equipment.put(slot, armor);
		refreshStats();
		return true;
	}

	/**
	 * 
	 * @param armor
	 *            the armor to be removed
	 * @return false if the player is not wearing anything in that slot
	 * @return true if the removed item was removed and placed in player's
	 *         inventory
	 */
	public boolean unEquipArmor(Armor armor) {
		EquipmentSlot slot = armor.slot;
		Armor removedItem = (Armor) this.equipment.remove(slot);
		if (removedItem == null) {
			return false;
		}
		return inventory.add(removedItem);
	}
	/**
	 * 
	 * @param weapon
	 *            the weapon to be removed
	 * @return false if the player is not wearing anything in that slot
	 * @return true if the removed item was removed and placed in player's
	 *         inventory
	 */
	public boolean unEquipWeapon(Weapon weapon) {
		EquipmentSlot slot = weapon.slot;
		Armor removedItem = (Armor) this.equipment.remove(slot);
		if (removedItem == null) {
			return false;
		}
		return inventory.add(removedItem);
	}

	/**
	 * @param weapon
	 *            the weapon to equip
	 * @return false if player's level does not meet min level criteria
	 * @return true if item gets equipped
	 */
	public boolean equipWeapon(Weapon weapon) {
		EquipmentSlot slot = weapon.slot;
		if (this.level < weapon.minLevel) {
			return false;
		}
		this.equipment.remove(slot);
		this.equipment.put(slot, weapon);
		refreshStats();
		return true;		
	}
	
	/**
	 * @param stat
	 *            which stat to modify (Strength, Intelligence, Agility,
	 *            Endurance)
	 * @param amount
	 *            the amount to modify it by
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

	/**
	 * 
	 * @param item
	 *            the item to give. Removes item from player's inventory.
	 *            Recipient Player object will call its own take() method.
	 */
	public void give(Item item) {
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i) == item) {
				inventory.remove(i);
				break;
			}
		}

	}

	public void take(Item item) {
		inventory.add(item);
	}

	@Override
	public void emote(String action) {
		// TODO Auto-generated method stub

	}

	private void refreshStats() {
		// TODO Auto-generated method stub
	}

}
