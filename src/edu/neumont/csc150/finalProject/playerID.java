package edu.neumont.csc150.finalProject;

import java.awt.Color;

public enum playerID {
	FIRST(new Color(102, 0, 0)),
	SECOND(new Color(0, 102, 102)),
	THIRD(new Color(204, 102, 0)),
	FOURTH(new Color(102, 0, 204));
	
	private Color playerColor;
	
	private playerID(Color playerColor) {
		this.playerColor = playerColor;
	}

	public Color getPlayerColor() {
		return playerColor;
	}
}
