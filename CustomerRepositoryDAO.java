package org.pwc.application.secondTask;// DEALS WITH DATABSE


import java.util.List;
import java.sql.*;
public class CustomerRepositoryDAO {
	
	
	List<Customer> customerList;
	Connection con;
	public CustomerRepositoryDAO() {
		try {
		Class.forName("com.mysql.jdbc.Driver");//register the driver
		 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/CUSTOMERS","root","naman1012");//establishing the connection
		
		}
		catch (Exception e){
			System.out.println(e);
			
		}
	}
	
	public List<Customer> getCustomers(){
		String sql = "select * from customer_details";
		try {
		Statement st = con.createStatement();
		 ResultSet rs = st.executeQuery(sql);
		 while(rs.next()) {
			 Customer customer = new Customer();
			 customer.setId(rs.getInt(1));
			 customer.setName(rs.getString(2));
			 customer.setEmail(rs.getString(3));
			 customer.setPassword(rs.getString(4));
			 customer.setMobileNumber(rs.getLong(5));
			 customer.setCategory(rs.getString(6));
			 customer.setStatus(rs.getString(7));
			 customerList.add(customer);
			 
		 }
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return customerList;
		}
	
	public Customer getCustomer(int id ) {
		String sql = "select * from customer_details where id ="+id;
		Customer customer = new Customer();
		try {
		Statement st = con.createStatement();
		 ResultSet rs = st.executeQuery(sql);
		 if(rs.next()) {
			 
			 customer.setId(rs.getInt(1));
			 customer.setName(rs.getString(2));
			 customer.setEmail(rs.getString(3));
			 customer.setPassword(rs.getString(4));
			 customer.setMobileNumber(rs.getLong(5));
			 customer.setCategory(rs.getString(6));
			 customer.setStatus(rs.getString(7));
			 
			 
		 }
		}
		catch(Exception e) {
			System.out.println(e);
		}
	

		return customer;
}
	public void createCustomer(Customer customer) {
		String sql = "insert into customer_details values (?,?,?,?,?,?,?)";
		
		try {
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, customer.getId());
		st.setString(2,customer.getName());
		st.setString(3,customer.getEmail());
		st.setString(4,customer.getPassword());
		st.setLong(5,customer.getMobileNumber());
		st.setString(6,customer.getCategory());
		st.setString(7,customer.getStatus());
		 st.executeUpdate();
		 	 
		}
		catch(Exception e) {
			System.out.println(e);
		}
		

		
	}
	public void updateCustomer(Customer customer) {
		String sql = "update customer_details set name =?,email=?,password=?,mobileNumber=?,category =?,status =? where id =?";
		
		try {
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,customer.getName());
		st.setString(2,customer.getEmail());
		st.setString(3,customer.getPassword());
		st.setLong(4,customer.getMobileNumber());
		st.setString(5,customer.getCategory());
		st.setString(6,customer.getStatus());
		st.setInt(7, customer.getId());
		 st.executeUpdate();
		 
		}
		catch(Exception e) {
			System.out.println(e);
		}
	
		
	}
	
	
	public void deleteCustomer(int id) {
		String sql ="delete from customer_details where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			 st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	
		
		
	}

}


