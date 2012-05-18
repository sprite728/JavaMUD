package com.mud.worldmodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DirectedMultigraph;

public class GraphTest {

	
	private static DirectedMultigraph<Room, DirectionEdge> graph = new DirectedMultigraph<Room, DirectionEdge>(
			new ClassBasedEdgeFactory<Room, DirectionEdge>(
					DirectionEdge.class));
	/**
	 * @param args
	 *            the command line arguments are ignored
	 */
	public static void main(String[] args) {
		//
		Room r1 = new Room(1, "Room1", "The First Room!");
		Room r2 = new Room(2, "Room2", "The Second Room!");
		Room r3 = new Room(3, "Room3", "The Third Room!");
		Room r4 = new Room(4, "Room4", "The Fourth Room!");

		

		graph.addVertex(r1);
		graph.addVertex(r2);
		graph.addVertex(r3);
		graph.addVertex(r4);
		graph.addEdge(r1, r2, new DirectionEdge("East"));
		graph.addEdge(r2, r1, new DirectionEdge("West"));
		graph.addEdge(r2, r3, new DirectionEdge("North"));
		graph.addEdge(r3, r2, new DirectionEdge("South"));
		graph.addEdge(r1, r3, new DirectionEdge("Northeast"));
		graph.addEdge(r3, r1, new DirectionEdge("Southwest"));
		graph.addEdge(r1, r4, new DirectionEdge("West"));
		graph.addEdge(r4, r1, new DirectionEdge("East"));

		
	
		
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String input = null;
		
		Room currentRoom = r1;
		while (true) {
			System.out.println(currentRoom);
			HashMap<String, Room> exits = getExits(currentRoom);
			
			System.out.print("Exits:  ");
			for(String direction: exits.keySet()){
				System.out.print(direction + "   ");				
			}
			System.out.println("");
			
			try {
				input = reader.readLine();					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (input.equalsIgnoreCase("Quit")){
				System.out.println("GOODBYE!");
				break;
			}			
			if (exits.containsKey(input)) {				
				currentRoom = exits.get(input);
				continue;
			} else {
				System.out.println("There is no exit in that direction");
			}
		}

	}
	
	private static HashMap getExits(Room r){
		
		Set<DirectionEdge> exits = graph.outgoingEdgesOf(r);
		Iterator<DirectionEdge> iterator = exits.iterator();
		HashMap<String, Room> exitsMap = new HashMap<String,Room>();
		
		while(iterator.hasNext()){
			DirectionEdge de = iterator.next();			
			exitsMap.put(de.getDirection(), (Room) de.getTarget());
		}
		
		return exitsMap;
	}

}