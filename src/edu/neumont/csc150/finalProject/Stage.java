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
	
	public static final double GRAV = -1;
	
	private static ArrayList<Collidable> surfaces;
	private ArrayList<TickListener> ticks;
	
	public static final int tickLength = 10;
	private Timer t;
	
	public Stage() {
		initVars();
		initUI();
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
		
		t = new Timer(tickLength, this);
		t.start();
	}
	
	public static ArrayList<Collidable> getSurfaces() {
		return surfaces;
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
		for (TickListener tickListener : ticks) {
			tickListener.doTick();
		}
		repaint();
	}

	public void createKittens(int playerCount) {
		playerCount = playerCount <= PlayerID.values().length ? playerCount: 4;
		for (int i = 0; i < playerCount; i++) {
			add(new Kitten(PlayerID.values()[i]));
		}
		
	}

}
