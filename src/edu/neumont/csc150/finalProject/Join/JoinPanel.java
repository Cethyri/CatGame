package edu.neumont.csc150.finalProject.Join;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle;

import edu.neumont.csc150.finalProject.Actor.Player.Cat;
import edu.neumont.csc150.finalProject.Actor.Player.PlayerID;

@SuppressWarnings("serial")
public class JoinPanel extends JPanel {
	                     
	private JLabel PlayerLabel;
	private JComboBox<Passive> PassiveOptions;
	private JComboBox<Aggressive> AggressiveOptions;
	private Cat Display;
	private JRadioButton ReadyButton;                  

    public JoinPanel(PlayerID id) {
        initComponents(id);
    }
                       
    private void initComponents(PlayerID id) {

    	PlayerLabel = new JLabel();
        PassiveOptions = new JComboBox<>(Passive.values());
        AggressiveOptions = new JComboBox<>(Aggressive.values());
        Display = new Cat(id);
        ReadyButton = new JRadioButton();

        PlayerLabel.setText("PRESS ANY KEY, OR CONNECT A PHONE TO JOIN");

        ReadyButton.setText("Ready");

		//setVisibilityForInfo(false);
        
		GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(PlayerLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(PassiveOptions, 0, 115, Short.MAX_VALUE)
                            .addComponent(AggressiveOptions, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ReadyButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(Display, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PlayerLabel)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PassiveOptions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(AggressiveOptions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(Display, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(ReadyButton)
                .addContainerGap())
        );
    }// </editor-fold>                        

	private void setVisibilityForInfo(boolean visible) {
		PassiveOptions.setVisible(visible);
		AggressiveOptions.setVisible(visible);
		Display.setVisible(visible);
		ReadyButton.setVisible(visible);
	}
	
	public void animate() {
		Display.animate();
	}
}
