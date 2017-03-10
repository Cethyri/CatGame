package edu.neumont.csc150.finalProject;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class StatPanel extends JPanel {
	
	public static final int HEALTH_HEIGHT = 15;
	
	public final Cat owner;
	
	public StatPanel(Cat owner) {
		this.owner = owner;
		
		initUI();
	}

	private void initUI() {
		this.setOpaque(true);
		this.setBackground(owner.id.playerColor);
		setBounds(owner.id.startX, 0, MainFrame.CONTENT_WIDTH / 4, 100);
	}
	
@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.green);
		
		if (owner.isAlive()) {
			g.setColor(new Color(255 - (int) (owner.getHealth() * 1.0 / owner.maxHealth * 255), (int) (owner.getHealth() * 1.0 / owner.maxHealth * 220) + 35, 0));
			g.fillRect((int) ((this.getWidth() - (owner.getHealth() * 1.0 / owner.maxHealth * this.getWidth() )) / 2), this.getHeight() - HEALTH_HEIGHT, (int) (owner.getHealth() * 1.0 / owner.maxHealth * this.getWidth()), HEALTH_HEIGHT);			
		}
		else {
			g.setColor(Color.black);
			g.fillRect(0, this.getHeight() - HEALTH_HEIGHT, this.getWidth(), HEALTH_HEIGHT);
		}

	}
}
