package edu.neumont.csc150.finalProject.Join;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import edu.neumont.csc150.finalProject.Actor.Player.Cat;
import edu.neumont.csc150.finalProject.Actor.Player.PlayerID;

@SuppressWarnings("serial")
public class JoinPanel extends JPanel implements KeyListener {

	private JLabel PlayerLabel;
	private JComboBox<Passive> PassiveOptions;
	private JComboBox<Aggressive> AggressiveOptions;
	private Cat Display;
	private JRadioButton ReadyButton;
	private boolean joined;

	public JoinPanel() {
		initComponents();
	}

	private void initComponents() {

		PlayerLabel = new JLabel();

		PlayerLabel.setText("PRESS ATTACK KEY, OR CONNECT A PHONE TO JOIN");

		this.add(PlayerLabel);
	}

	public void join(PlayerID id) {
		joined = true;
		setInfo();
		PassiveOptions = new JComboBox<>(Passive.values());
		PassiveOptions.setFocusable(false);
		PassiveOptions.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Display.id.setBuff((Passive) PassiveOptions.getSelectedItem());
			}
		});

		AggressiveOptions = new JComboBox<>(Aggressive.values());
		AggressiveOptions.setFocusable(false);
		AggressiveOptions.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Display.id.setSpecial((Aggressive) AggressiveOptions.getSelectedItem());
			}
		});
		
		Display = new Cat(id);
		ReadyButton = new JRadioButton();

		ReadyButton.setText("Ready");
		ReadyButton.setFocusable(false);

		setFullLayout();
	}

	public void setFullLayout() {
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(PlayerLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(PassiveOptions, 0, 115, Short.MAX_VALUE)
										.addComponent(AggressiveOptions, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(ReadyButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
								.addComponent(Display, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(PlayerLabel).addGap(42, 42, 42)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(PassiveOptions, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(37, 37, 37).addComponent(AggressiveOptions, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(Display, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
						.addComponent(ReadyButton).addContainerGap()));
	}

	private void setInfo() {
		PlayerLabel.setText(
				!joined ? "PRESS ANY KEY, OR CONNECT A PHONE TO JOIN" : "CHOOSE YOUR ABILITIES, SELECT READY TO START");
	}

	public void animate() {
		Display.displayAnimate();
	}

	public boolean isJoined() {
		return joined;
	}
	
	public boolean isReady() {
		return ReadyButton.isSelected();
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
	public void keyPressed(KeyEvent arg0) {
		if (joined) {
			System.out.println("Pressed");
			this.dispatchEvent(arg0);
		} else {

		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (joined) {
			System.out.println("Released");
			this.dispatchEvent(arg0);
		} else {

		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void sendToStage() {
		Display = new Cat(Display.id);
	}
}
