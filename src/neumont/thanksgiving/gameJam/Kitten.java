package neumont.thanksgiving.gameJam;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;

public class Kitten implements KeyListener, MouseListener {

	private static final int PLAYER_WIDTH = 24 * Finals.PIXEL_RATIO, PLAYER_HEIGHT = 12 * Finals.PIXEL_RATIO;

	private static final int JUMP_VEL = - 30;
	
	private static final String PATH = "Images/", END = ".png", PLACE_HOLDER = "catTemp";

	private int posx, posy, dx, dy;
	
	private boolean jump, duck;

	private int frame, frameDelay;
	private Image sprite;

	public Kitten(int startX, int startY) {

		posx = startX;
		posy = startY - PLAYER_HEIGHT;

		dx = 0;
		dy = 0;
		
		jump = false;
		duck = false;

		frame = 0;
		frameDelay = Finals.animDelay;
	}

	public void move() {
		posx += dx;
		
		
		if (jump) {
			jump = false;
			dy += JUMP_VEL;
		}
		
		System.out.println("dy: " + dy);
		if (posy + PLAYER_HEIGHT + dy < Finals.FRAME_HEIGHT && posy + dy > 0) {
			posy += dy;			
			dy -= Finals.GRAV;
		}
		else {
			dy = 0;
			posy = Finals.FRAME_HEIGHT - PLAYER_HEIGHT;
		}


		animate();
	}

	private void animate() {
		if (frameDelay >= Finals.animDelay) {
			frameDelay = 0;
			
			if (frame > 5) {
				frame = 0;
			}
			File f = new File(PATH + PLACE_HOLDER + frame + END);
			sprite = new ImageIcon(PATH + PLACE_HOLDER + frame + END).getImage();
			frame++;
		}
		frameDelay++;
	}

	public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(sprite, posx, posy, null);
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
			dx = -5;
		}

		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			dx = 5;
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
			dx = 0;
		}

		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			dx = 0;
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
