package edu.neumont.csc150.finalProject;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Cat extends JLabel implements KeyListener, TickListener, Collidable, Movable, Attackable {

	public final PlayerID id;

	public static final int TIME_FOR_FRAME = 200, FRAMES_FOR_ANIM = 4;
	private int frame, frameDelay;

	public static final double DX_VEL = 2, JUMP_VEL = 20, RESOLVE_VAL = DX_VEL / 2;
	private double dx, dy, moveDx, moveDy, dxEffect, dyEffect, posY, posX;
	private boolean left, right, up, down;
	private String action, direction;

	public final Attack slash;
	public final int maxHealth;
	private int health;
	
	private final StatPanel stats;

	/**
	 * creates a new cat and initializes id so cat knows it's start position, player color, and controls
	 * @param id contains start position, player color, and controls
	 */
	public Cat(PlayerID id) {
		
		this.id = id;

		slash = new Attack(10, 5, id, "Right", false, Attack.FRAMES_FOR_ANIM, 64, 64);
		Game.stage.add(slash);
		
		maxHealth = 20;
		
		stats = new StatPanel(this);
		Game.stage.add(stats);

		initVars(id);

		initUI();
	}

	/**
	 * set initial value of variables
	 * @param id
	 */
	private void initVars(PlayerID id) {

		frameDelay = 0;
		frame = 0;

		resetDx();
		resetDy();

		posX = id.startX + MainFrame.CONTENT_WIDTH / 8;
		posY = 0;
		
		resetLocation();

		resetInputs();

		action = "Idle";
		direction = "Right";

		health = maxHealth;

		doTick();
	}

	/**
	 * resets all booleans for inputs to false, used specifically when player dies before reading key releases, and in initVars()
	 */
	private void resetInputs() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	/**
	 * sets the initial component values
	 */
	private void initUI() {

		this.setOpaque(false);
		this.setBackground(id.playerColor);
		setBounds(getIntPosX(), getIntPosY(), getIcon().getIconWidth(), getIcon().getIconHeight());
	}

	/**
	 * Calculates dx and dy based on moveDx (the effect of the players controls on the cat) and dxEffect (the effect from any attacks or external effects). Adds gravity and some gentle resistance.
	 * 
	 * 
	 */
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
	
	/* (non-Javadoc)
	 * @see edu.neumont.csc150.finalProject.Movable#checkForCollisions(java.util.ArrayList, boolean)
	 */
	@Override
	public void checkForCollisions(ArrayList<Collidable> surfaces, boolean resolve) {
		Rectangle internalX, internalY, internalBoth, external;

		for (Collidable collidable : surfaces) {
			external = new Rectangle(collidable.getBounds());

			if (!this.getBounds().equals(collidable.getBounds())) {
				if (!external.intersects(this.getBounds()) && !(collidable instanceof Cat)) {

					internalX = new Rectangle(getBounds());
					internalX.setLocation((int) Math.round(posX + dx), getIntPosY());
					if (external.intersects(internalX)) {
						setPosX(dx > 0 ? external.getX() - internalX.getHeight() : external.getMaxX());
						resetDx();
					}

					internalY = new Rectangle(getBounds());
					internalY.setLocation(getIntPosX(), (int) Math.round(posY + dy));
					if (external.intersects(internalY)) {
						setPosY(dy > 0 ? external.getY() - internalY.getHeight() : external.getMaxY());
						resetDy();
					}

					internalBoth = new Rectangle(getBounds());
					internalBoth.setLocation((int) Math.round(posX + dx), (int) Math.round(posY + dy));
					if (external.intersects(internalBoth) && dx != 0 && dy != 0) {
						setPosX(dx > 0 ? external.getX() - internalX.getHeight() : external.getMaxX());
						setPosY(dy > 0 ? external.getY() - internalY.getHeight() : external.getMaxY());
						if (dx < dy) {
							resetDx();
						} else {
							resetDy();
						}
					}

				} else if (external.intersects(this.getBounds()) && (resolve)) {
					this.resolveCollision(collidable);
					if (collidable instanceof Movable) {
						((Movable) collidable).resolveCollision(this);
					}
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.neumont.csc150.finalProject.Movable#resolveCollision(edu.neumont.csc150.finalProject.Collidable)
	 */
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
		
		if (c.doHorizontalCollisionResolution()
				&& (Math.abs(newDx) < Math.abs(newDy) || !c.doVerticalCollisionResolution())) {
			dx = (newDx / c.getBounds().getWidth()) + dx;
		}
		if (c.doVerticalCollisionResolution()
				&& (Math.abs(newDy) < Math.abs(newDx) || !c.doHorizontalCollisionResolution())) {
			dy = (newDy / c.getBounds().getHeight()) + dy;
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.neumont.csc150.finalProject.Collidable#doHorizontalCollisionResolution()
	 */
	@Override
	public boolean doHorizontalCollisionResolution() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see edu.neumont.csc150.finalProject.Collidable#doVerticalCollisionResolution()
	 */
	@Override
	public boolean doVerticalCollisionResolution() {
		return false;
	}

	/**
	 * set dx and all things that effect it to zero
	 */
	private void resetDy() {
		moveDy = 0;
		dyEffect = 0;
		dy = 0;
	}

	/**
	 * set dy and all things that effect it to zero
	 */
	private void resetDx() {
		moveDx = 0;
		dxEffect = 0;
		dx = 0;
	}

	/* (non-Javadoc)
	 * @see edu.neumont.csc150.finalProject.Collidable#isGround()
	 */
	@Override
	public boolean isGround() {
		return false;
	}

	@Override
	public double getResolveVal() {
		return RESOLVE_VAL;
	}

	/**
	 * adds dx to the pos of this cat and has slash update it's position
	 */
	private void doMove() {
		setPosX(posX + dx);
		setPosY(posY + dy);

		slash.updatePosition(this);
	}

	/**
	 * @return if this cat is on solid ground
	 */
	public boolean isGrounded() {
		for (Collidable collidable : Stage.getSurfaces()) {
			if (this.getBounds().getMaxY() == collidable.getBounds().getY()
					&& this.getBounds().getX() < collidable.getBounds().getMaxX()
					&& this.getBounds().getMaxX() > collidable.getBounds().getX() && collidable.isGround()) {
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.neumont.csc150.finalProject.Attackable#checkForAttack(java.util.ArrayList)
	 */
	@Override
	public void checkForAttack(ArrayList<Attack> attacks) {
		for (Attack attack : attacks) {
			if (this.getBounds().intersects(attack.getBounds())) {
				receiveAttack(attack);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.neumont.csc150.finalProject.Attackable#receiveAttack(edu.neumont.csc150.finalProject.Attack)
	 */
	@Override
	public void receiveAttack(Attack atk) {
		if (!atk.owner.equals(id) && !atk.hasHit(id) && atk.isInAction()) {
			atk.addHasBeenHit(id);
			health -= atk.damage;
			
			double effect;
			
			effect = atk.getEffect(this.getBounds(), true);
			dxEffect += effect;
			moveDx = (effect != 0) ? 0 : moveDx;
			
			effect = atk.getEffect(this.getBounds(), false);
			dyEffect += effect;
			moveDy = (effect != 0) ? 0 : moveDy;
		}
		
	}
	
	/**
	 * @return if this cat is alive
	 */
	public boolean isAlive() {
		return health > 0;
	}

	/**
	 * set the field posX and set the location of this JLabel to match
	 * @param posX 
	 */
	private void setPosX(double posX) {
		this.posX = posX;
		resetLocation();
	}

	/**
	 * set the field posY and set the location of this JLabel to match
	 * @param posY
	 */
	private void setPosY(double posY) {
		this.posY = posY;
		resetLocation();
	}

	/**
	 * sets the location of the this JLabel to match the rounded posX and posY
	 */
	private void resetLocation() {
		this.setLocation(getIntPosX(), getIntPosY());
	}

	/**
	 * @return the string representing the direction the cat is facing
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * @return a rounded posX
	 */
	public int getIntPosX() {
		return (int) Math.round(posX);
	}

	/**
	 * @return a rounded posY
	 */
	public int getIntPosY() {
		return (int) Math.round(posY);
	}
	
	/**
	 * @return this cat's health
	 */
	public int getHealth() {
		return health;
	}

	/* (non-Javadoc)
	 * @see edu.neumont.csc150.finalProject.TickListener#doTick()
	 */
	@Override
	public void doTick() {
		if (!isAlive()) {
			resetInputs();
			if (slash.isInAction()) {
				slash.clear();
			}
		}
		
		checkForAttack(Stage.getAttacks());
		setVelocity();

		checkForCollisions(Stage.getSurfaces(), true);
		checkForCollisions(Stage.getSurfaces(), false);

		animate();
		doMove();
	}

	/**
	 * sets the frame based on the current direction, action, and frame of the cat
	 */
	private void animate() {

		direction = right ? "Right" : left ? "Left" : direction;
		action = !isAlive() ? "Dead" : ((right || left) && dx != 0 ? "Walk" : "Idle");
		this.setIcon(new ImageIcon("images/Cat/" + action + "_" + direction + "_" + frame + ".png"));

		frameDelay++;
		if (frameDelay >= TIME_FOR_FRAME / Stage.tickLength) {
			frame++;
			if (frame >= FRAMES_FOR_ANIM) {
				frame = 0;
			}
			frameDelay = 0;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(id.playerColor);
		g.setFont(new Font("ARIAL", Font.BOLD, 16));
		g.drawString(id.name(), 0, 16);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (isAlive()) {
			int key = e.getKeyCode();

			if (key == id.left) {
				left = true;
				right = false;
			} else if (key == id.right) {
				right = true;
				left = false;
			}

			if (key == id.x) {
				up = true;
			}

			if (key == id.y) {
				down = true;
			}

			if (key == id.a) {
				if (!slash.isInAction()) {
					slash.create(direction);
				}
			}

			System.out.println("pressed " + KeyEvent.getKeyText(key));

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (isAlive()) {
			int key = e.getKeyCode();

			if (key == id.left) {
				left = false;
			} else if (key == id.right) {
				right = false;
			}

			if (key == id.x) {
				up = false;
			}
			if (key == id.y) {
				down = false;
			}

			System.out.println("released " + KeyEvent.getKeyText(key));

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
