package edu.neumont.csc150.finalProject.Actor.Player;

import java.util.ArrayList;

import edu.neumont.csc150.finalProject.Actor.Collidable;

public interface Movable {

	/**
	 * 
	 * checks for collisions between this and collidables, either collides or resolves if intersecting
	 * @param surfaces is the array of objects to check collisions with
	 * @param resolve determines whether or not to resolve intersections
	 */
	public void checkForCollisions(ArrayList<Collidable> surfaces, boolean resolve);
	
	
	/**
	 * resolves an intersection between this and collidable
	 * @param collidable is the intersection surface
	 */
	public void resolveCollision(Collidable collidable);
}
