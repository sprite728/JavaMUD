package com.mud.worldmodel;

import org.jgrapht.graph.DefaultEdge;

public class DirectionEdge extends DefaultEdge{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1890531751967298436L;
	private String direction;	
	
	public DirectionEdge(){
		super();
	}
	
	/**
	 * @param direction the direction to set
	 */
	public DirectionEdge(String d){
		this();
		setDirection(d);
	}	
	
	
	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	

}
