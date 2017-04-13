package edu.neumont.csc150.finalProject.Main;

import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.JFrame;

import edu.neumont.csc150.finalProject.Stage.Stage;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	
	public static final int CONTENT_WIDTH_IN_TILES = 40, CONTENT_HEIGHT_IN_TILES = 20;
	public static final int CONTENT_WIDTH = CONTENT_WIDTH_IN_TILES * Stage.TILE_DIMENSIONS, CONTENT_HEIGHT = CONTENT_HEIGHT_IN_TILES * Stage.TILE_DIMENSIONS;
	
	public static Game game;
	
	public MainFrame() throws Exception {
		
		initUI();
	}
	
    private void initUI() throws Exception {
    	
    	setTitle("Fight!");
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
    	setLayout(null);
    	setResizable(false);
    	
    	setVisible(true);
    	Insets i = this.getInsets();
    	setSize(CONTENT_WIDTH + i.left + i.right, CONTENT_HEIGHT + i.top + i.bottom);
    	setLocationRelativeTo(null);
    	
       	game = new Game();
    	
    	this.setContentPane(game);
    }
    
	public static void main(String[] args) {
		
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mf;
				try {
					mf = new MainFrame();
					mf.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
		});
	}

}