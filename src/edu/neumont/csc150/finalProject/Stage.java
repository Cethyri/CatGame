package edu.neumont.csc150.finalProject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Stage extends JPanel implements ActionListener {
	
	private KeyAdapter TA;

	private Timer T;
	
	private ArrayList<Rectangle> surfaces;

	private Kitten[] kittens;

	public Stage(int floorHeight) {
		surfaces = new ArrayList<Rectangle>();

		createFloor(floorHeight);
		createTestStage(floorHeight);

		kittens = new Kitten[2];
		
		for (int i = 0; i < kittens.length; i++) {
			kittens[i] = new Kitten(300, floorHeight, playerID.values()[i]);
			addKeyListener(kittens[i]);
		}
		
		initUI();
	}

	private void initUI() {
    	setLayout(null);
       	setFocusable(true);
        setDoubleBuffered(true);
        setBounds(0, 0, Finals.FRAME_WIDTH, Finals.FRAME_HEIGHT);
		
		T = new Timer(10, this);
		T.start();
	}

	private void createTestStage(int floorHeight) {
		surfaces.add(new Rectangle(Finals.FRAME_WIDTH / 2 - 50, Finals.FRAME_HEIGHT - (100 + floorHeight), 100, 100));
		surfaces.add(new Rectangle(Finals.FRAME_WIDTH / 2 - 150, Finals.FRAME_HEIGHT - (330 + floorHeight), 100, 100));
		surfaces.add(new Rectangle(Finals.FRAME_WIDTH / 2 - 50, Finals.FRAME_HEIGHT - (560 + floorHeight), 100, 100));
		surfaces.add(new Rectangle(Finals.FRAME_WIDTH / 2 + 50, Finals.FRAME_HEIGHT - (790 + floorHeight), 100, 100));
	}

	private void createFloor(int floorHeight) {

		surfaces.add(new Rectangle(0, Finals.FRAME_HEIGHT - floorHeight, Finals.FRAME_WIDTH , floorHeight));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.DARK_GRAY);

		for (Rectangle r : surfaces) {
			g2d.fill(r);
		}

		for (Kitten k : kittens) {
			k.draw(g);
		}

		this.paintComponents(g);

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public void test(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.RED);

		for (Rectangle r : surfaces) {
			for (Rectangle r2 : surfaces) {
				if (r2.intersects(r) && r2 != r) {
					g2d.fill(r.intersection(r2));
				}
			}
		}
	}

	
	public void actionPerformed(ActionEvent e) {
		for (Kitten k : kittens) {
			k.move(surfaces);
		}
		repaint();
	}
}
