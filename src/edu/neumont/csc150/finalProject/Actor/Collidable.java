package edu.neumont.csc150.finalProject.Actor;

import java.awt.Rectangle;

public interface Collidable {
	
	/**
	 * @return the bounds of this object
	 */
	public Rectangle getBounds();
	
	/**
	 * @return whether or not to have intersecting collidables resolve on the vertical axis
	 */
	public boolean doVerticalCollisionResolution();
	
	/**
	 * @return whether or not to have intersecting collidables resolve on the horizontal axis
	 */
	public boolean doHorizontalCollisionResolution();
	
	/**
	 * @return the velocity at which a collidable should resolve away from this collidable
	 */
	public double getResolveVal();
	
	/**
	 * @return whether or not this collidable can be jumped on or stood on
	 */
	public boolean isGround();
}
