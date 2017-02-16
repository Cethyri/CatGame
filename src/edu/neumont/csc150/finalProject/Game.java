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

public class Game extends JPanel implements ActionListener {

	private KeyAdapter TA;

	private Image bgImage;

	private Timer T;

	private Stage s;

	public Game() {
			super();
			Init();
		}

	public void Init() {
		bgImage = new ImageIcon("Images/Background.png").getImage();

		TA = new TAdapter();
		addKeyListener(TA);
		
		s = new Stage(100, Finals.FRAME_WIDTH);

		T = new Timer(10, this);
		T.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(bgImage, 0, 0, this);
		
		s.draw(g);

		this.paintComponents(g);

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	// g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
	// g2d.setColor(Color.(COLOR/color));
	// g2d.drawString("", x, y);
	// g2d.fillRect(x, y, width, height);

	public void actionPerformed(ActionEvent e) {
		s.actionPerformed();
		
		repaint();
	}

	public class TAdapter extends KeyAdapter {
		
		public void keyPressed(KeyEvent e) {
			s.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			s.keyReleased(e);
		}
		
		public void keyTyped(KeyEvent e) {
			s.keyTyped(e);
		}
	}
}