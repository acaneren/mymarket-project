import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CustomerLoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField customerLoginEmail;
	private JTextField customerLoginPassword;
	private JTextField newUserName;
	private JTextField newUserSurname;
	private JTextField newUserEmail;
	private JTextField newUserTel;
	private JTextField newUserPassword;
	private JTextField newUserAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerLoginPage frame = new CustomerLoginPage();
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
	public CustomerLoginPage() {
		setTitle("Customer Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 240, 240);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email:");
		lblNewLabel.setBounds(10, 30, 69, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(10, 55, 69, 14);
		contentPane.add(lblNewLabel_1);
		
		customerLoginEmail = new JTextField();
		customerLoginEmail.setBounds(89, 27, 86, 20);
		contentPane.add(customerLoginEmail);
		customerLoginEmail.setColumns(10);
		
		customerLoginPassword = new JTextField();
		customerLoginPassword.setBounds(89, 52, 86, 20);
		contentPane.add(customerLoginPassword);
		customerLoginPassword.setColumns(10);
		
		JButton customerLoginButton = new JButton("Login");
		customerLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StoreDB storeDB = new StoreDB();
				
				try {
					if(storeDB.authenticateUser(customerLoginEmail.getText(), customerLoginPassword.getText())) {
						
						JOptionPane.showMessageDialog(null, "You have logged successfully");
						
						CustomerStorePage customerStorePage = new CustomerStorePage();
						customerStorePage.setVisible(true);
						dispose();
						
					}
					else {
						JOptionPane.showMessageDialog(null, "You have logged in incorrectly or you are not registered...");
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		customerLoginButton.setBounds(89, 83, 89, 23);
		contentPane.add(customerLoginButton);
		
		JButton customerNewUserButton = new JButton("New User");
		customerNewUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBounds(100, 100, 500, 300);
				
			}
		});
		customerNewUserButton.setBounds(89, 117, 89, 23);
		contentPane.add(customerNewUserButton);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setBounds(230, 30, 56, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Surname:");
		lblNewLabel_3.setBounds(230, 55, 56, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email:");
		lblNewLabel_4.setBounds(230, 83, 56, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Address:");
		lblNewLabel_5.setBounds(230, 158, 56, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Password:");
		lblNewLabel_6.setBounds(230, 133, 56, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Tel No:");
		lblNewLabel_7.setBounds(230, 108, 56, 14);
		contentPane.add(lblNewLabel_7);
		
		newUserName = new JTextField();
		newUserName.setBounds(296, 27, 132, 20);
		contentPane.add(newUserName);
		newUserName.setColumns(10);
		
		newUserSurname = new JTextField();
		newUserSurname.setBounds(296, 52, 132, 20);
		contentPane.add(newUserSurname);
		newUserSurname.setColumns(10);
		
		newUserEmail = new JTextField();
		newUserEmail.setBounds(296, 80, 132, 20);
		contentPane.add(newUserEmail);
		newUserEmail.setColumns(10);
		
		newUserTel = new JTextField();
		newUserTel.setBounds(296, 105, 132, 20);
		contentPane.add(newUserTel);
		newUserTel.setColumns(10);
		
		newUserPassword = new JTextField();
		newUserPassword.setBounds(296, 130, 132, 20);
		contentPane.add(newUserPassword);
		newUserPassword.setColumns(10);
		
		newUserAddress = new JTextField();
		newUserAddress.setBounds(296, 155, 132, 49);
		contentPane.add(newUserAddress);
		newUserAddress.setColumns(10);
		
		JButton customerRegisterButton = new JButton("Register");
		customerRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Customer customer = new Customer(newUserName.getText(),
						newUserSurname.getText(),newUserEmail.getText(), 
						newUserTel.getText(), newUserPassword.getText(), 
						newUserAddress.getText(), "Customer");
				
				StoreDB storeDB = new StoreDB();
				
				try {
					storeDB.saveNewCustomer(customer);
					JOptionPane.showMessageDialog(null, 
							"User " + customer.getName() + " " + customer.getSurname() + " has been successfully registered.");
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		customerRegisterButton.setBounds(296, 215, 132, 23);
		contentPane.add(customerRegisterButton);
		
		JButton customerLoginPageBackButton = new JButton("Back");
		customerLoginPageBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PanelPage panelPage = new PanelPage();
				panelPage.setVisible(true);
				dispose();
			}
		});
		customerLoginPageBackButton.setBounds(10, 168, 89, 23);
		contentPane.add(customerLoginPageBackButton);
	}
}
