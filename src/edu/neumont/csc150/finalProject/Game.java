package edu.neumont.csc150.finalProject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel {

	private Stage s;
	

	public Game() {
			initUI();
			initStage();
			
		}

	public void initUI() {
		setLayout(null);
       	setFocusable(false);
        setDoubleBuffered(true);
        setBounds(0, 0, MainFrame.CONTENT_WIDTH, MainFrame.CONTENT_HEIGHT);
	}

	private void initStage() {
		s = new Stage();
		this.add(s);
		
		s.createKittens(4);
		s.createTestStage();
		
	}
}