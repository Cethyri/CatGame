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

public class Kitten extends JLabel implements KeyListener, Collidable, TickListener {
	
	public final PlayerID id;
	
	private int frame;
	
	public static final double dxVel = 2, jumpVel = 2;
	private double dx, dy, posY, posX;
	private boolean left, right, up, down;
	
	public Kitten(PlayerID id) {
		this.id = id;
		initVars(id);
		
		initUI();
	}

	private void initVars(PlayerID id) {
		
		frame = 0;
		doTick();
	}

	private void initUI() {
		
		this.setOpaque(true);
		this.setBackground(id.getPlayerColor());
		setBounds(0, 0, getIcon().getIconWidth(), getIcon().getIconHeight());
	}
	
	private void setVelocity() {
		dx = left ? -dxVel : right ? dxVel : 0;
		dy = up ? -jumpVel : dy;
		if (!isGrounded()) {
			dy -= Stage.GRAV;
		}
	}
	
	@Override
	public void checkForCollisions(ArrayList<Collidable> surfaces) {
		Rectangle internalX, internalY, external;
		internalX = new Rectangle(getBounds());
		internalX.setLocation(getPosX() + getDX(), getPosY());
		
		internalY = new Rectangle(getBounds());
		internalY.setLocation(getPosX(), getPosY() + getDY());
		
		for (Collidable collidable : surfaces) {
			external = new Rectangle(collidable.getBounds());
			if (!external.intersects(this.getBounds())) {
				if (external.intersects(internalX)) {
					posX = dx > 0 ? external.getX() - internalX.getHeight() : external.getMaxX();
					setPosX(posX);
					dx = 0;
				}
				if (external.intersects(internalY)) {
					posY = dy > 0 ? external.getY() - internalY.getHeight() : external.getMaxY();
					setPosY(posY);
					dy = 0;
				}
			}
		}
	}
	
	private void doMove() {
		setPosX(posX + dx);
		setPosY(posY + dy);
		
	}
	
	public boolean isGrounded() {
		for (Collidable collidable : Stage.getSurfaces()) {
			if (this.getBounds().getMaxY() == collidable.getBounds().getX()) {
				return true;
			}
		}
		return false;
	}
	
	protected void setPosX(double posX) {
		this.posX = posX;
		resetLocation();
	}

	protected void setPosY(double posY) {
		this.posY = posY;
		resetLocation();
	}

	private void resetLocation() {
		this.setLocation(getPosX(), getPosY());
	}

	private int getPosX() {
		return round(posX);
	}
	
	private int getPosY() {
		return round(posY);
	}
	
	private int getDX() {
		return round(dx);
	}
	
	private int getDY() {
		return round(dy);
	}
	
	private int round(double d) {
		int r = (int) d;
		r += (d - ((int) d) >= .5) ? 1 : 0;
		return r;
	}

	@Override
	public boolean isMovable() {
		return true;
	}
	
	@Override
	public void doTick() {
		animate();
		setVelocity();
		checkForCollisions(Stage.getSurfaces());
		doMove();
	}

	private void animate() {
		this.setIcon(new ImageIcon("images/Cat_Left_" + frame + ".png"));
		
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
