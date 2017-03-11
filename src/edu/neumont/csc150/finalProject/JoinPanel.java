package edu.neumont.csc150.finalProject;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle;

@SuppressWarnings("serial")
public class JoinPanel extends javax.swing.JPanel {
	                     
	private JLabel PlayerLabel;
	private JComboBox<String> AggressiveOptions;
	private JComboBox<String> PassiveOptions;
	private JLabel Display;
	private JRadioButton ReadyButton;                  

    public JoinPanel() {
        initComponents();
    }
                       
    private void initComponents() {

    	PlayerLabel = new JLabel();
        PassiveOptions = new JComboBox<>();
        AggressiveOptions = new JComboBox<>();
        Display = new JLabel();
        ReadyButton = new JRadioButton();

        ReadyButton.setText("Ready");

        PassiveOptions.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        
        AggressiveOptions.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        PlayerLabel.setText("");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PlayerLabel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 106, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(ReadyButton)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(PassiveOptions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AggressiveOptions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
}
