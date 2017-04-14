package edu.neumont.csc150.finalProject.Join;

public enum Passive {
	NONE("NONE", "none"),
	SPEED_UP("Speed Up", "Speed"),
	STRENGTH_UP("Strength Up", "Goku"),
	HEALTH_UP("Health Up", "Health"),
	DOUBLE_JUMP("Double Jump", "Genji");
	
	public final String name;
	public final String imagePart;
	
	private Passive(String name, String imagePart) {
		this.name = name;
		this.imagePart = imagePart;
	}

	@Override
	public String toString() {
		
		return name;
	}
}
