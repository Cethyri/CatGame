package edu.neumont.csc150.finalProject;

import java.awt.Rectangle;
import java.util.ArrayList;

public interface Collidable {
	
	public Rectangle getBounds();
	
	public boolean doVerticalCollisionResolution();
	public boolean doHorizontalCollisionResolution();
	
	public double getResolveVal();
	
	public boolean isGround();
}
