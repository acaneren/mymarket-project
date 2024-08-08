import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PanelPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelPage frame = new PanelPage();
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
	public PanelPage() {
		setTitle("Welcome!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton customerPanelButton = new JButton("Customer Panel");
		customerPanelButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		customerPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerLoginPage customerLoginPage = new CustomerLoginPage();
				customerLoginPage.setVisible(true);
				dispose();
			}
		});
		customerPanelButton.setBounds(114, 42, 194, 77);
		contentPane.add(customerPanelButton);
		
		JButton adminPanelButton = new JButton("Admin Panel");
		adminPanelButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		adminPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLoginPage adminLoginPage = new AdminLoginPage();
				adminLoginPage.setVisible(true);
				dispose();
			}
		});
		adminPanelButton.setBounds(114, 136, 194, 77);
		contentPane.add(adminPanelButton);
		
		JLabel lblNewLabel = new JLabel("WELCOME TO THE MYMARKET PLEASE CHOOSE YOUR LOGİN TYPE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setForeground(new Color(51, 0, 153));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 414, 14);
		contentPane.add(lblNewLabel);
	}

}
