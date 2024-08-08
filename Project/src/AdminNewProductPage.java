import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminNewProductPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField newProductName;
	private JTextField newProductStockQuantity;
	private JTextField newProductCategory;
	private JTextField newProductPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminNewProductPage frame = new AdminNewProductPage();
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
	public AdminNewProductPage() {
		setTitle("Admin New Product Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 290, 230);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product Name:");
		lblNewLabel.setBounds(10, 28, 94, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblProductStocu = new JLabel("Stock Quantity:");
		lblProductStocu.setBounds(10, 60, 94, 21);
		contentPane.add(lblProductStocu);
		
		JLabel lblProductCategory = new JLabel("Product Category:");
		lblProductCategory.setBounds(10, 92, 94, 21);
		contentPane.add(lblProductCategory);
		
		JLabel lblProductPrice = new JLabel("Product Price:");
		lblProductPrice.setBounds(10, 124, 94, 21);
		contentPane.add(lblProductPrice);
		
		newProductName = new JTextField();
		newProductName.setBounds(114, 28, 150, 20);
		contentPane.add(newProductName);
		newProductName.setColumns(10);
		
		newProductStockQuantity = new JTextField();
		newProductStockQuantity.setBounds(114, 60, 150, 20);
		contentPane.add(newProductStockQuantity);
		newProductStockQuantity.setColumns(10);
		
		newProductCategory = new JTextField();
		newProductCategory.setBounds(114, 92, 150, 20);
		contentPane.add(newProductCategory);
		newProductCategory.setColumns(10);
		
		newProductPrice = new JTextField();
		newProductPrice.setBounds(114, 124, 150, 20);
		contentPane.add(newProductPrice);
		newProductPrice.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StoreDB storeDB = new StoreDB();
				storeDB.addNewProduct(newProductName.getText(), Integer.parseInt(newProductStockQuantity.getText()), newProductCategory.getText(), newProductPrice.getText());
			}
		});
		btnNewButton.setBounds(114, 155, 150, 23);
		contentPane.add(btnNewButton);
		
		JButton newProductBackButton = new JButton("Back");
		newProductBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminStorePage adminStorePage = new AdminStorePage();
				adminStorePage.setVisible(true);
				dispose();
				
			}
		});
		newProductBackButton.setBounds(10, 157, 89, 23);
		contentPane.add(newProductBackButton);
	}

}
