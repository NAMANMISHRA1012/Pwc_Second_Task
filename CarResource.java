package org.pwc.application.secondTask;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("car")
public class CarResource {
	CarRepositoryDAO card = new CarRepositoryDAO();
	
	@GET
	@Path("carList")
	@Produces(MediaType.APPLICATION_XML)
	public List<Car> getCarList() {
		
		return card.getCars();
	
	}
	
	@GET
	@Path("car/{id}")
	public Car get1car(@PathParam("id") int id )
	{
		return  card.getCar(id);
	}
	
	
	@POST
	@Path("createcar")
	public Car creatingCar(@FormParam("id") int id,@FormParam("type") String type,
			@FormParam("numberOfSeats") int numberOfSeats,@FormParam("RentPerHour") int RentPerHour) {
		Car car = new Car(id,type,numberOfSeats,RentPerHour); 
	card.createCar(car);
	System.out.println("car created");
    return car;
		
	}
	@PUT
	@Path("updatecar")
	public Car updatingCar(Car car) {
		if (card.getCar(car.getId())== null){
			card.createCar(car);
			
		}
		else {
	card.updateCar(car);
		}
	System.out.println("account updation");
    return car;
		
	}
	
	
	@DELETE
	@Path("deleteCar/{id}")
	public Car deletecar(@PathParam("id") int id )
	{
		card.deleteCar(id);
		System.out.println("account deleted");
		return  card.getCar(id);
	}
	

}
