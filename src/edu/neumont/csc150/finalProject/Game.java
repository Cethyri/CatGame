package edu.neumont.csc150.finalProject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener {

	private static int PLAYER_COUNT = 4;
	
	public final UDPServer UDP;

	private static Stage s;

	public Game() throws Exception {
		UDP = new UDPServer();
		
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

		s.createKittens(PLAYER_COUNT);
		s.createTestStage();
	}
	
	public static int getPlayerCount() {
		return PLAYER_COUNT;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("key pressed in game");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("key released in game");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	
}