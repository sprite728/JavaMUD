package com.mud.worldmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DirectedMultigraph;

import com.mud.entities.MOB;
import com.mud.entities.NPC;
import com.mud.entities.Player;
import com.mud.items.Item;

public class World {

	private static DirectedMultigraph<Room, DirectionEdge> rooms;

	public World() {
		rooms = new DirectedMultigraph<Room, DirectionEdge>(
				new ClassBasedEdgeFactory<Room, DirectionEdge>(
						DirectionEdge.class));
	}

	public HashMap<String, Room> getExits(Room r) {

		Set<DirectionEdge> exits = rooms.outgoingEdgesOf(r);
		Iterator<DirectionEdge> iterator = exits.iterator();
		HashMap<String, Room> exitsMap = new HashMap<String, Room>();
		while (iterator.hasNext()) {
			DirectionEdge de = iterator.next();
			exitsMap.put(de.getDirection(), (Room) de.getTarget());
		}

		return exitsMap;
	}

	public ArrayList<Player> getPlayersInRoom(Room r) {
		return r.players;
	}

	public ArrayList<MOB> getMOBsInRoom(Room r) {
		return r.MOBs;
	}

	public ArrayList<NPC> getNPCsInRoom(Room r) {
		return r.NPCs;
	}
	public ArrayList<Item> getItemsInRoom(Room r){
		return r.items;
	}
}
