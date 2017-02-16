package edu.neumont.csc150.finalProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Kitten implements KeyListener {

	private static final int PLAYER_WIDTH = 64 * Finals.PIXEL_RATIO, PLAYER_HEIGHT = 64 * Finals.PIXEL_RATIO;

	private static final int JUMP_VEL = -25, WALK_VEL = 5;

	private static final String PATH = "Images/Useable/", END = ".png", PLACE_HOLDER = "catTemp", IDLE = "Idle", RIGHT = "Right", LEFT = "Left", WALK = "Walk", DUCK = "Duck", JUMP = "Jump", FALL = "Fall";

	private final playerID ID;

	private int posx, posy, dx, dy, collideDx, collideDy;
	
	private boolean stopDx, stopDy;
	
	private boolean jump, duck, left, right;

	private int frame, frameDelay;
	private Image sprite;

	public Kitten(int startX, int startY, playerID ID) {

		this.ID = ID;
		
		posx = startX;
		posy = startY - PLAYER_HEIGHT;

		dx = 0;
		dy = 0;
		collideDx = dx;
		collideDy = dy;
		
		stopDx = false;
		stopDy = false;
		
		jump = false;
		duck = false;
		left = false;
		right = false;

		frame = 0;
		frameDelay = Finals.animDelay;
		sprite = new ImageIcon(PATH + PLACE_HOLDER + frame + END).getImage();
	}

	public void move(ArrayList<Rectangle> surfaces) {
		doHorizontal(surfaces);

		doVertical(surfaces);

		animate();
	}

	private void doHorizontal(ArrayList<Rectangle> surfaces) {
		if (left || right) {

			dx = left ? -WALK_VEL : WALK_VEL;

			collisionsHorizontal(getHitBox(), surfaces);
			
			collideDx = dx;
			
			if (stopDx) {
				dx = 0;
				stopDx = false;
			}
			
			posx += collideDx;
		}
	}

	private void doVertical(ArrayList<Rectangle> surfaces) {
		if (jump) {
			jump = false;
			if (onGround(getHitBox(), surfaces)) {
				dy += JUMP_VEL;				
			}
		}
		
		collisionsVertical(getHitBox(), surfaces);
		
		collideDy = dy;

		if (stopDy) {
			dy = 0;
			stopDy = false;
		}
		
		posy += collideDy;
		
		if (collideDy != dy) {
			dy = 0;
		}
		
		if (!onGround(getHitBox(), surfaces)) {
			dy -= Finals.GRAV;			
		}
	}


	public boolean onGround(Rectangle hitBox, ArrayList<Rectangle> surfaces) {
		boolean isOnGround = false;
		
		for (Rectangle r : surfaces) {
			if (hitBox.y + hitBox.height == r.y && hitBox.x < r.x + r.width && hitBox.x + hitBox.width > r.x) {
				isOnGround = true;
			}
		}
		
		return isOnGround;
	}

	private Rectangle getHitBox() {
		return new Rectangle(posx, getPosy(), PLAYER_WIDTH, duck ? PLAYER_HEIGHT / 2: PLAYER_HEIGHT);
	}
	
	public void collisionsHorizontal(Rectangle hitBox, ArrayList<Rectangle> surfaces) {
		Rectangle temp = new Rectangle(hitBox);
		temp.setLocation(hitBox.x + dx, hitBox.y);
		
//		ArrayList<Rectangle> t = new ArrayList<Rectangle> ();
		
		for (Rectangle r: surfaces) {
			if (r.intersects(temp)) {
//				t.add(r.intersection(temp));
				if (dx < 0) {
					dx = (r.x + r.width) - hitBox.x;
				} else {
					dx = r.x - (hitBox.x + hitBox.width);
				}
				stopDx = true;
				temp.setLocation(hitBox.x + dx, hitBox.y);
			}
		}
		
//		for (Rectangle r : t) {
//			surfaces.add(r);
//		}
	}
	
	public void collisionsVertical(Rectangle hitBox, ArrayList<Rectangle> surfaces) {
		Rectangle temp = new Rectangle(hitBox);
		temp.setLocation(hitBox.x, hitBox.y + dy);
		
//		ArrayList<Rectangle> t = new ArrayList<Rectangle> ();
		
		for (Rectangle r: surfaces) {
			if (r.intersects(temp)) {
//				t.add(r.intersection(temp));
				if (dy < 0) {
					dy = (r.y + r.height) - hitBox.y;
				} else {
					dy = r.y - (hitBox.y + hitBox.height);
				}
				stopDy = true;
				temp.setLocation(hitBox.x, hitBox.y + dy);
			}
		}
		
//		for (Rectangle r : t) {
//			surfaces.add(r);
//		}
	}

	private void animate() {
		String direction = dx < 0 ? LEFT : RIGHT, action = dx == 0 ? IDLE : duck ? DUCK : WALK;
		
		if (frameDelay >= Finals.animDelay) {
			frameDelay = 0;

			if (frame > 4 || action == IDLE) {
				frame = 0;
			}
			
			sprite = new ImageIcon(PATH + direction + action + frame + END).getImage();
			frame++;
		}
		frameDelay++;
	}

	public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
		
		g2d.setColor(ID.equals(playerID.FIRST) ? Color.BLUE : Color.RED);
		
		g2d.drawString(ID.name(), posx, getPosy());
		
		g2d.fill(getHitBox());
		
		g2d.drawImage(sprite, posx, getPosy(), null);
		
	}

	private int getPosy() {
		return duck ? posy + PLAYER_HEIGHT / 2: posy;
	}

	public Image getSprite() {
		return sprite;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (ID.equals(playerID.FIRST) ? key == KeyEvent.VK_W : key == KeyEvent.VK_UP) {
			jump = true;
		}

		if (ID.equals(playerID.FIRST) ? key == KeyEvent.VK_S : key == KeyEvent.VK_DOWN) {
			duck = true;
		}

		if (ID.equals(playerID.FIRST) ? key == KeyEvent.VK_A : key == KeyEvent.VK_LEFT) {
			left = true;
		}

		if (ID.equals(playerID.FIRST) ? key == KeyEvent.VK_D : key == KeyEvent.VK_RIGHT) {
			right = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (ID.equals(playerID.FIRST) ? key == KeyEvent.VK_W : key == KeyEvent.VK_UP) {
			jump = false;
		}

		if (ID.equals(playerID.FIRST) ? key == KeyEvent.VK_S : key == KeyEvent.VK_DOWN) {
			duck = false;
		}

		if (ID.equals(playerID.FIRST) ? key == KeyEvent.VK_A : key == KeyEvent.VK_LEFT) {
			left = false;
		}

		if (ID.equals(playerID.FIRST) ? key == KeyEvent.VK_D : key == KeyEvent.VK_RIGHT) {
			right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
