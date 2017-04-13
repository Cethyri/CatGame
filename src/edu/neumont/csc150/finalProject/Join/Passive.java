package edu.neumont.csc150.finalProject.Join;

public enum Passive {
	SPEED_UP("Speed Up"),
	STRENGTH_UP("Strength Up"),
	HEALTH_UP("Health Up"),
	DOUBLE_JUMP("Double Jump");
	
	private final String Name;
	
	private Passive(String Name) {
		this.Name = Name;
	}

	@Override
	public String toString() {
		
		return Name;
	}
}
