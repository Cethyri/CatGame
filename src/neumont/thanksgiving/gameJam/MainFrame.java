package neumont.thanksgiving.gameJam;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private Game C;
	
	private GroupLayout layout;
	
	public MainFrame() {
		
		InitUI();
	}
	
    private void InitUI() {
    	
    	InitGame();
    	
    	this.setContentPane(C);
    	
    	setLayout(null);
    	setResizable(false);
    	setVisible(true);
    	setBounds((Finals.SCR_WIDTH - Finals.FRAME_WIDTH) / 2, (Finals.SCR_HEIGHT - Finals.FRAME_HEIGHT) / 2, Finals.FRAME_WIDTH + 6, Finals.FRAME_HEIGHT + 51); // 6 & 51 are offsets from the window itself
    	setTitle("Fight!");
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
    public void InitGame() {
    	C = new Game();
    	
    	layout = new GroupLayout(C);
    	
    	C.setLayout(layout);
       	C.setFocusable(true);
        C.setDoubleBuffered(true);
        C.setPreferredSize(new Dimension(Finals.FRAME_WIDTH, Finals.FRAME_HEIGHT));
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