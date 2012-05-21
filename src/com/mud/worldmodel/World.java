package com.mud.worldmodel;

import java.util.Iterator;

import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DirectedMultigraph;

import com.mud.entities.Player;

public class World {

	private static DirectedMultigraph<Room, DirectionEdge> rooms;

	public World() {
		rooms = new DirectedMultigraph<Room, DirectionEdge>(
				new ClassBasedEdgeFactory<Room, DirectionEdge>(
						DirectionEdge.class));
	}

}
