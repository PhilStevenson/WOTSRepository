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
	
	private String[] statuses = {"Incomplete","Pending", "Packing", "Delivery", "Complete", "Cancelled"};
	
	private String currentOrderID;
	
	Container mainMenu = new Container();
	Container orderMenu = new Container();
	
	JFrame frame = new JFrame("Warehouse Order Tracking System");

	public GUI() {
		//prepareGUI(); 
		init();
	}

	
	public void init() {
	
	// this sets the jframe window to the size of the screen
	Toolkit tk = Toolkit.getDefaultToolkit();
	int xSize = ((int) tk.getScreenSize().getWidth());
	int ySize = ((int) tk.getScreenSize().getHeight());
	frame.setSize(xSize,ySize);
	orderMenu.setSize(xSize, ySize);
	
	
	mainMenu();		// call the mainMenu
	
	frame.setVisible(true); 	//make the frame visible
	}
	
	// creates the Main menu
	private void mainMenu() {
		
		// add main components 
		frame.add(mainMenu);
		mainMenu.add(mainPanel);
		
		// set the layout, not sure this even does anything
		mainMenu.setLayout(new FlowLayout());
		
		// create new buttons
		JButton orderButton = new JButton("Process Order");
		JButton stockButton = new JButton("Process Stock");
		JButton exitButton = new JButton("Exit");
		
		//set the commands for the buttons 
		orderButton.setActionCommand("processOrder");
		stockButton.setActionCommand("processStock");
		exitButton.setActionCommand("exit");
		
		// create action listeners for buttons
		orderButton.addActionListener(new BCL());
		stockButton.addActionListener(new BCL());
		exitButton.addActionListener(new BCL());
		
		// add the buttons the main panel
		mainPanel.add(orderButton);
		mainPanel.add(stockButton);
		mainPanel.add(exitButton);
		
		// set the components visible
		mainMenu.setVisible(true);
		mainPanel.setVisible(true);
	}
	
	
	// creates the process order menu called when the process order button is pressed
	private void processOrder() {
		
		// ensure the panel is empty
		orderPanel.removeAll();
		
		// hide the main menu
		mainMenu.setVisible(false);
		
		// create a back button
		JButton backButton = new JButton("Back");
		
		// create a new custorder object
		CustOrder co = new CustOrder();
		
		// add the orderMenu to the frame
		frame.add(orderMenu);
		// add the order panel to the order menu
		orderMenu.add(orderPanel);
		
		// set the layout of the order menu
		orderMenu.setLayout(new FlowLayout());
		
		//create the command and action listener for the back button
		backButton.setActionCommand("back");
		backButton.addActionListener(new BCL());
		
		// create column headings for the list of orders
		String[] columnNames = {"Order ID",	"GDZone", "Order Placed", "Status"};
		
		//JList ordersList = new JList(co.printOrders());
		ordersList = new JTable(co.getOrders(), columnNames);
		
		// taken this from somewhere to get help the jtables work but actually don remember if it makes a difference
	    TableColumn column = null;
	    for (int i = 0; i < 3; i++) {
	        column = ordersList.getColumnModel().getColumn(i);
	        if (i == 2) {
	            column.setPreferredWidth(180); //sport column is bigger
	        } else {
	            column.setPreferredWidth(80);
	        }
	    }
	    
	    // create a mouse listener for the order list
		ordersList.addMouseListener(new MCL());
		
		// add the back button to the order panel
		orderPanel.add(backButton);
		
		// add the orders list to a new scroll pane, this is needed to insure the column headings are displayed
		JScrollPane ordersPane = new JScrollPane(ordersList);

		//add the orderspane to the order panel
		orderPanel.add(ordersPane);
		
		// set everything visible
		ordersList.setVisible(true);
		orderPanel.setVisible(true);
		orderMenu.setVisible(true);
		frame.setVisible(true);
	}

	// Overloaded method to provide functionality  for displaying a specific order when the GUI is rendered
	// therefore all comments pretty much the same
	
	// this should be changed now as orderID argument is no longer used
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
		
		displayOrder(); // this is an addition to this overloaded method 

	}

	// This method is to create the process stock menu
	private void processStock() {
		
	}
	
	// action listener for the buttons 
	private class BCL implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
			switch (command) {
				case "processOrder":
					processOrder();		// when the process order button is pressed then create process order menu
					
				break;
				case "processStock":
					
				break;
				case "exit": 
					System.exit(0); 	// exit the system 
				break;
				case "back":			// open the main menu
					orderMenu.removeAll();
					orderPanel.removeAll();
					orderMenu.setVisible(false);
					mainMenu.setVisible(true);
			}
		}
	}
	
	// Action listener for change of drop down for order status
	private class CBL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			
			CustOrder co = new CustOrder();
			Product p = new Product();
			
			// case statements change the order to the appropriate status.
			// the statuses are in order of the process and therefore when the order gets to the packing status the stock is allocated to the order and the stock level is updated
			// this is the same for the later statuses apart from cancelled and for the statuses that come before packing there is a method to un-allocate stock, this is also used in the cancelled status 
			switch(String.valueOf(statusList.getSelectedItem())) {
				case "Incomplete":
					co.updateStatus(currentOrderID,"Incomplete");
					p.unAllocateStock(currentOrderID);
					break;
				case "Pending":
					co.updateStatus(currentOrderID,"Pending");
					p.unAllocateStock(currentOrderID);
					break;
				case "Packing":
					co.updateStatus(currentOrderID,"Packing");
					p.allocateStock(currentOrderID);
					break;
				case "Delivery":
					co.updateStatus(currentOrderID, "Delivery");
					p.allocateStock(currentOrderID);
					break;
				case "Complete":
					co.updateStatus(currentOrderID, "Complete");
					p.allocateStock(currentOrderID);
					break;
				case "Cancelled":
					co.updateStatus(currentOrderID, "Cancelled");
					p.unAllocateStock(currentOrderID);
			}
			
			//Reinitialise
			processOrder(currentOrderID);
			
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
		
		
		//System.out.println("ROW: " + itemsListIndex);
		//System.out.println("ORDER ID: " + currentOrderID);
		
		String[] columnNames = {"Product ID","Product", "Description", "Ordered Quantity", "Stock", "Price", "Warehouse Location"};
		
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
