package edu.neumont.csc150.finalProject.Actor.Player;

import java.awt.Color;
import java.awt.event.KeyEvent;

import edu.neumont.csc150.finalProject.Join.Aggressive;
import edu.neumont.csc150.finalProject.Join.Passive;
import edu.neumont.csc150.finalProject.Main.MainFrame;

public enum PlayerID {
	//@@@@ "An enum with custom data elements."
	
	FIRST(new Color(102, 0, 0), KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_PAGE_UP, KeyEvent.VK_PAGE_DOWN, 0),
	SECOND(new Color(0, 102, 102), KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_E, MainFrame.CONTENT_WIDTH / 4),
	THIRD(new Color(204, 102, 0), KeyEvent.VK_F, KeyEvent.VK_H, KeyEvent.VK_T, KeyEvent.VK_G, KeyEvent.VK_R, KeyEvent.VK_Y, MainFrame.CONTENT_WIDTH / 4 * 2),
	FOURTH(new Color(102, 0, 204), KeyEvent.VK_J, KeyEvent.VK_L, KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_U, KeyEvent.VK_O, MainFrame.CONTENT_WIDTH / 4 * 3);
	
	public final int startX;
	
	public final Color playerColor;
	public final int left, right, x, y, a, b;
	
	private Passive buff;
	private Aggressive special;
	
	private PlayerID(Color playerColor, int left, int right, int x, int y, int a, int b, int startX) {
		this.playerColor = playerColor;
		this.left = left;
		this.right = right;
		this.x = x;
		this.y = y;
		this.a = a;
		this.b = b;
		this.startX = startX;
		
		buff = Passive.NONE;
		special = Aggressive.NONE;
	}
	
	public void setBuff(Passive buff) {
		this.buff = buff;
	}
	
	public void setSpecial(Aggressive special) {
		this.special = special;
	}
	
	public Passive getBuff() {
		return buff;
	}
	
	public Aggressive getSpecial() {
		return special;
	}
	
	public static int translate(String button, int ordinal) {
		int keyCode = -1;
		
		if (ordinal >= 0 && ordinal < values().length) {
			switch (button) {
			case "left":
				keyCode = values()[ordinal].left;
				break;
			case "right":
				keyCode = values()[ordinal].right;
				break;
			case "x":
				keyCode = values()[ordinal].x;
				break;
			case "y":
				keyCode = values()[ordinal].y;
				break;
			case "a":
				keyCode = values()[ordinal].a;
				break;
			case "b":
				keyCode = values()[ordinal].b;
				break;
			default:
				break;
			}			
		}
		
		return keyCode;
	}
	
	public static PlayerID findWhich(KeyEvent e) {
		
		for (PlayerID id: PlayerID.values()) {
			if (e.equals(id.a)) {
				return id;
			}
		}
		
		return null;
	}
}
