package neumont.thanksgiving.gameJam;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {

	private KeyAdapter TA;

	private MouseAdapter MA;

	private Image bgImage;

	private Timer T;

	public Game() {
			super();
			Init();
		}

	public void Init() {
		bgImage = new ImageIcon("Images/Background.png").getImage();

		TA = new TAdapter();
		addKeyListener(TA);

		MA = new MAdapter();
		addMouseListener(MA);
		addMouseMotionListener(MA);
		addMouseWheelListener(MA);

		T = new Timer(250, this);
		T.start();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(bgImage, 0, 0, this);

		this.paintComponents(g);

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	// g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
	// g2d.setColor(Color.(COLOR/color));
	// g2d.drawString("", x, y);
	// g2d.fillRect(x, y, width, height);

	public void actionPerformed(ActionEvent e) {

		repaint();
	}

	public class TAdapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {

		}

		public void keyPressed(KeyEvent e) {

		}
	}

	public class MAdapter extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			// System.out.println("click " + e.getButton());
		}

		public void mouseDragged(MouseEvent e) {
			// System.out.println("drag");
		}

		public void mouseEntered(MouseEvent e) {
			// System.out.println("enter");
		}

		public void mouseExited(MouseEvent e) {
			// System.out.println("exit");
		}

		public void mouseMoved(MouseEvent e) {
			// System.out.println("X: " + x + "Y: " + y);
		}

		public void mousePressed(MouseEvent e) {
			// System.out.println("press");
		}

		public void mouseReleased(MouseEvent e) {
			// System.out.println("release");
		}

		public void mouseWheelMoved(MouseWheelEvent e) {
			// System.out.println("mousewheel");
		}

	}
}