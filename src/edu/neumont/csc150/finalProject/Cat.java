package edu.neumont.csc150.finalProject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Cat extends JLabel implements KeyListener, TickListener, Collidable, Attackable, KeyCodeListener {

	public final PlayerID id;

	private static final int TIME_FOR_FRAME = 200;
	private int frame, frameDelay;
	
	public static final double DX_VEL = 2, JUMP_VEL = 20, RESOLVE_VAL = DX_VEL / 2;
	private double dx, dy, moveDx, moveDy, dxEffect, dyEffect, posY, posX;
	private boolean left, right, up, down;
	private String action, direction;
	
	public final Attack slash;
	public final int maxHealth;
	private int health;

	
	public Cat(PlayerID id) {
		
		this.id = id;
		
		slash = new Attack(0, 0, 5, id, 32, 32);
		maxHealth = 20;
		
		initVars(id);

		initUI();
	}

	private void initVars(PlayerID id) {

		frameDelay = 0;
		frame = 0;
		
		
		resetDx();
		resetDy();
		moveDx = 0;
		moveDy = 0;
		dxEffect = 0;
		dyEffect = 0;
		
		posX = id.startX;
		posY = 0;
		
		left = false;
		right = false;
		up = false;
		down = false;
		
		action = "Idle";
		direction = "Right";
		
		health = maxHealth;
		
		doTick();
	}

	private void initUI() {

		this.setOpaque(true);
		this.setBackground(id.getPlayerColor());
		setBounds(getIntPosX(), getIntPosY(), getIcon().getIconWidth(), getIcon().getIconHeight());
	}

	private void setVelocity() {
		moveDx = left ? -DX_VEL : right ? DX_VEL : moveDx * (isGrounded() ? .85 : .95);
		dx = moveDx + dxEffect;
		dxEffect *= (isGrounded() ? .85 : .95);
		
		moveDy = up && isGrounded() ? -JUMP_VEL : moveDy;
		moveDy += dyEffect;
		dyEffect = 0;
		dy = moveDy;
		
		if (!isGrounded()) {
			moveDy -= Stage.GRAV;
		}
	}

	@Override
	public void checkForCollisions(ArrayList<Collidable> surfaces, boolean resolve) {
		Rectangle internalX, internalY, internalBoth, external;

		for (Collidable collidable : surfaces) {
			external = new Rectangle(collidable.getBounds());
			
			if (!this.getBounds().equals(collidable.getBounds())) {
				if (!external.intersects(this.getBounds()) && !(collidable instanceof Cat)) {
					
					internalX = new Rectangle(getBounds());
					internalX.setLocation(round(posX + dx), getIntPosY());
					if (external.intersects(internalX)) {
						setPosX(dx > 0 ? external.getX() - internalX.getHeight() : external.getMaxX());
						resetDx();
					}
					
					internalY = new Rectangle(getBounds());
					internalY.setLocation(getIntPosX(), round(posY + dy));
					if (external.intersects(internalY)) {
						setPosY(dy > 0 ? external.getY() - internalY.getHeight() : external.getMaxY());
						resetDy();
					}
					
					internalBoth = new Rectangle(getBounds());
					internalBoth.setLocation(round(posX + dx), round(posY + dy));
					if (external.intersects(internalBoth) && dx != 0 && dy !=0) {
						setPosX(dx > 0 ? external.getX() - internalX.getHeight() : external.getMaxX());
						setPosY(dy > 0 ? external.getY() - internalY.getHeight() : external.getMaxY());
						dx = dx > dy ? dx : 0;
						dy = dy > dx ? dy : 0;
					}
					
				} else if (external.intersects(this.getBounds()) && (resolve)) {
					this.resolveCollision(collidable);
					collidable.resolveCollision(this);
				}
			}
		}
	}

	private void resetDy() {
		moveDy = 0;
		dyEffect = 0;
		dy = 0;
	}

	private void resetDx() {
		moveDx = 0;
		dxEffect = 0;
		dx = 0;
	}

	@Override
	public boolean doHorizontalCollisionResolution() {
		return true;
	}
	
	@Override
	public boolean doVerticalCollisionResolution() {
		return false;
	}

	@Override
	public void resolveCollision(Collidable c) {
		double posDx, negDx, posDy, negDy, newDx, newDy;
		posDx = c.getBounds().getMaxX() - this.getBounds().getX();
		negDx = c.getBounds().getX() - this.getBounds().getMaxX();
		
		posDy = c.getBounds().getMaxY() - this.getBounds().getY();
		negDy = c.getBounds().getY() - this.getBounds().getMaxY();
		
		newDx = posDx < Math.abs(negDx) ? posDx : negDx;
		newDy = posDy < Math.abs(negDy) ? posDy : negDy;
		newDx *= c.getResolveVal();
		newDy *= c.getResolveVal();
		
		if (c.doHorizontalCollisionResolution() && (Math.abs(newDx) < Math.abs(newDy) || !c.doVerticalCollisionResolution())) {
			dx = (newDx / c.getBounds().getWidth()) + dx;
		}
		if (c.doVerticalCollisionResolution() && (Math.abs(newDy) < Math.abs(newDx) || !c.doHorizontalCollisionResolution())) {
			dy = (newDy / c.getBounds().getHeight()) + dy;			
		}
	}
	
	@Override
	public boolean isGround() {
		return false;
	}
	
	public double getResolveVal() {
		return RESOLVE_VAL;
	}

	private void doMove() {
		setPosX(posX + dx);
		setPosY(posY + dy);

	}

	public boolean isGrounded() {
		for (Collidable collidable : Stage.getSurfaces()) {
			if (this.getBounds().getMaxY() == collidable.getBounds().getY() && this.getBounds().getX() < collidable.getBounds().getMaxX()
					&& this.getBounds().getMaxX() > collidable.getBounds().getX() && collidable.isGround()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void receiveAttack(Attack atk) {
		if (!atk.hasHit(id)) {
			atk.addHasBeenHit(id);
			health -= atk.damage;
			dxEffect += atk.getDxEffect();
			dyEffect += atk.getDyEffect();
		}
		
	}
	
	@Override
	public void checkForAttack(ArrayList<Attack> attacks) {
		for (Attack attack : attacks) {
			if (this.getBounds().intersects(attack.getBounds())) {
				receiveAttack(attack);				
			}
		}
		
	}

	private void setPosX(double posX) {
		this.posX = posX;
		resetLocation();
	}

	private void setPosY(double posY) {
		this.posY = posY;
		resetLocation();
	}

	private void resetLocation() {
		this.setLocation(getIntPosX(), getIntPosY());
	}

	private int getIntPosX() {
		return round(posX);
	}

	private int getIntPosY() {
		return round(posY);
	}

	private int round(double d) {
		int r = (int) d;
		r += (d - ((int) d) >= .5) ? 1 : 0;
		return r;
	}

	@Override
	public void doTick() {

		checkForAttack(Stage.getAttacks());
		setVelocity();
		
		checkForCollisions(Stage.getSurfaces(), true);
		checkForCollisions(Stage.getSurfaces(), false);
		animate();
		doMove();
	}

	private void animate() {
		
		direction = right ? "Right" : left ? "Left" : direction;
		action = (right || left) && dx != 0 ?  "Walk" : "Idle";
		this.setIcon(new ImageIcon("images/Cat/" + action + "_" + direction + "_" + frame + ".png"));

		frameDelay++;
		if (frameDelay >= TIME_FOR_FRAME / Stage.tickLength) {
			frame++;
			if (frame >= 4) {
				frame = 0;
			}	
			frameDelay = 0;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
	}
	
	public void keyPressedByCode(int key) {
		if (key == id.getLeft()) {
			left = true;
		} else if (key == id.getRight()) {
			right = true;
		}
		
		if (key == id.getUp()) {
			up = true;
		}
		if (key == id.getDown()) {
			down = true;
		}
	}
	
	public void keyReleasedByCode(int key) {
		if (key == id.getLeft()) {
			left = false;
		} else if (key == id.getRight()) {
			right = false;
		}
		
		if (key == id.getUp()) {
			up = false;
		}
		if (key == id.getDown()) {
			down = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		keyPressedByCode(key);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		keyReleasedByCode(key);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
