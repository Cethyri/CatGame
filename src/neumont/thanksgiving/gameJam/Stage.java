package neumont.thanksgiving.gameJam;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Stage {
	
	private int floorHeight;
	
	ArrayList<Rectangle> surfaces;
	
	public Stage (int floorHeight, int floorWidth) {
		surfaces = new ArrayList<Rectangle> ();
		
		this.floorHeight = floorHeight;
		
		surfaces.add(new Rectangle(0, Finals.FRAME_HEIGHT - floorHeight, floorWidth, floorHeight));
		surfaces.add(new Rectangle(Finals.FRAME_WIDTH / 2 - 50, Finals.FRAME_HEIGHT - (100 + floorHeight), 100, 100));
		surfaces.add(new Rectangle(Finals.FRAME_WIDTH / 2 - 150, Finals.FRAME_HEIGHT - (350 + floorHeight), 100, 100));
		surfaces.add(new Rectangle(Finals.FRAME_WIDTH / 2 - 50, Finals.FRAME_HEIGHT - (600 + floorHeight), 100, 100));
		surfaces.add(new Rectangle(Finals.FRAME_WIDTH / 2 + 50, Finals.FRAME_HEIGHT - (850 + floorHeight), 100, 100));
	}
	
	public int collisionsHorizontal(int dx, Rectangle hitBox) {
		Rectangle temp = new Rectangle(hitBox);
		temp.setLocation(hitBox.x + dx, hitBox.y);
		
		ArrayList<Rectangle> t = new ArrayList<Rectangle> ();
		
		for (Rectangle r: surfaces) {
			if (r.intersects(temp)) {
				t.add(r.intersection(temp));
				if (dx < 0) {
					dx = (r.x + r.width) - hitBox.x;
				} else {
					dx = r.x - (hitBox.x + hitBox.width);
				}
				temp.setLocation(hitBox.x + dx, hitBox.y);
			}
		}
		
		for (Rectangle r : t) {
			surfaces.add(r);
		}
		
		return dx;
	}
	
	public int collisionsVertical(int dy, Rectangle hitBox) {
		Rectangle temp = new Rectangle(hitBox);
		temp.setLocation(hitBox.x, hitBox.y + dy);
		
		ArrayList<Rectangle> t = new ArrayList<Rectangle> ();
		
		for (Rectangle r: surfaces) {
			if (r.intersects(temp)) {
				t.add(r.intersection(temp));
				if (dy < 0) {
					dy = (r.y + r.height) - hitBox.y;
				} else {
					dy = r.y - (hitBox.y + hitBox.height);
				}
				temp.setLocation(hitBox.x, hitBox.y + dy);
			}
		}
		
		for (Rectangle r : t) {
			surfaces.add(r);
		}
		
		return dy;
	}
	
	public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.DARK_GRAY);
		
		for (Rectangle r: surfaces) {
			g2d.fill(r);			
		}
		
		test(g);
	}

	public boolean onGround(Rectangle hitBox) {
		boolean isOnGround = false;
		
		for (Rectangle r : surfaces) {
			if (hitBox.y + hitBox.height == r.y && hitBox.x < r.x + r.width && hitBox.x + hitBox.width > r.x) {
				isOnGround = true;
			}
		}
		
		return isOnGround;
	}
	
	public void test(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.RED);
		
		for (Rectangle r: surfaces) {
			for (Rectangle r2: surfaces) {
				if (r2.intersects(r) && r2 != r) {
					g2d.fill(r.intersection(r2));
				}
			}			
		}
	}
	
	
}
