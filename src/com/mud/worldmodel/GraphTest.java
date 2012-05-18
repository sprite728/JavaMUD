package com.mud.worldmodel;

import java.util.Iterator;
import java.util.Set;

import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DirectedMultigraph;


public class GraphTest {

	/**
	 * @param args
	 *            the command line arguments are ignored
	 */
	public static void main(String[] args) {
		// 
		Room r1 = new Room(1, "Room1", "The First Room!");
		Room r2 = new Room(2, "Room2", "The Second Room!");
		Room r3 = new Room(3, "Room3", "The Third Room!");

		DirectedMultigraph<Room, DirectionEdge> graph = new DirectedMultigraph<Room, DirectionEdge>(
				new ClassBasedEdgeFactory<Room, DirectionEdge>(
						DirectionEdge.class));

		graph.addVertex(r1);
		graph.addVertex(r2);
		graph.addVertex(r3);
		graph.addEdge(r1, r2).setDirection("East");
		graph.addEdge(r2, r1).setDirection("West");
		graph.addEdge(r2, r3, new DirectionEdge("North"));
		graph.addEdge(r3, r2, new DirectionEdge("South"));
		graph.addEdge(r1, r3, new DirectionEdge("Northeast"));
		graph.addEdge(r3, r1, new DirectionEdge("Southwest"));

		Set<DirectionEdge> set = graph.outgoingEdgesOf(r1);
		Iterator<DirectionEdge> it = set.iterator();
		while (it.hasNext()) {
			DirectionEdge next = it.next();
			System.out.print(next.getDirection() + " ");
		}
		System.out.println("");

		it = set.iterator();
		while (it.hasNext()) {
			DirectionEdge next = it.next();
			if (next.getDirection().equals("East")) {
				System.out.println(next);
				Room r = (Room) next.getTarget();
				System.out.println(r.getName());
			}
		}

	}
}