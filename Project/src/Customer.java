
public class Customer {
	
	String name, surname, email, telNo, password, address, userType;
	
	public Customer(String name, String surname, String email, String telNo, String password, String address, String userType) {
		
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.telNo = telNo;
		this.password = password;
		this.address = address;
		this.userType = userType;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
