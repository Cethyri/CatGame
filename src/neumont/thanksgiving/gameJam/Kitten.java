package neumont.thanksgiving.gameJam;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;

public class Kitten implements KeyListener, MouseListener {

	private static final int PLAYER_WIDTH = 24 * Finals.PIXEL_RATIO, PLAYER_HEIGHT = 12 * Finals.PIXEL_RATIO;

	private static final int JUMP_VEL = -25, WALK_VEL = 10;

	private static final String PATH = "Images/", END = ".png", PLACE_HOLDER = "catTemp", IDLE = "Idle", RIGHT = "Right", LEFT = "Left", WALK = "Walk", JUMP = "Jump", FALL = "Fall";

	private int posx, posy, dx, dy, collideDx, collideDy;
	
	private boolean jump, duck, left, right;

	private int frame, frameDelay;
	private Image sprite;
	
	private Stage s;

	public Kitten(int startX, int startY) {

		posx = startX;
		posy = startY - PLAYER_HEIGHT;

		dx = 0;
		dy = 0;
		
		jump = false;
		duck = false;
		left = false;
		right = false;

		frame = 0;
		frameDelay = Finals.animDelay;
		sprite = new ImageIcon(PATH + PLACE_HOLDER + frame + END).getImage();
		
		s = new Stage(100, Finals.FRAME_WIDTH);
	}

	public void move() {
		doHorizontal();

		doVertical();

		animate();
	}

	private void doHorizontal() {
		if (left || right) {

			dx = left ? -WALK_VEL : WALK_VEL;

			collideDx = s.collisionsHorizontal(dx, getHitBox());
			posx += collideDx;
		}
	}

	private void doVertical() {
		if (jump) {
			jump = false;
			if (onGround()) {
				dy += JUMP_VEL;				
			}
		}
		
		
		collideDy = s.collisionsVertical(dy, getHitBox());
		posy += collideDy;
		
		if (collideDy != dy) {
			dy = 0;
		}
		
		if (!onGround()) {
			dy -= Finals.GRAV;			
		}
	}

	private boolean onGround() {
		return s.onGround(getHitBox());
	}

	private Rectangle getHitBox() {
		return new Rectangle(posx, duck ? posy + PLAYER_HEIGHT / 2: posy, PLAYER_WIDTH, duck ? PLAYER_HEIGHT / 2: PLAYER_HEIGHT);
	}

	private void animate() {
		String direction = dx == 0 ? IDLE : (dx < 0 ? LEFT : RIGHT), action = WALK;
		
		if (frameDelay >= Finals.animDelay) {
			frameDelay = 0;

			if (frame > 4 || direction == IDLE) {
				frame = 0;
			}
			
			sprite = new ImageIcon(PATH + direction + action + frame + END).getImage();
			frame++;
		}
		frameDelay++;
	}

	public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(sprite, posx, duck ? posy + PLAYER_HEIGHT / 2: posy, null);
		
		s.draw(g2d);
	}

	public Image getSprite() {
		return sprite;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {

		}

		if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
			duck = true;
		}

		if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			left = true;
		}

		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			right = true;
		}

		if (key == KeyEvent.VK_SPACE) {
			jump = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {

		}

		if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
			duck = false;
		}

		if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			left = false;
		}

		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			right = false;
		}

		if (key == KeyEvent.VK_SPACE) {
			jump = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
