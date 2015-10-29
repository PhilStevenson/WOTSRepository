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
	JScrollPane itemsPane;
	
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
		mainMenu.setVisible(false);
		JButton exitButton = new JButton("Exit");
		CustOrder co = new CustOrder();
				
		frame.add(orderMenu);
		orderMenu.add(orderPanel);
		
		orderMenu.setLayout(new GridLayout(2,2));
		
		exitButton.setActionCommand("exit");
		exitButton.addActionListener(new BCL());
		
		
		
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
		
		orderPanel.add(exitButton);
		JScrollPane ordersPane = new JScrollPane(ordersList);
		//orderPanel.add(ordersList);
		orderPanel.add(ordersPane);
		
		ordersList.setVisible(true);
		orderPanel.setVisible(true);
		orderMenu.setVisible(true);
		frame.setVisible(true);
	

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
	
	private class MCL implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			if(itemsPane != null){
				itemsPane.setVisible(false);
				orderPanel.remove(itemsList);
			}
			int row = ordersList.getSelectedRow();
			CustOrder co = new CustOrder();
			String[][] orders = co.getOrders();
			
			String orderID = orders[row][0];
			
			
			System.out.println("ROW: " + row);
			System.out.println("ORDER ID: " + orderID);
			
			String[] columnNames = {"Product ID","Product", "Description", "Quantity", "Price"};
			
		    itemsList = new JTable(co.getOrderItems(orderID), columnNames);
		    
		    itemsPane = new JScrollPane(itemsList);
			//orderPanel.add(ordersList);
			orderPanel.add(itemsPane);
		    
		    itemsList.setVisible(true);
			itemsList.setVisible(true);
			orderPanel.setVisible(true);
			orderMenu.setVisible(true);
			frame.setVisible(true);
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
