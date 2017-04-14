package edu.neumont.csc150.finalProject.Stage;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import edu.neumont.csc150.finalProject.Actor.Collidable;
import edu.neumont.csc150.finalProject.Actor.Surface;
import edu.neumont.csc150.finalProject.Actor.TickListener;
import edu.neumont.csc150.finalProject.Actor.Player.Attack;
import edu.neumont.csc150.finalProject.Actor.Player.Cat;
import edu.neumont.csc150.finalProject.Actor.Player.Movable;
import edu.neumont.csc150.finalProject.Actor.Player.PlayerID;
import edu.neumont.csc150.finalProject.Main.Game;
import edu.neumont.csc150.finalProject.Main.MainFrame;

@SuppressWarnings("serial")
public class Stage extends JPanel implements ActionListener{
	
	public static final double GRAV = -1;
	public static final int TILE_DIMENSIONS = 50;
	
	private static ArrayList<Collidable> surfaces;
	private static ArrayList<Movable> moveables;
	private static ArrayList<TickListener> tickListeners;
	private static ArrayList<Attack> attacks;
	
	private int dead;
	
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
        setBounds(0, 0, MainFrame.CONTENT_WIDTH, MainFrame.CONTENT_HEIGHT);
        
        setBackground(Color.cyan);
	}
	
	private void initVars() {
		
		surfaces = new ArrayList<>();
		moveables = new ArrayList<>();
		tickListeners = new ArrayList<>();
		attacks = new ArrayList<>();
		
		dead = 0;
		
		t = new Timer(tickLength, this);
		t.start();
	}
	
	public static ArrayList<Collidable> getSurfaces() {
		return surfaces;
	}
	
	public static ArrayList<Attack> getAttacks() {
		return attacks;
	}
	
	@Override
	public Component add(Component c) {
		if (c instanceof Collidable) {
			surfaces.add((Collidable) c);
		}
		if (c instanceof Movable) {
			moveables.add((Movable) c);
		}
		if (c instanceof TickListener) {
			tickListeners.add((TickListener) c);
		}
		if (c instanceof KeyListener) {
			addKeyListener((KeyListener) c);
		}
		if (c instanceof Attack) {
			attacks.add((Attack) c);
		}
		return super.add(c);
	}
	
	@Override
	public void remove(Component c) {
		if (c instanceof Collidable) {
			surfaces.remove((Collidable) c);
		}
		if (c instanceof Movable) {
			moveables.remove((Movable) c);
		}
		if (c instanceof TickListener) {
			tickListeners.remove((TickListener) c);
		}
		if (c instanceof KeyListener) {
			removeKeyListener((KeyListener) c);
		}
		if (c instanceof Attack) {
			attacks.remove((Attack) c);
		}
		super.remove(c);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		for (int i = 0; i < tickListeners.size(); i++) {
			tickListeners.get(i).doTick();
		}
		if (dead != -1) {
			dead = 0;
			
			for (TickListener tickListener : tickListeners) {
				if (tickListener instanceof Cat) {
					if (!((Cat) tickListener).isAlive()) {
						dead++;
					}
				}
			}
			
//			if (dead >= Game.getPlayerCount() - 1) {
//				finalScreen();
//			}
		}
		repaint();
	}

	public void createKittens(int playerCount) {
		playerCount = playerCount <= PlayerID.values().length ? playerCount: PlayerID.values().length;
		for (int i = 0; i < playerCount; i++) {
			add(new Cat(PlayerID.values()[i]));
		}
	}
	
	public void addToKittens(Cat k) {
		add(k);
	}
	
	//@@@@ "A multi-dimensional array (>= 2 dimensions)"
	public void createSurfacesFromArray(boolean[][] surfaces) {
		boolean validated = true;
		if (surfaces.length == MainFrame.CONTENT_WIDTH_IN_TILES) {
			for (boolean[] bs : surfaces) {
				if (bs.length != MainFrame.CONTENT_HEIGHT_IN_TILES) {
					validated = false;
				}
			}
		}
		
		if (validated) {
			for (int x = 0; x < surfaces.length; x++) {
				for (int y = 0; y < surfaces[x].length; y++) {
					if (surfaces[x][y]) {
						add(new Surface(x * TILE_DIMENSIONS, y * TILE_DIMENSIONS, TILE_DIMENSIONS, TILE_DIMENSIONS));
					}
				}
			}
		}
	}

	public void createTestStage() {
		add(new Surface(0, MainFrame.CONTENT_HEIGHT - (MainFrame.CONTENT_HEIGHT / 10), MainFrame.CONTENT_WIDTH, MainFrame.CONTENT_HEIGHT / 10));
		
		add(new Surface(MainFrame.CONTENT_WIDTH / 5, MainFrame.CONTENT_HEIGHT - (MainFrame.CONTENT_HEIGHT / 5), MainFrame.CONTENT_WIDTH / 20, MainFrame.CONTENT_HEIGHT / 10));
		add(new Surface(MainFrame.CONTENT_WIDTH / 5 * 2, MainFrame.CONTENT_HEIGHT - (MainFrame.CONTENT_HEIGHT / 10) * 4, MainFrame.CONTENT_WIDTH / 20, MainFrame.CONTENT_HEIGHT / 5));
		add(new Surface(MainFrame.CONTENT_WIDTH / 10 * 3, MainFrame.CONTENT_HEIGHT - (MainFrame.CONTENT_HEIGHT / 10) * 3, MainFrame.CONTENT_WIDTH / 20, MainFrame.CONTENT_HEIGHT / 10));
		add(new Surface(MainFrame.CONTENT_WIDTH / 10 * 6, MainFrame.CONTENT_HEIGHT - (MainFrame.CONTENT_HEIGHT / 5), MainFrame.CONTENT_WIDTH / 20, MainFrame.CONTENT_HEIGHT / 10));
		add(new Surface(MainFrame.CONTENT_WIDTH / 10 * 5, MainFrame.CONTENT_HEIGHT - (MainFrame.CONTENT_HEIGHT / 10) * 3, MainFrame.CONTENT_WIDTH / 20, MainFrame.CONTENT_HEIGHT / 10));
	}
	
	private void finalScreen() {
		dead = -1;
		add(new Surface(0, MainFrame.CONTENT_HEIGHT - (MainFrame.CONTENT_HEIGHT / 10), MainFrame.CONTENT_WIDTH, MainFrame.CONTENT_HEIGHT / 10));
		
		add(new Surface(MainFrame.CONTENT_WIDTH / 5, MainFrame.CONTENT_HEIGHT - (MainFrame.CONTENT_HEIGHT / 5), MainFrame.CONTENT_WIDTH / 20, MainFrame.CONTENT_HEIGHT / 10));
		add(new Surface(MainFrame.CONTENT_WIDTH / 5 * 2, MainFrame.CONTENT_HEIGHT - (MainFrame.CONTENT_HEIGHT / 10) * 4, MainFrame.CONTENT_WIDTH / 20, MainFrame.CONTENT_HEIGHT / 5));
		add(new Surface(MainFrame.CONTENT_WIDTH / 10 * 3, MainFrame.CONTENT_HEIGHT - (MainFrame.CONTENT_HEIGHT / 10) * 3, MainFrame.CONTENT_WIDTH / 20, MainFrame.CONTENT_HEIGHT / 10));
		add(new Surface(MainFrame.CONTENT_WIDTH / 10 * 6, MainFrame.CONTENT_HEIGHT - (MainFrame.CONTENT_HEIGHT / 5), MainFrame.CONTENT_WIDTH / 20, MainFrame.CONTENT_HEIGHT / 10));
		add(new Surface(MainFrame.CONTENT_WIDTH / 10 * 5, MainFrame.CONTENT_HEIGHT - (MainFrame.CONTENT_HEIGHT / 10) * 3, MainFrame.CONTENT_WIDTH / 20, MainFrame.CONTENT_HEIGHT / 10));
	}

}
