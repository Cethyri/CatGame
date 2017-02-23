package edu.neumont.csc150.finalProject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Kitten extends JLabel implements KeyListener, Collidable, TickListener {
	
	int frame;
	
	public Kitten() {
		this.setOpaque(false);
		
		frame = 0;
		doTick();
		
		setBounds(0, 0, getIcon().getIconWidth(), getIcon().getIconHeight());
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
		System.out.println("I'm painted");
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
