import javax.swing.*;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;

public class GUI extends JFrame {
	
	private JPanel mainPanel = new JPanel();
	private JPanel orderPanel = new JPanel();
	
	JButton exitButton = new JButton("Exit");

	
	Container mainMenu = new Container();
	Container orderMenu = new Container();
	
	JFrame frame = new JFrame("Warehouse Order Tracking System");

	public GUI() {
		//prepareGUI(); 
		init();
	}

	
	public void init()
	{
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	int xSize = ((int) tk.getScreenSize().getWidth());
	int ySize = ((int) tk.getScreenSize().getHeight());
	frame.setSize(xSize,ySize);
	

	
	mainMenu();
	frame.setVisible(true);
	}
	
	private void mainMenu() {
		mainMenu.setVisible(true);
		
		
		frame.add(mainMenu);
		mainMenu.add(mainPanel);
		
		mainMenu.setLayout(new FlowLayout());
		orderPanel.setLayout(new FlowLayout());
		
		
		mainPanel.setVisible(true);
		
		
		
		
		JButton orderButton = new JButton("Process Order");
		JButton stockButton = new JButton("Process Stock");
		
		
		orderButton.setActionCommand("processOrder");
		stockButton.setActionCommand("processStock");
		exitButton.setActionCommand("exit");
		
		
		orderButton.addActionListener(new BCL());
		stockButton.addActionListener(new BCL());
		exitButton.addActionListener(new BCL());
		
		mainPanel.add(orderButton);
		mainPanel.add(stockButton);
		mainPanel.add(exitButton);
		
		
	}
	
	private void processOrder() {
		mainMenu.setVisible(false);
		orderMenu.setVisible(true);
		orderMenu.add(orderPanel);
		
		
		exitButton.setActionCommand("exit");
		exitButton.addActionListener(new BCL());
		
		orderPanel.add(exitButton);
	}
	
	private void processStock() {
		
	}
	
	private class BCL implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
			switch (command) {
				case "processOrder":
					processOrder();
					
				break;
				case "processStock":
					
				break;
				case "exit": 
					System.exit(0);
				break;
			}
		}
	}
	
	
	
	
	
	
/*	
	
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
		
		mainMenu = new JPanel();
		orderPanel = new JPanel();
		mainMenu.setLayout(new FlowLayout());
		orderPanel.setLayout(new FlowLayout());
		
		initFrame.add(headerLabel);
		initFrame.add(mainMenu);
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
		
		mainMenu.add(fufillButton);
		mainMenu.add(restockButton);
		mainMenu.add(exitButton);
		
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
					mainMenu.setVisible(false);
					initFrame.remove(mainMenu);
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
*/
}