import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class CustomerStorePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_1;
	private JTable table_2;
	private JTextField customerSearchProductField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerStorePage frame = new CustomerStorePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerStorePage() {
		setTitle("MyMarket");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	    
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product Name", "Stock Quantity", "Product Category", "Product Price"
			}
		));
		
		table_1.getColumnModel().getColumn(0).setPreferredWidth(111);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(111);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(111);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(111);
		table_1.setBounds(10, 39, 300, 169);
		contentPane.add(table_1);
		
		JScrollPane scrollPane = new JScrollPane(table_1);
        scrollPane.setBounds(10, 39, 568, 169);
        contentPane.add(scrollPane);
        
        JLabel customerStorePageTotalPriceLabel = new JLabel("Total Price :");
        customerStorePageTotalPriceLabel.setBounds(390, 424, 188, 26);
        contentPane.add(customerStorePageTotalPriceLabel);
        
        JLabel customerLabel = new JLabel("Welcome Customer!");
        customerLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        customerLabel.setBounds(10, 11, 135, 14);
        contentPane.add(customerLabel);
        
        JLabel lblNewLabel = new JLabel("Basket");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(10, 219, 188, 14);
        contentPane.add(lblNewLabel);
        
        
        
        
        table_2 = new JTable();
        table_2.setModel(new DefaultTableModel(
    			new Object[][] {
    			},
    			new String[] {
    				"Product Name", "Quantity", "Product Category", "Product Price"
    			}
    		));
        table_2.getColumnModel().getColumn(0).setPreferredWidth(111);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(111);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(111);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(111);
        table_2.setBounds(10, 255, 566, 169);
        contentPane.add(table_2);
        
        JScrollPane scrollPane2 = new JScrollPane(table_2);
        scrollPane2.setBounds(10, 244, 566, 169);
        contentPane.add(scrollPane2);
        
        JButton customerAddToBasketButton = new JButton("Add to Basket");
        customerAddToBasketButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		int selectedRow = table_1.getSelectedRow();
        		if(selectedRow != -1) {
        			
        			
                    String productName = String.valueOf(table_1.getValueAt(selectedRow, 0));
                    int stockQuantity = 1;
                    String productCategory = String.valueOf(table_1.getValueAt(selectedRow, 2));
                    double productPrice = (double) table_1.getValueAt(selectedRow, 3);
                    
              
                    DefaultTableModel model2 = (DefaultTableModel) table_2.getModel();
                    model2.addRow(new Object[] { productName, stockQuantity, productCategory, productPrice });
        			
                    customerStorePageTotalPriceLabel.setText("Total Price : " + String.valueOf(calculateTotalPrice()));
               
        		}
        	}
        });
        customerAddToBasketButton.setBounds(588, 137, 148, 71);
        contentPane.add(customerAddToBasketButton);
        
        JButton customerDeleteFromTheBasketButton = new JButton("Delete from Basket");
        customerDeleteFromTheBasketButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		int selectedRow = table_2.getSelectedRow();
        		if(selectedRow != -1) {
        			
        			DefaultTableModel model2 = (DefaultTableModel) table_2.getModel();
        			model2.removeRow(selectedRow);
        			
        			customerStorePageTotalPriceLabel.setText("Total Price : " + String.valueOf(calculateTotalPrice()));
        			
        		}
        	}
        });
        customerDeleteFromTheBasketButton.setBounds(586, 244, 150, 45);
        contentPane.add(customerDeleteFromTheBasketButton);
        
        JButton customerPurchaseButton = new JButton("Purchase");
        customerPurchaseButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		int countRow = table_2.getRowCount();
        		
        		StoreDB storeDB = new StoreDB();
        		
        		for(int i=0; i < countRow; i++) {
        			storeDB.customerPurchase(String.valueOf(table_2.getValueAt(i, 0)));
        		}
        		
        		JOptionPane.showMessageDialog(null, "Your order has been created successfully.\n"
			        								+"We have started preparing your order.\n"
			        								+"Estimated delivery time: 20 minutes.\n"
			        								+"Thank you!");
        		
        		dispose();    		
        	}
        });
        customerPurchaseButton.setBounds(586, 342, 150, 71);
        contentPane.add(customerPurchaseButton);
        
        customerSearchProductField = new JTextField();
        customerSearchProductField.setBounds(259, 8, 319, 20);
        contentPane.add(customerSearchProductField);
        customerSearchProductField.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Search Product :");
        lblNewLabel_1.setBounds(154, 11, 95, 17);
        contentPane.add(lblNewLabel_1);
        
        JButton customerSearchProductButton = new JButton("Search");
        customerSearchProductButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		searchInDatabase(customerSearchProductField.getText());
        	}
        });
        customerSearchProductButton.setBounds(588, 8, 148, 23);
        contentPane.add(customerSearchProductButton);
        

        loadDataFromDatabase();
    }
	
	private double calculateTotalPrice() {
		
		DefaultTableModel model2 = (DefaultTableModel) table_2.getModel();
		
		int rowCount = table_2.getRowCount();
		double totalPrice = 0;
		
		for(int i=0; i < rowCount; i++) {
			
			totalPrice = totalPrice + (double) table_2.getValueAt(i, 3);
		}
		
		return totalPrice;
	}
	
    private void loadDataFromDatabase() {
        
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root","12345");
            Statement statement = connect.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM products");

            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
            model.setRowCount(0);
            
            while (rs.next()) {
            	String productName = rs.getString("product_name");
                int stockQuantity = rs.getInt("stock_quantity");
                String productCategory = rs.getString("product_category");
                double productPrice = rs.getDouble("product_price");

                model.addRow(new Object[] { productName, stockQuantity, productCategory, productPrice });
            }

            rs.close();
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();   
        }
	}
    
    
    private void searchInDatabase(String product_name) {
        
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root","12345");
            Statement statement = connect.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM products WHERE product_name LIKE '%" + product_name + "%';");

            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
            model.setRowCount(0);
            
            while (rs.next()) {
            	String productName = rs.getString("product_name");
                int stockQuantity = rs.getInt("stock_quantity");
                String productCategory = rs.getString("product_category");
                double productPrice = rs.getDouble("product_price");

                model.addRow(new Object[] { productName, stockQuantity, productCategory, productPrice });
            }

            rs.close();
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();   
        }
	}
}
