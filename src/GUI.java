import javax.swing.*;
import javax.swing.table.TableColumn;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;

public class GUI extends JFrame {
	
	private JPanel mainPanel = new JPanel();
	private JPanel orderPanel = new JPanel();
	private JTable ordersList;
	private JTable itemsList;
	private int itemsListIndex;
	private JScrollPane itemsPane;
	private JTextArea orderDetails;
	private JComboBox statusList;
	private JLabel statusLabel;
	
	private String currentOrderID;
	
	Container mainMenu = new Container();
	Container orderMenu = new Container();
	
	JFrame frame = new JFrame("Warehouse Order Tracking System");

	public GUI() {
		//prepareGUI(); 
		init();
	}

	
	public void init() {
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	int xSize = ((int) tk.getScreenSize().getWidth());
	int ySize = ((int) tk.getScreenSize().getHeight());
	frame.setSize(xSize,ySize);
	orderMenu.setSize(xSize, ySize);
	mainMenu();
	frame.setVisible(true);
	}
	
	private void mainMenu() {
		
		frame.add(mainMenu);
		mainMenu.add(mainPanel);
		
		mainMenu.setLayout(new FlowLayout());
		
		
		JButton orderButton = new JButton("Process Order");
		JButton stockButton = new JButton("Process Stock");
		JButton exitButton = new JButton("Exit");
		
		orderButton.setActionCommand("processOrder");
		stockButton.setActionCommand("processStock");
		exitButton.setActionCommand("exit");
		
		
		orderButton.addActionListener(new BCL());
		stockButton.addActionListener(new BCL());
		exitButton.addActionListener(new BCL());
		
		mainPanel.add(orderButton);
		mainPanel.add(stockButton);
		mainPanel.add(exitButton);
		
		
		mainMenu.setVisible(true);
		mainPanel.setVisible(true);
	}
	
	private void processOrder() {
		
		orderPanel.removeAll();
		
		mainMenu.setVisible(false);
		JButton backButton = new JButton("Back");
		CustOrder co = new CustOrder();
				
		frame.add(orderMenu);
		orderMenu.add(orderPanel);
		
		orderMenu.setLayout(new FlowLayout());
		
		backButton.setActionCommand("back");
		backButton.addActionListener(new BCL());
		
		
		
		String[] columnNames = {"Order ID",	"GDZone", "Order Placed", "Status"};
		
		//JList ordersList = new JList(co.printOrders());
		ordersList = new JTable(co.getOrders(), columnNames);
		
	    TableColumn column = null;
	    for (int i = 0; i < 3; i++) {
	        column = ordersList.getColumnModel().getColumn(i);
	        if (i == 2) {
	            column.setPreferredWidth(180); //sport column is bigger
	        } else {
	            column.setPreferredWidth(80);
	        }
	    }
	    
	    
		ordersList.addMouseListener(new MCL());
		
		orderPanel.add(backButton);
		JScrollPane ordersPane = new JScrollPane(ordersList);

		orderPanel.add(ordersPane);
		
		ordersList.setVisible(true);
		orderPanel.setVisible(true);
		orderMenu.setVisible(true);
		frame.setVisible(true);
	

	}

