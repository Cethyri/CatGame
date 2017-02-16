package edu.neumont.csc150.finalProject;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private Game C;
	
	private GroupLayout layout;
	
	public MainFrame() {
		
		initUI();
	}
	
    private void initUI() {
    	
    	setTitle("Fight!");
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
    	setLayout(null);
    	setResizable(false);
    	setBounds((Finals.SCR_WIDTH - Finals.FRAME_WIDTH) / 2, (Finals.SCR_HEIGHT - Finals.FRAME_HEIGHT) / 2, Finals.FRAME_WIDTH + 6, Finals.FRAME_HEIGHT + 51); // 6 & 51 are offsets from the window itself
    	setLocationRelativeTo(null);
    	
    	setVisible(true);
    	
    	initGame();
    	
    	this.setContentPane(C);
    }
    
    
    public void initGame() {
    	C = new Game();
    	
    	C.setLayout(null);
       	C.setFocusable(false);
        C.setDoubleBuffered(true);
        C.setBounds(0, 0, Finals.FRAME_WIDTH, Finals.FRAME_HEIGHT);
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