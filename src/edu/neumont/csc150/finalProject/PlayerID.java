package edu.neumont.csc150.finalProject;

import java.awt.Color;
import java.awt.event.KeyEvent;

public enum PlayerID {
	FIRST(new Color(102, 0, 0), KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D),
	SECOND(new Color(0, 102, 102), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT),
	THIRD(new Color(204, 102, 0), KeyEvent.VK_T, KeyEvent.VK_G, KeyEvent.VK_F, KeyEvent.VK_H),
	FOURTH(new Color(102, 0, 204), KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L);
	
	private Color playerColor;
	private int up, down, left, right;
	
	private PlayerID(Color playerColor, int up, int down, int left, int right) {
		this.playerColor = playerColor;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}

	public Color getPlayerColor() {
		return playerColor;
	}

	public int getUp() {
		return up;
	}

	public int getDown() {
		return down;
	}

	public int getLeft() {
		return left;
	}

	public int getRight() {
		return right;
	}
}
