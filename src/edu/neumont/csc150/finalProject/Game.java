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

	private Image bgImage;

	private Stage s;

	public Game() {
			super();
			initPanel();
		}

	public void initPanel() {
		
		this.add(new Stage(100));
	}

	// g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
	// g2d.setColor(Color.(COLOR/color));
	// g2d.drawString("", x, y);
	// g2d.fillRect(x, y, width, height);
}