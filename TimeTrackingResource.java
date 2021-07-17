package org.pwc.application.secondTask;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("TimeTracking")
public class TimeTrackingResource {
	Car c = new Car();
	long time;
	int amount;
	
	CarInventoryResource cir = new CarInventoryResource();
	
	TimeTrackingRepositoryDAO ttrd = new TimeTrackingRepositoryDAO();
	
	
	
	@GET
	@Path("TimeTrackingList")
	@Produces(MediaType.APPLICATION_XML)
	public List<Car> getBorrowedCarList() {
		
		return ttrd.getBorrowedCars();
		
	}
	
	
	@GET
	@Path("StartingTime/{id}")
	public  void rentCar(@PathParam("id") int id ) {
		
		ttrd.getStartTime(id);
		
		  	}
	
	
	@GET
	@Path("EndingTime/{id}")
	public void returnRentCar(@PathParam("id") int id) {
		ttrd.getEndTime(id);
		
	}
	 
	@GET
	@Path("Duration/{id}")
	public long durationTime(@PathParam("id") int id) {
		
		return ttrd.duration(id);
	}
	
	@GET
	@Path("Discount/{id}")
	public double discountGiven(@PathParam("id") int id) {
		Car car = new Car(id);
		long time =ttrd.duration(id);
		int amount = (int) ttrd.billingBefore(car, time);
		 return ttrd.discount(id,amount);
		
	}
	
	@GET
	@Path("Bill/{id}")
	public void billing(@PathParam("id") int id) {
		
		System.out.println(ttrd.billingAfter(id, time, amount));
		
		
	}
	
	
	
	
	

}
