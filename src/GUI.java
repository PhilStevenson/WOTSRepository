import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class GUI extends JFrame {
	
	private JFrame initFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private JPanel orderPanel;

	public GUI() { prepareGUI(); }

	private void prepareGUI() {
		initFrame = new JFrame("Warehouse Order Tracking System");
		initFrame.setSize(400, 400);
		initFrame.setLayout(new GridLayout(3, 1));
		
		
		headerLabel = new JLabel("", JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.CENTER);
		statusLabel.setSize(350, 100);
		initFrame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		controlPanel = new JPanel();
		orderPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		orderPanel.setLayout(new FlowLayout());
		
		initFrame.add(headerLabel);
		initFrame.add(controlPanel);
		initFrame.add(statusLabel);

		initFrame.setVisible(true);
		
	}
	
	protected void showEvent() {
		headerLabel.setText("Press Button");
		
		JButton fufillButton = new JButton("Fufill Order");
		JButton restockButton = new JButton("Restock");
		JButton exitButton = new JButton("Exit");
		
		fufillButton.setActionCommand("VcusOrder");
		restockButton.setActionCommand("Rstock");
		exitButton.setActionCommand("exit");
		
		fufillButton.addActionListener(new BCL());
		restockButton.addActionListener(new BCL());
		exitButton.addActionListener(new BCL());
		
		controlPanel.add(fufillButton);
		controlPanel.add(restockButton);
		controlPanel.add(exitButton);
		
		initFrame.setVisible(true);
		
	}
	
	private class BCL implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
			switch (command) {
				case "VcusOrder":
					
					headerLabel.setVisible(false);
					initFrame.remove(headerLabel);
					controlPanel.setVisible(false);
					initFrame.remove(controlPanel);
					statusLabel.setVisible(false);
					initFrame.remove(statusLabel);
					
					initFrame.add(orderPanel);
					orderPanel.setVisible(true);
					
					initFrame.setSize(1280, 765);
					
					
				break;
				case "Rstock":
					statusLabel.setText("Submitted!");
				break;
				case "exit": 
					System.exit(0);
				break;
			}
		}
	}
}