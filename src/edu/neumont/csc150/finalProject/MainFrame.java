package edu.neumont.csc150.finalProject;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.GroupLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	public static final int CONTENT_WIDTH = 2000, CONTENT_HEIGHT = 1000;
	
	private GroupLayout layout;
	
	private static Game G;
	
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
    	
       	G = new Game();
    	
    	this.setContentPane(G);
    }   
    
    public static Game getGame() {
    	return G;
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