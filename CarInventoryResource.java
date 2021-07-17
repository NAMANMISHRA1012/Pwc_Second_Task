 package org.pwc.application.secondTask;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("inventory")
public class CarInventoryResource {

	CarInventoryRepositoryDAO cird = new CarInventoryRepositoryDAO();
	TimeTrackingRepositoryDAO ttrd = new TimeTrackingRepositoryDAO();
	TimeTrackingResource ttr = new TimeTrackingResource();
	@GET
	@Path("inventoryList")
	@Produces(MediaType.APPLICATION_XML)
	public List<Car> getCarList() {
		
		return cird.getCars();
		
	}
	@GET
	@Path("borrowcar/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Car borrowingAcar(@PathParam("id") int id) {
		ttr.rentCar(id);
		 return cird.borrowCar(id);
		 
		
	}
	@POST
	@Path("addcar")
	public Car addingCar(Car car) {
		
	cird.addCar(car);
	System.out.println("car created");
    return car;
	}
	
	@POST
	@Path("returncar")
	public Car returningCar(Car car) {
		System.out.println(car);
		if(cird.borrowCar(car.getId())==null) {
			cird.addCar(car);
		}
		else {
			cird.returnCar(car);
		}
		System.out.println("car is returned");
		ttr.returnRentCar(car.getId());
		return car;
		
	}
	
	}
