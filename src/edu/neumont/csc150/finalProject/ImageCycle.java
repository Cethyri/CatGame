package edu.neumont.csc150.finalProject;

public enum ImageCycle {
	WALK_LEFT(true, false, false, false);
	
	public final boolean LEFT, RIGHT, UP, DOWN;
	
	private ImageCycle(boolean LEFT, boolean RIGHT, boolean UP, boolean DOWN) {
		this.LEFT = LEFT;
		this.RIGHT = RIGHT;
		this.UP = UP;
		this.DOWN = DOWN;
	}
}
