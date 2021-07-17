package org.pwc.application.secondTask;
import java.util.List;
import java.sql.*;

public class CarRepositoryDAO {
	List<Car> carList;
	Connection con;
	public CarRepositoryDAO() {
		try {
		Class.forName("com.mysql.jdbc.Driver");//register the driver
		 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/CUSTOMERS","root","naman1012");//establishing the connection
		
		}
		catch (Exception e){
			System.out.println(e);
			
		}
	}
	public List<Car> getCars(){
		String sql = "select* from car_details";
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
	
	public Car getCar(int id){
		String sql = "select* from car_details where id ="+id;
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
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return car;
		
		}
	public void createCar(Car car) {
		String sql = "insert into car_details values (?,?,?,?)";
		
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
		public void updateCar(Car car) {
			String sql = "update car_details set type =?,numberOfSeats=?,RentPerHour=? where id =?";
			
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
		
		
		public void deleteCar(int id) {
			String sql ="delete from car_details where id=?";
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
