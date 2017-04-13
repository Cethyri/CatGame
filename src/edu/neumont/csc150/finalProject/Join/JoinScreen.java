package edu.neumont.csc150.finalProject.Join;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import edu.neumont.csc150.finalProject.Actor.Player.PlayerID;
import edu.neumont.csc150.finalProject.Main.MainFrame;
import edu.neumont.csc150.finalProject.Stage.Stage;

public class JoinScreen extends JPanel implements ActionListener {
	
	private static final JoinPanel[] playerPanels = new JoinPanel[PlayerID.values().length];
	
	private Timer t;
	
	
	public JoinScreen() {
		initVars();
		initUI();
	}

	private void initVars() {
		t = new Timer(Stage.tickLength, this);
		t.start();
	}
	
	private void initUI() {
		for (int i = 0; i < playerPanels.length; i++) {
			playerPanels[i] = new JoinPanel(PlayerID.values()[i]);
			
			playerPanels[i].setBounds((i % (playerPanels.length / 2)) * (MainFrame.CONTENT_WIDTH / (playerPanels.length / 2)), (i / 2) * (MainFrame.CONTENT_HEIGHT / 2), MainFrame.CONTENT_WIDTH / 2, MainFrame.CONTENT_HEIGHT / 2);
			
			this.add(playerPanels[i]);
		}
		
		setLayout(null);
       	setFocusable(true);
        setDoubleBuffered(true);
        setBounds(0, 0, MainFrame.CONTENT_WIDTH, MainFrame.CONTENT_HEIGHT);
        
        setBackground(Color.cyan);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < playerPanels.length; i++) {
			playerPanels[i].animate();
		}
		
	}
}
