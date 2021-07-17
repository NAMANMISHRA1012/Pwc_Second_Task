package org.pwc.application.secondTask;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

public class CarInventoryRepositoryDAO {
	List<Car> carList;
	Connection con;
	public CarInventoryRepositoryDAO() {
		try {
		Class.forName("com.mysql.jdbc.Driver");//register the driver
		 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/CUSTOMERS","root","naman1012");//establishing the connection
		
		}
		catch (Exception e){
			System.out.println(e);
			
		}
	}
	public List<Car> getCars(){
		String sql = "select * from car_inventory";
		try {
		Statement st = con.createStatement();
		 ResultSet rs = st.executeQuery(sql);
		 while(rs.next()) {
			 Car car = new Car();
			 car.setId(rs.getInt(1));
			 car.setType(rs.getString(2));
			 car.setNumberOfSeats(rs.getInt(3));
			 car.setRentPerHour(rs.getInt(4));
			 carList.add(car);	 
		 }
}
		catch(Exception e) {
			System.out.println(e);
		}
		return carList;
		}
	
	public Car borrowCar(int id) {
		String sql = "select * from car_inventory where id ="+id;
		String s2="insert into car_rental_tracker(id) select id from car_inventory";
		Car car = new Car();
		try {
		Statement st = con.createStatement();
		 ResultSet rs = st.executeQuery(sql);
		 if(rs.next()) {
			 
			 car.setId(rs.getInt(1));
			 car.setType(rs.getString(2));
			 car.setNumberOfSeats(rs.getInt(3));
			 car.setRentPerHour(rs.getInt(4));
		 }
		 
			 String s ="delete from car_inventory where id=?";
				try {
					PreparedStatement ps = con.prepareStatement(s);
					ps.setInt(1, id);
					 ps.executeUpdate();
				}
				catch(Exception e) {
					System.out.println(e);
				}
			 			 
		 
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
		System.out.println("car is borrowed");
		return car;
		
		
	}
	
	public void addCar(Car car) {
		String sql = "insert into car_inventory values (?,?,?,?)";
		
		try {
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, car.getId());
		st.setString(2,car.getType());
		st.setInt(3,car.getNumberOfSeats());
		st.setInt(4,car.getRentPerHour());
		
		 st.executeUpdate();
		  
		 
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void returnCar(Car car) {
		String sql = "update car_inventory set type =?,numberOfSeats=?,RentPerHour=? where id =?";
		
		try {
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,car.getType());
		st.setInt(2,car.getNumberOfSeats());
		st.setInt(3,car.getRentPerHour());
		st.setInt(4, car.getId());
		 st.executeUpdate();
		 
		}
		catch(Exception e) {
			System.out.println(e);
		}
	
		
	}
	
		
		
		
		
		
	
	
	
		
		
}	
		