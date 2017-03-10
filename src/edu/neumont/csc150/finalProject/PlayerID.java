package edu.neumont.csc150.finalProject;

import java.awt.Color;
import java.awt.event.KeyEvent;

public enum PlayerID {
	FIRST(new Color(102, 0, 0), KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_Q, KeyEvent.VK_E, 0),
	SECOND(new Color(0, 102, 102), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_PAGE_DOWN, KeyEvent.VK_PAGE_DOWN, MainFrame.CONTENT_WIDTH / 4),
	THIRD(new Color(204, 102, 0), KeyEvent.VK_T, KeyEvent.VK_G, KeyEvent.VK_F, KeyEvent.VK_H, KeyEvent.VK_U, KeyEvent.VK_O, MainFrame.CONTENT_WIDTH / 4 * 2),
	FOURTH(new Color(102, 0, 204), KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L, KeyEvent.VK_R, KeyEvent.VK_Y, MainFrame.CONTENT_WIDTH / 4 * 3);
	
	public final int startX;
	
	public final Color playerColor;
	public final int up, down, left, right, attack, special;
	
	private PlayerID(Color playerColor, int up, int down, int left, int right, int attack, int special, int startX) {
		this.playerColor = playerColor;
		this.up = up;
		this.down = down;
		this.left = left;
		this.attack = attack;
		this.special = special;
		this.right = right;
		this.startX = startX;
	}

//	public Color getPlayerColor() {
//		return playerColor;
//	}
//
//	public int getUp() {
//		return up;
//	}
//
//	public int getDown() {
//		return down;
//	}
//
//	public int getLeft() {
//		return left;
//	}
//
//	public int getRight() {
//		return right;
//	}
//	
//	public int getAttack() {
//		return attack;
//	}
//
//	public int getSpecial() {
//		return special;
//	}
	
	public static int translate(String button, int ordinal) {
		int keyCode = -1;
		
		if (ordinal >= 0 && ordinal < values().length) {
			switch (button) {
			case "up":
				keyCode = values()[ordinal].up;
				break;
			case "down":
				keyCode = values()[ordinal].down;
				break;
			case "left":
				keyCode = values()[ordinal].left;
				break;
			case "right":
				keyCode = values()[ordinal].right;
				break;
			case "attack":
				keyCode = values()[ordinal].attack;
				break;
			case "special":
				keyCode = values()[ordinal].special;
				break;
			default:
				break;
			}			
		}
		
		return keyCode;
	}
}
