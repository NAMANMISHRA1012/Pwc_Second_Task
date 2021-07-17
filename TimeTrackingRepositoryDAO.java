package org.pwc.application.secondTask;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TimeTrackingRepositoryDAO {
	double discountAmount;
	long billBefore;
	Date date,date1;
	long time;
	CarInventoryResource cir = new CarInventoryResource();
	Car car = new Car();
	Customer customer = new Customer();
	List<Car> List;
	Connection con;
	public TimeTrackingRepositoryDAO() {
		try {
		Class.forName("com.mysql.jdbc.Driver");//register the driver
		 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/CUSTOMERS","root","naman1012");//establishing the connection
		
		}
		catch (Exception e){
			System.out.println(e);
			
		}
	}
	public List<Car> getBorrowedCars(){
		String sql = "select * from car_rental_tracker";
		try {
		Statement st = con.createStatement();
		 ResultSet rs = st.executeQuery(sql);
		 while(rs.next()) {
			 Car car = new Car();
			 
			List.add(car);	 
		 }
}
		catch(Exception e) {
			System.out.println(e);
		}
		return List;
		}
	
	
	
	public void getStartTime(int id ) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		 date = new Date(System.currentTimeMillis());
		System.out.println(formatter.format(date));
		
		String query = "update car_rental_tracker set start_time=?, where id ="+id;
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setDate(1,date);
			
			 st.executeUpdate();
			 
			}
			catch(Exception e) {
				System.out.println(e);
			}
		
	 	 
		
		
		
	}
	public void getEndTime(int id) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		 date1 = new Date(System.currentTimeMillis());
		System.out.println(formatter.format(date1));
		
		
		
		String query = "update car_rental_tracker set end_time=?, where id ="+id;
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setDate(1,date1);
			
			 st.executeUpdate();
			 
			}
			catch(Exception e) {
				System.out.println(e);
			}	
		
	}
	
	
	
	public long duration(int id) {
		 	long duration = (date.getTime())-(date1.getTime());
		 	long  amountOfTime=duration/(1000*60);
		     long time=(long) Math.ceil((double)amountOfTime/60);
		     String q = "update car_rental_tracker set duration=?, where id ="+id;
		     try {
					PreparedStatement st = con.prepareStatement(q);
					st.setLong(1,time);
					
					 st.executeUpdate();
					 
					}
					catch(Exception e) {
						System.out.println(e);
					}	
		     return  time;
		     
		    
		 	 
		 	 
		  }
	
	public long billingBefore(Car car,long time) {
		long billBefore = car.getRentPerHour()* time;
	     return billBefore;
	}
	
	
	public double discount(int id,int amountSpend) {
		
		int discount=0;
		if (customer.getCategory().compareTo("regular")== 0){
			if (amountSpend<= 4000) {
				discount=10;
				
			}
			else if (amountSpend> 4000 && amountSpend<8000) {
				discount=15;
				
			}
			else if (amountSpend> 8000 && amountSpend<12000) {
				discount=20;
				
			}
			else {
				discount=25;
			}
			
		}
		else if (customer.getCategory().compareTo("premium")== 0)
		{
			if (amountSpend<= 5000) {
				discount=0;
				
			}
			else if (amountSpend> 5000 && amountSpend<10000) {
				discount=10;
				
			}
			
			else {
				discount=20;
			}
			
		}
		else {
			System.out.println("Invalid customer type");
		}
		System.out.println(discount);
	discountAmount = (double)amountSpend*((double)discount/100);
	
	
	
	String query ="update  car_rental_tracker set discount=?, where id ="+id ;
	 try {
			PreparedStatement st = con.prepareStatement(query);
			st.setDouble(1,discountAmount);
			
			 st.executeUpdate();
			 
			}
			catch(Exception e) {
				System.out.println(e);
			}	
		
	 return discountAmount;
	}
	
	public double billingAfter(int id,long time,int amountSpend) {
		double billAfter = (double) (billBefore-discountAmount);
		String query ="update  car_rental_tracker set billingAmount=?, where id ="+id ;
		 try {
				PreparedStatement st = con.prepareStatement(query);
				st.setDouble(1,billAfter);
				
				 st.executeUpdate();
				 
				}
				catch(Exception e) {
					System.out.println(e);
				}	
		
		
	return billAfter;
	
		
	}
	}
		
	
	

 
