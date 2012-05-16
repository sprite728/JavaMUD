import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;

import com.mud.server.LabeledEdges.DirectionEdge;

public class LabeledEdges {

	// This is a quick, rough example of how I want to use a DirectedMultiGraph
	// to model rooms in the MUD.

	private static final String NORTH = "north";
	private static final String SOUTH = "south";
	private static final String EAST = "east";
	private static final String WEST = "west";
	private static final String SOUTHWEST = "southwest";
	private static final String SOUTHEAST = "southeast";
	private static final String NORTHEAST = "northeast";
	private static final String NORTHWEST = "northwest";
	private static final String UP = "up";
	private static final String DOWN = "down";

	public static void main(String[] args) {
		DirectedGraph<Room, DirectionEdge<Room>> graph = new DirectedMultigraph<Room, DirectionEdge<Room>>(
				new ClassBasedEdgeFactory<Room, DirectionEdge<Room>>(
						(Class<? extends DirectionEdge<Room>>) DirectionEdge.class));

		Room room0, room1, room2; // pretend they're actual rooms
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(room0);
		rooms.add(room1);
		rooms.add(room2);

		// add all rooms as vertexes in graph, with no connecting edges
		for (Room r : rooms) {
			graph.addVertex(r);
		}

		/*
		 * sample map room1 / | / | room0 ---room2
		 */
		// room0 connects to room1 by going northeast
		graph.addEdge(room0, room1, NORTHEAST);
		// and room1 back to room0 by going southwest
		graph.addEdge(room1, room0, SOUTHWEST);
		// room0 connects to room2 by going east
		graph.addEdge(room0, room2, EAST);
		// and room2 back to room0 by going west
		graph.addEdge(room2, room0, WEST);
		// room2 connects to room1 by going north
		graph.addEdge(room2, room1, NORTH);
		// and room1 back to room2 by going south
		graph.addEdge(room1, room2, SOUTH);

		// let's assume we're in room0, so we have two exits to the NE and E
		Room currentRoom = room0;
		System.out.println("Current Room: " + currentRoom.toString());

		// we can encapsulate all of the following to a getExits method
		// this (should) returns a set of outgoing edges. in this case, should
		// be two DirectionEdges.
		// TODO: Write method to return a HashMap of the form:
		// <DirectionEdge.Direction, someDirectionEdge>;
		HashMap<String, DirectionEdge<Room>> exits = (HashMap<String, DirectionEdge<Room>>) graph
				.outgoingEdgesOf(currentRoom);
		Set set = exits.keySet();
		Iterator it = set.iterator();
		System.out.println("Obvious exits: ");
		while (it.hasNext()) {
			Object o = it.next();
			System.out.print(o.toString() + "  ");
		}
		/*
		 * Output should be:* Obvious exits: NORTHEAST EAST
		 */
		// let's assume they go EAST.
		String targetDirection = EAST;

		// we need to update currentLocation to the vertex (Room) that is the
		// Target of the edge labeled EAST
		// first we check our hashmap for the key targetDirection
		// targetPath will equal the path keyed by targetDirection or NULL if it
		// doesn't exist.
		// then update currentLocation using targetPath.getDestination if
		// targetDirection is valid.
		DirectionEdge targetPath = exits.get(targetDirection);
		if (targetPath != null) {
			Room currentLocation = (Room) targetPath.getDestination();
			System.out.println("Current Room: " + currentRoom.toString());
		} else {
			System.out.println("There is no exit in that direction");
		}
		
		DirectionEdge<Room> de = new DirectionEdge<Room>(room1, room2, EAST);

	}

	public static class DirectionEdge<V> extends DefaultEdge {
		private V v1;
		private V v2;
		private String direction;

		public DirectionEdge(V v1, V v2, String direction) {
			this.v1 = v1;
			this.v2 = v2;
			this.direction = direction;
		}

		public V getSource() {
			return v1;
		}

		public V getDestination() {
			return v2;
		}

		public String toString() {
			return direction;
		}
	}
}