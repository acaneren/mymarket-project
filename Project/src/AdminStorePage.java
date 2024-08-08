import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;

public class AdminStorePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_1;
	private JButton refreshTableButton;
	private JButton adminNewProduct;
	private JButton adminLogOutButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminStorePage frame = new AdminStorePage();
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
	public AdminStorePage() {
		setTitle("Admin Store Control Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 300);
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
        
        refreshTableButton = new JButton("Refresh");
        refreshTableButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		loadDataFromDatabase();
        	}
        });
        refreshTableButton.setBounds(588, 42, 134, 37);
        contentPane.add(refreshTableButton);
        
        adminNewProduct = new JButton("New Product");
        adminNewProduct.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AdminNewProductPage adminNewProductPage = new AdminNewProductPage();
        		adminNewProductPage.setVisible(true);
        		dispose();
        		
        	}
        });
        adminNewProduct.setBounds(588, 114, 134, 23);
        contentPane.add(adminNewProduct);
        
        adminLogOutButton = new JButton("Log Out");
        adminLogOutButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AdminLoginPage adminLoginPage = new AdminLoginPage();
        		adminLoginPage.setVisible(true);
        		dispose();
        	}
        });
        adminLogOutButton.setBounds(588, 186, 134, 23);
        contentPane.add(adminLogOutButton);
        
        JButton adminDeleteProductButton = new JButton("Delete Product");
        adminDeleteProductButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		int selectedRow = table_1.getSelectedRow();
        		if(selectedRow != -1) {
        			String product_name = String.valueOf(table_1.getValueAt(selectedRow, 0));
        			
        			StoreDB storeDB = new StoreDB();
        			storeDB.deleteProduct(product_name);
        			
        			loadDataFromDatabase();
        		}
        	}
        });
        adminDeleteProductButton.setBounds(588, 152, 134, 23);
        contentPane.add(adminDeleteProductButton);
        
        JLabel adminLabel = new JLabel("Welcome Admin!");
        adminLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        adminLabel.setBounds(10, 11, 335, 14);
        contentPane.add(adminLabel);
  
        loadDataFromDatabase();
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
}
