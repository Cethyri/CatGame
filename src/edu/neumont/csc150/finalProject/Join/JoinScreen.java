package edu.neumont.csc150.finalProject.Join;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import edu.neumont.csc150.finalProject.Actor.Player.PlayerID;
import edu.neumont.csc150.finalProject.Main.MainFrame;
import edu.neumont.csc150.finalProject.Stage.Stage;

public class JoinScreen extends JPanel implements ActionListener, KeyListener {

	public static final JoinPanel[] playerPanels = new JoinPanel[PlayerID.values().length];
	private final JLabel cntDwnLbl = new JLabel();

	private Timer t;

	private static final int TIME_FOR_FRAME = 500, COUNT_DOWN_START = 5;

	private boolean go;
	private int cntDwnDel;
	private int cntDwn;

	private int playerCount;

	public JoinScreen() {
		initVars();
		initUI();
	}

	private void initVars() {
		go = false;
		cntDwnDel = 0;
		cntDwn = 0;

		playerCount = 0;

		t = new Timer(Stage.tickLength, this);
		t.start();
	}

	private void initUI() {

		cntDwnLbl.setBounds(MainFrame.CONTENT_WIDTH / 4, MainFrame.CONTENT_HEIGHT / 4, MainFrame.CONTENT_WIDTH / 2,
				MainFrame.CONTENT_HEIGHT / 2);
		cntDwnLbl.setFont(new Font("Serif", Font.BOLD, 576));
		cntDwnLbl.setHorizontalAlignment(JLabel.CENTER);
		this.add(cntDwnLbl);

		for (int i = 0; i < playerPanels.length; i++) {
			playerPanels[i] = new JoinPanel();

			playerPanels[i].setBounds(
					(i % (playerPanels.length / 2)) * (MainFrame.CONTENT_WIDTH / (playerPanels.length / 2)),
					(i / 2) * (MainFrame.CONTENT_HEIGHT / 2), MainFrame.CONTENT_WIDTH / 2,
					MainFrame.CONTENT_HEIGHT / 2);
			playerPanels[i].join(PlayerID.values()[i]);
			this.add(playerPanels[i]);
			playerCount++;
		}

		setLayout(null);
		setFocusable(true);
		setDoubleBuffered(true);
		setBounds(0, 0, MainFrame.CONTENT_WIDTH, MainFrame.CONTENT_HEIGHT);
	}

	public void addPlayer(PlayerID id) {
		playerPanels[playerCount] = new JoinPanel();

		playerPanels[playerCount].setBounds(
				(playerCount % (playerPanels.length / 2)) * (MainFrame.CONTENT_WIDTH / (playerPanels.length / 2)),
				(playerCount / 2) * (MainFrame.CONTENT_HEIGHT / 2), MainFrame.CONTENT_WIDTH / 2,
				MainFrame.CONTENT_HEIGHT / 2);
		playerPanels[playerCount].join(id);
		this.add(playerPanels[playerCount]);
		playerCount++;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public Component add(Component c) {
		if (c instanceof KeyListener) {
			addKeyListener((KeyListener) c);
		}
		return super.add(c);
	}

	@Override
	public void remove(Component c) {
		if (c instanceof KeyListener) {
			removeKeyListener((KeyListener) c);
		}
		super.remove(c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int amountReady = 0;
		for (int i = 0; i < playerCount; i++) {
			if (playerPanels[i].isJoined()) {
				playerPanels[i].animate();
			}
			if (playerPanels[i].isReady()) {
				amountReady++;
			}
		}

		if (amountReady > 1) {
			go = true;
		} else {
			go = false;
			cntDwn = 0;
		}

		if (go && (COUNT_DOWN_START - cntDwn) != -1) {
			cntDwnDel++;
			if (cntDwnDel >= TIME_FOR_FRAME / Stage.tickLength) {
				cntDwnLbl.setText(" " + (COUNT_DOWN_START - cntDwn));
				if (cntDwn >= COUNT_DOWN_START) {
					cntDwnLbl.setText("GO");
					t.stop();
					MainFrame.game.start();
				}
				cntDwn++;
				cntDwnDel = 0;
			}
		}

	}
}
