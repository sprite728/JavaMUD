package com.mud.worldmodel;
import org.jgrapht.*;
import org.jgrapht.graph.*;
public class GraphTest {

	/**
	 * @param args are ignored
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DirectedMultigraph<Room, DirectionEdge> graph = new DirectedMultigraph<Room, DirectionEdge>(new ClassBasedEdgeFactory<Room,DirectionEdge>(DirectionEdge.class));
		
	}

}