	private void processOrder(String orderID) {
		
		orderPanel.removeAll();
		
		mainMenu.setVisible(false);
		JButton backButton = new JButton("Back");
		CustOrder co = new CustOrder();
				
		frame.add(orderMenu);
		orderMenu.add(orderPanel);
		
		orderMenu.setLayout(new FlowLayout());
		
		backButton.setActionCommand("back");
		backButton.addActionListener(new BCL());
		
		
		
		String[] columnNames = {"Order ID",	"GDZone", "Order Placed", "Status"};
		
		//JList ordersList = new JList(co.printOrders());
		ordersList = new JTable(co.getOrders(), columnNames);
		
	    TableColumn column = null;
	    for (int i = 0; i < 3; i++) {
	        column = ordersList.getColumnModel().getColumn(i);
	        if (i == 2) {
	            column.setPreferredWidth(180); //sport column is bigger
	        } else {
	            column.setPreferredWidth(80);
	        }
	    }
	    
	    ordersList.setRowSelectionInterval(itemsListIndex, itemsListIndex);
		ordersList.addMouseListener(new MCL());
		
		orderPanel.add(backButton);
		JScrollPane ordersPane = new JScrollPane(ordersList);

		orderPanel.add(ordersPane);
		
		ordersList.setVisible(true);
		orderPanel.setVisible(true);
		orderMenu.setVisible(true);

		frame.setVisible(true);
		
		displayOrder();

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
				case "back":
					orderMenu.removeAll();
					orderPanel.removeAll();
					orderMenu.setVisible(false);
					mainMenu.setVisible(true);
			}
		}
	}
	
	private class CBL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			
			CustOrder co = new CustOrder();
			
			switch(String.valueOf(statusList.getSelectedItem())) {
				case "Incomplete":
					co.updateStatus(currentOrderID,"Incomplete");
					break;
				case "Pending":
					co.updateStatus(currentOrderID,"Pending");
					break;
				case "Packed":
					co.updateStatus(currentOrderID,"Pending");
					break;
				case "Delivery":
					co.updateStatus(currentOrderID, "Delivery");
					break;
				case "Complete":
					co.updateStatus(currentOrderID, "Complete");
					break;
					
					//TODO update textarea
			}
			
			//Reinitialize
			processOrder(currentOrderID);
			
			//System.out.println(statusList.getSelectedItem());
			
		}
		
	}
	
	private void displayOrder() {
		if(itemsPane != null){
			itemsPane.setVisible(false);
			orderDetails.setVisible(false);
			statusLabel.setVisible(false);
			statusList.setVisible(false);
			
			orderPanel.remove(statusLabel);
			orderPanel.remove(statusList);
			orderPanel.remove(itemsList);
			orderPanel.remove(orderDetails);
		}
		
		itemsListIndex = ordersList.getSelectedRow();
		CustOrder co = new CustOrder();
		String[][] orders = co.getOrders();
		
		currentOrderID = orders[itemsListIndex][0];
		
		
		System.out.println("ROW: " + itemsListIndex);
		System.out.println("ORDER ID: " + currentOrderID);
		
		String[] columnNames = {"Product ID","Product", "Description", "Quantity", "Price", "Warehouse Location"};
		
	    itemsList = new JTable(co.getOrderItems(currentOrderID), columnNames);
	    
	    itemsPane = new JScrollPane(itemsList);
	    
	    orderDetails = new JTextArea();
	    
	    String[] orderdeets = co.getOrderDetails(currentOrderID);
	    
	    orderDetails.setText(
	    					"Order ID: \t" + orderdeets[0] + "\n\n" + 
	    					"Customer: \t" + orderdeets[1] + " " + orderdeets[2] + "\n\n" + 
	    					"Order Placed: \t" + orderdeets[3] + "\n\n" + 
	    					"GDZ: \t" + orderdeets[4] + "\n\n" + 
	    					"Current Status: \t" + orderdeets[5]);
	    
	    orderPanel.add(orderDetails);
	    
	    statusLabel = new JLabel();
	    
	    statusLabel.setText("Status: ");
	    
	    orderPanel.add(statusLabel);
	    
	    
	    String[] statuses = {"Incomplete","Pending", "Packed", "Delivery", "Complete"};
	    
	    statusList = new JComboBox(statuses);
	    
	    statusList.setSelectedItem(orderdeets[5]);
	    
	    statusList.addActionListener(new CBL());
	    
	    orderPanel.add(statusList);
	    
	    
		orderPanel.add(itemsPane);
	    
		statusLabel.setVisible(true);
		statusList.setVisible(true);
		orderDetails.setVisible(true);
	    itemsList.setVisible(true);
		itemsList.setVisible(true);
		orderPanel.setVisible(true);
		orderMenu.setVisible(true);
		frame.setVisible(true);
	}
	
	private class MCL implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			displayOrder();
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
