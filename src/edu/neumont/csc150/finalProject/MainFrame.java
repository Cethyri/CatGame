package edu.neumont.csc150.finalProject;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.GroupLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	public static final int FRAME_WIDTH = 2000, FRAME_HEIGHT = 1000;
	
	private GroupLayout layout;
	
	private Game C;
	
	public MainFrame() {
		
		initUI();
	}
	
    private void initUI() {
    	
    	setTitle("Fight!");
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
    	setLayout(null);
    	setResizable(false);
    	
    	setVisible(true);
    	Insets i = this.getInsets();
    	setSize(FRAME_WIDTH + i.left + i.right, FRAME_HEIGHT + i.top + i.bottom);
    	setLocationRelativeTo(null);
    	
       	C = new Game();
    	
    	this.setContentPane(C);
    }    
    
    
	public static void main(String[] args) {
		
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mf = new MainFrame();
                mf.setVisible(true);
            }
		});
	}

}