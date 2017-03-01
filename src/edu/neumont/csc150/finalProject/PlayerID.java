package edu.neumont.csc150.finalProject;

import java.awt.Color;
import java.awt.event.KeyEvent;

public enum PlayerID {
	FIRST(new Color(102, 0, 0), KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, 0),
	SECOND(new Color(0, 102, 102), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, MainFrame.CONTENT_WIDTH / 4),
	THIRD(new Color(204, 102, 0), KeyEvent.VK_T, KeyEvent.VK_G, KeyEvent.VK_F, KeyEvent.VK_H, MainFrame.CONTENT_WIDTH / 4 * 2),
	FOURTH(new Color(102, 0, 204), KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L, MainFrame.CONTENT_WIDTH / 4 * 3);
	
	public final int startX;
	
	private Color playerColor;
	private int up, down, left, right;
	
	private PlayerID(Color playerColor, int up, int down, int left, int right, int startX) {
		this.playerColor = playerColor;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		this.startX = startX;
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
