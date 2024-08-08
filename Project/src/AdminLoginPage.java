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

public class AdminLoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField adminLoginEmail;
	private JTextField adminPasswordLogin;
	private JButton adminLoginPageBackButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLoginPage frame = new AdminLoginPage();
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
	public AdminLoginPage() {
		setTitle("Admin Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 230);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email:");
		lblNewLabel.setBounds(10, 33, 70, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(10, 69, 70, 14);
		contentPane.add(lblNewLabel_1);
		
		adminLoginEmail = new JTextField();
		adminLoginEmail.setBounds(90, 30, 86, 20);
		contentPane.add(adminLoginEmail);
		adminLoginEmail.setColumns(10);
		
		adminPasswordLogin = new JTextField();
		adminPasswordLogin.setBounds(90, 66, 86, 20);
		contentPane.add(adminPasswordLogin);
		adminPasswordLogin.setColumns(10);
		
		JButton adminLoginButton = new JButton("Login");
		adminLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StoreDB storeDB = new StoreDB();
				
				try {
					if(storeDB.authenticateAdmin(adminLoginEmail.getText(), adminPasswordLogin.getText())) {
						JOptionPane.showMessageDialog(null, "Admin Login Successful");
						
						AdminStorePage adminStorePage = new AdminStorePage();
						adminStorePage.setVisible(true);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Admin login failed... Please check your e-mail and password.");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		adminLoginButton.setBounds(87, 108, 89, 23);
		contentPane.add(adminLoginButton);
		
		adminLoginPageBackButton = new JButton("Back");
		adminLoginPageBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PanelPage panelPage = new PanelPage();
				panelPage.setVisible(true);
				dispose();
			}
		});
		adminLoginPageBackButton.setBounds(185, 157, 89, 23);
		contentPane.add(adminLoginPageBackButton);
	}
}
