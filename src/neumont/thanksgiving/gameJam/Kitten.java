package neumont.thanksgiving.gameJam;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class Kitten implements KeyListener, MouseListener{

	private static final int PLAYER_WIDTH = 24 * Finals.PIXEL_RATIO, PLAYER_HEIGHT = 24 * Finals.PIXEL_RATIO;

	private static final String PATH = "Images/", END = ".png", PLACE_HOLDER = "catTemp";

	private int posx, posy, dx, dy;

	private int frame;
	private Image sprite;

	public Kitten(int startX, int startY) {
		
		posx = startX;
		posy = startY - PLAYER_HEIGHT;

		dx = 0;
		dy = 0;

		frame = 0;
	}

	public void move() {
		posx += dx;
		posy += dy;
		
		animate();
	}

	private void animate() {
		if (frame > 6) {
			frame = 0;
		}
		sprite = new ImageIcon(PATH + PLACE_HOLDER + frame + END).getImage();
		frame++;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
