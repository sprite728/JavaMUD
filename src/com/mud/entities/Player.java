package com.mud.entities;

import java.util.ArrayList;
import java.util.HashMap;

import com.mud.items.Armor;
import com.mud.items.EquipmentSlot;
import com.mud.items.Item;
import com.mud.items.Weapon;
import com.mud.worldmodel.Room;

public class Player extends Entity {

	public PlayerClass playerClass;
	public HashMap<String, Integer> stats;
	public HashMap<EquipmentSlot, Item> equipment;
	public ArrayList<Item> inventory;
	public boolean isAuthenticated;
	public Room bindPoint;
	

	public Player() {
		stats = new HashMap<String, Integer>();
		equipment = new HashMap<EquipmentSlot, Item>();
		inventory = new ArrayList<Item>();
		isAuthenticated = false;
	}

	public boolean inIventory(Item item) {
		return inventory.contains(item);
	}

	public boolean isWearing(Item item) {
		return equipment.containsValue(item);
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
		r.alertArrival();
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

		spawn(bindPoint);
	}

	@Override
	public void spawn(Room r) {
		this.currentRoom = r;
		r.players.add(this);
	}

	@Override
	public void despawn() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param item
	 *            the item to remove from inventory.
	 * 
	 *            Removes item from player's inventory. *
	 * 
	 */
	public void removeItem(Item item) {
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i) == item) {
				inventory.remove(i);
				break;
			}
		}

	}

	/**
	 * 
	 * @param item
	 *            the item to add to player's inventory
	 * 
	 *            Adds item to player's inventory.
	 */
	public void addItem(Item item) {
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
