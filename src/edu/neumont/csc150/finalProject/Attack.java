package edu.neumont.csc150.finalProject;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Attack extends JLabel implements TickListener {
	
	public static final int TIME_FOR_FRAME = 30, FRAMES_FOR_ANIM = 11;
	public final int lifeInFrames;
	private int frame, frameDelay, lifeFrames;
	
	public final double effectVal;
	public final int damage;
	
	private boolean inAction;
	
	private double posY, posX;
	
	public final PlayerID owner;
	
	private String direction;
	
	private ArrayList<PlayerID> hasBeenHit;
	
	public Attack(double effectVal, int damage, PlayerID owner, String direction, boolean inAction, int lifeInFrames, int width, int height) {
		this.effectVal = effectVal;
		this.damage = damage;
		this.owner = owner;
		this.lifeInFrames = lifeInFrames;
		
		initVars(direction, inAction);
		initUI(500, 500, width, height);
	}
	
	private void initVars(String direction, boolean inAction) {
		this.direction = direction;
		this.inAction = inAction;
		
		frameDelay = 0;
		frame = 0;
		lifeFrames = 0;
		
		posX = 0;
		posY = 0;
				
		hasBeenHit = new ArrayList<>();
	}

	private void initUI(int x, int y, int width, int height) {
		setOpaque(true);
		setBackground(Color.RED);
		setBounds(x, y, width, height);
	}
	
	public void addHasBeenHit(PlayerID id) {
		if (isStageHazard()) {
			hasBeenHit.add(id);
		}
	}

	private boolean isStageHazard() {
		return lifeInFrames != -1;
	}
	
	public boolean isInAction() {
		return inAction;
	}
	
	public boolean hasHit(PlayerID id) {
		
		return hasBeenHit.contains(id);
	}

	public double getEffect(Rectangle toEffect, boolean isX) {
		double xDist, yDist, hypot, mod, effect;
		
		xDist = Math.round(toEffect.getCenterX()) - Math.round(this.getBounds().getCenterX());
		
		yDist = Math.round(toEffect.getCenterY()) - Math.round(this.getBounds().getCenterY());
		
		hypot = Math.hypot(Math.abs(xDist), Math.abs(yDist));
		
		mod = hypot / effectVal;
		
		effect = isX ? (xDist / mod) : (yDist / mod);
		return effect;
	}

	public void updatePosition(Cat owner) {
		int x, y;
		
		direction = owner.getDirection();
		
		x = owner.getIntPosX() + (direction.equals("Left") ? -this.getWidth() : owner.getWidth());
		y = owner.getIntPosY() + 16;
		
		this.setLocation(x + getIntPosX(), y + getIntPosY());
	}
	
	public int getIntPosX() {
		return (int) Math.round(posX);
	}

	public int getIntPosY() {
		return (int) Math.round(posY);
	}
	

	private void animate() {
		
		this.setIcon(new ImageIcon("images/Slash/" + direction + "_" + frame + ".png"));

		frameDelay++;
		if (frameDelay >= TIME_FOR_FRAME / Stage.tickLength) {
			frame++;
			lifeFrames ++;
			if (frame >= FRAMES_FOR_ANIM) {
				frame = 0;
			}	
			frameDelay = 0;
		}
	}
	
	public void create(String direction) {
		initVars(direction, true);
		Game.stage.add(this);
	}
	
	public void clear() {
		inAction = false;
		Game.stage.remove(this);
	}
	
	@Override
	public void doTick() {
		if (inAction) {
			animate();			
			
			if (isStageHazard()) {
				if (lifeFrames > lifeInFrames) {
					clear();
				}
			}
		}
	}
}
