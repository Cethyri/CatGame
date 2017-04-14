package edu.neumont.csc150.finalProject.Join;

public enum Aggressive {
	NONE("NONE", "none"),
	AOE("AOE Attack", "AOE"),
	BACKSTEP("Backstep Attack", "BackStep"),
	DASH("Dash Attack", "Dash"),
	SLAM("Slam Attack", "Slam");
	
	public final String name;
	public final String imagePart;
	
	private Aggressive(String name, String imagePart) {
		this.name = name;
		this.imagePart = imagePart;
	}

	@Override
	public String toString() {
		
		return name;
	}
}
