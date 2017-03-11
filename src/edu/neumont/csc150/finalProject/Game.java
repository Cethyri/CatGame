package edu.neumont.csc150.finalProject;

import java.awt.Component;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	private static int PLAYER_COUNT = 3;
	
	public final UDPServer UDP;

	public static Stage stage;

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
		stage = new Stage();
		this.add(stage);

		stage.createKittens(PLAYER_COUNT);
		stage.createTestStage();
	}
	
	public static int getPlayerCount() {
		return PLAYER_COUNT;
	}
	
	public static Stage getStage() {
		return stage;
	}

	public void dispatchToVisible(KeyEvent e) {
		for (Component c : this.getComponents()) {
			if (c.isVisible()) {
				System.out.println("dispatch on " + c);
				e.setSource(c);
				c.dispatchEvent(e);
			}
		}
	}	
}