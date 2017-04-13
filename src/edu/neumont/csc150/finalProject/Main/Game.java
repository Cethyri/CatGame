package edu.neumont.csc150.finalProject.Main;

import java.awt.Component;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

import edu.neumont.csc150.finalProject.Controller.UDPServer;
import edu.neumont.csc150.finalProject.Join.JoinScreen;
import edu.neumont.csc150.finalProject.Stage.Stage;
import edu.neumont.csc150.finalProject.Stage.stages;

@SuppressWarnings("serial")
public class Game extends JPanel {

	private static int PLAYER_COUNT = 3;
	
	public final UDPServer UDP;

	private static Stage stage;
	private static JoinScreen join;

	public Game() throws Exception {
		UDP = new UDPServer();
		
		initUI();
		initStage();
		//initJoinScreen();

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
		stage.createSurfacesFromArray(stages.testStage());
	}
	
	private void initJoinScreen() {
		join = new JoinScreen();
		this.add(join);
		
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