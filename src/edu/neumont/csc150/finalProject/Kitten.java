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

public class Kitten extends JLabel implements KeyListener, TickListener, Collidable {

	public final PlayerID id;

	private int frame;

	public static final double dxVel = 2, jumpVel = 15, resolveFloat = dxVel / 4;
	
	private double dx, dy, posY, posX;
	private boolean left, right, up, down;

	public Kitten(PlayerID id) {
		this.id = id;
		initVars(id);

		initUI();
	}

	private void initVars(PlayerID id) {

		frame = 0;
		
		dx = 0;
		dy = 0;
		
		posX = id.startX;
		posY = 0;
		
		left = false;
		right = false;
		up = false;
		down = false;
		
		doTick();
	}

	private void initUI() {

		this.setOpaque(true);
		this.setBackground(id.getPlayerColor());
		setBounds(getIntPosX(), getIntPosY(), getIcon().getIconWidth(), getIcon().getIconHeight());
	}

	private void setVelocity() {
		dx = left ? -dxVel : right ? dxVel : 0;
		dy = up && isGrounded() ? -jumpVel : dy;
		if (!isGrounded()) {
			dy -= Stage.GRAV;
		}
	}

	@Override
	public void checkForCollisions(ArrayList<Collidable> surfaces) {
		Rectangle internalX, internalY, external;

		for (Collidable collidable : surfaces) {
			external = new Rectangle(collidable.getBounds());
			
			if (!this.getBounds().equals(collidable.getBounds())) {
				if (!external.intersects(this.getBounds()) && !(collidable instanceof Kitten)) {
					
					internalX = new Rectangle(getBounds());
					internalX.setLocation(getIntPosX() + getIntDx(), getIntPosY());
					if (external.intersects(internalX)) {
						setPosX(dx > 0 ? external.getX() - internalX.getHeight() : external.getMaxX());
						dx = 0;
					}
					
					internalY = new Rectangle(getBounds());
					internalY.setLocation(getIntPosX(), getIntPosY() + getIntDy());
					if (external.intersects(internalY)) {
						setPosY(dy > 0 ? external.getY() - internalY.getHeight() : external.getMaxY());
						dy = 0;
					}
				} else if (external.intersects(this.getBounds())) {
					this.resolveCollision(collidable);
					collidable.resolveCollision(this);
				}
			}
		}
	}

	@Override
	public boolean doVerticalCollisionResolution() {
		return false;
	}

	@Override
	public boolean doHorizontalCollisionResolution() {
		return true;
	}

	@Override
	public void resolveCollision(Collidable collidable) {
		double posDx, negDx, posDy, negDy, newDx, newDy;
		posDx = collidable.getBounds().getMaxX() - this.getBounds().getX();
		negDx = collidable.getBounds().getX() - this.getBounds().getMaxX();

		posDy = collidable.getBounds().getMaxY() - this.getBounds().getY();
		negDy = collidable.getBounds().getY() - this.getBounds().getMaxY();

		newDx = posDx < Math.abs(negDx) ? resolveFloat : -resolveFloat;
		newDy = posDy < Math.abs(negDy) ? resolveFloat : -resolveFloat;

		dx = doHorizontalCollisionResolution() ? newDx + dx : dx;
		dy = doVerticalCollisionResolution() ? newDy + dx : dy;
	}

	private void doMove() {
		setPosX(posX + dx);
		setPosY(posY + dy);

	}

	public boolean isGrounded() {
		for (Collidable collidable : Stage.getSurfaces()) {
			if (this.getBounds().getMaxY() == collidable.getBounds().getY()) {
				return this.getBounds().getX() < collidable.getBounds().getMaxX()
						&& this.getBounds().getMaxX() > collidable.getBounds().getX();
			}
		}
		return false;
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

	private int getIntDx() {
		return round(dx);
	}

	private int getIntDy() {
		return round(dy);
	}

	private int round(double d) {
		int r = (int) d;
		r += (d - ((int) d) >= .5) ? 1 : 0;
		return r;
	}

	@Override
	public void doTick() {
		animate();
		setVelocity();
		checkForCollisions(Stage.getSurfaces());
		doMove();
	}

	private void animate() {
		this.setIcon(new ImageIcon("images/Cat_Left_Idle_" + frame + ".png"));

		frame++;
		if (frame >= 4) {
			frame = 0;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

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

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
