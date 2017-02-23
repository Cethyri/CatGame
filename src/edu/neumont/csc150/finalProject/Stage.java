package edu.neumont.csc150.finalProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Stage extends JPanel implements ActionListener {
	
	ArrayList<Collidable> surfaces;
	ArrayList<TickListener> ticks;
	
	Timer t;
	
	public Stage() {
		initUI();
		initVars();
	}

	private void initUI() {
		setLayout(null);
       	setFocusable(true);
        setDoubleBuffered(true);
        setBounds(0, 0, MainFrame.FRAME_WIDTH, MainFrame.FRAME_HEIGHT);
        
        setBackground(Color.cyan);
	}
	
	private void initVars() {
		surfaces = new ArrayList<>();
		ticks = new ArrayList<>();
		
		t = new Timer(50, this);
		t.start();
	}
	
	@Override
	public Component add(Component c) {
		if (c instanceof Collidable) {
			surfaces.add((Collidable) c);
		}
		if (c instanceof TickListener) {
			ticks.add((TickListener) c);
		}
		if (c instanceof KeyListener) {
			addKeyListener((KeyListener) c);
		}
		return super.add(c);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		for (Collidable collider : surfaces) {
			
		}
		for (TickListener tick : ticks) {
			tick.doTick();
		}
		repaint();
	}
}
