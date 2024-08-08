import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class StoreDB {

	public Connection getConnected() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","12345");
	}	
	
	public boolean authenticateAdmin(String email, String password) throws SQLException{
		
		String query = "SELECT * FROM users WHERE userType = ? AND email = ? AND password = ?";
		
		try(Connection connection = getConnected();
			PreparedStatement ps = connection.prepareStatement(query)){
			
			ps.setString(0, "admin");
			ps.setString(1, email);
			ps.setString(2, "password");
			
			try (ResultSet rs = ps.executeQuery()){
				
				return rs.next();
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public boolean authenticateUser(String email, String password) throws SQLException{
		
		String query = "SELECT * FROM users WHERE userType = ? AND email = ? AND password = ?";
		
		try(Connection connection = getConnected();
			PreparedStatement ps = connection.prepareStatement(query)){
			
			ps.setString(1, "customer");
			ps.setString(2, email);
			ps.setString(3, password);
			
			try (ResultSet rs = ps.executeQuery()){
				
				return rs.next();
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public void addNewProduct(String product_name, int stock_quantity, String product_category, String product_price) {
		
		String query = "INSERT INTO products(product_name, stock_quantity, product_category, product_price) VALUES(?,?,?,?)";
		
		try(Connection connection = getConnected();
			PreparedStatement ps = connection.prepareStatement(query)){
				
				ps.setString(1, product_name);
				ps.setInt(2, stock_quantity);
				ps.setString(3, product_category);
				ps.setString(4, product_price);
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void saveNewCustomer(Customer customer) throws SQLException {
		
		String query = "INSERT INTO users(name, surname, email, telNo, password, address, userType) VALUES (?,?,?,?,?,?,?)";
		
		try(Connection connection = getConnected();
			PreparedStatement ps = connection.prepareStatement(query);) {
			
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getSurname());
			ps.setString(3, customer.getEmail());
			ps.setString(4, customer.getTelNo());
			ps.setString(5, customer.getPassword());
			ps.setString(6, customer.getAddress());
			ps.setString(7, "Customer");
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void deleteProduct(String product_name) {
		
		String query = "DELETE FROM products WHERE product_name = ?";
		
		try(Connection connection = getConnected();
			PreparedStatement ps = connection.prepareStatement(query)){
			
			ps.setString(1, product_name);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void customerPurchase(String product_name) {
		
		String query = "UPDATE products SET stock_quantity = stock_quantity - 1 WHERE product_name = ? ;";
		
		try(Connection connection = getConnected();
			PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setString(1, product_name);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
	
	

