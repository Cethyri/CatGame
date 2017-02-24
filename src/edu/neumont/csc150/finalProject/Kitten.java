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
	
	public Kitten(PlayerID id) {
		initVars(id);
		
		initUI();
		
		this.id = id;
	}

	private void initVars(PlayerID id) {
		
		frame = 0;
		doTick();
	}

	private void initUI() {
		
		this.setOpaque(true);
		setBounds(0, 0, getIcon().getIconWidth(), getIcon().getIconHeight());
	}
	
	@Override
	public void checkForCollisions(ArrayList<Rectangle> surfaces) {
		
	}
	
	@Override
	public void doTick() {
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
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
