package neumont.thanksgiving.gameJam;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Kitten {

	private static final int PLAYER_WIDTH = 24 * Finals.PIXEL_RATIO, PLAYER_HEIGHT = 24 * Finals.PIXEL_RATIO;

	private static final String PATH = "Images/", END = ".png";

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
		if (frame > 3) {
			frame = 0;
		}
		sprite = new ImageIcon(PATH + "Idle Sprite " + frame + END).getImage();
		frame++;
	}
	
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(sprite, posx, posy, null);
	}

	public Image getSprite() {
		return sprite;
	}
}
