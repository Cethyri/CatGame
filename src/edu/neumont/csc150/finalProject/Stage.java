package edu.neumont.csc150.finalProject;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Stage extends JPanel implements ActionListener {
	
	ArrayList<Collidable> surfaces;
	ArrayList<TickListener> ticks;
	
	public Stage() {
		initUI();
	}

	private void initUI() {
		setLayout(null);
       	setFocusable(true);
        setDoubleBuffered(true);
        setBounds(0, 0, MainFrame.FRAME_WIDTH, MainFrame.FRAME_HEIGHT);
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
		repaint();
	}
}
