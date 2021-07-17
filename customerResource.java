package org.pwc.application.secondTask;


import java.util.List;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("customer")
public class customerResource {
	CustomerRepositoryDAO crd = new CustomerRepositoryDAO();
	
	@GET
	@Path("customerList")
	@Produces(MediaType.APPLICATION_XML)
	public List<Customer> getCustomerList() {
		
		return crd.getCustomers();
		
	}
	
	@GET
	@Path("customer/{id}")
	public Customer get1customer(@PathParam("id") int id )
	{
		return  crd.getCustomer(id);
	}
	
	
	@POST
	@Path("addcustomer")
	
	public Customer addingCustomer(@FormParam("id") int id,@FormParam("name") String name,
			@FormParam("email") String email,@FormParam("password") String password,
			@FormParam("mobileNumber") long mobileNumber,@FormParam("category") String category,@FormParam("status") String status) {
		Customer customer = new Customer(id,name,email,password,mobileNumber,category,status);
	crd.createCustomer(customer);
	System.out.println("account created");
    return customer;
		
	}
	
	@PUT
	@Path("updatecustomer")
	public Customer updatingCustomer(Customer customer) {
		if (crd.getCustomer(customer.getId())== null){
			crd.createCustomer(customer);
			
		}
		else {
	crd.updateCustomer(customer);
		}
	System.out.println("account updation");
    return customer;
		
	}
	
	
	@DELETE
	@Path("deleteCustomer/{id}")
	public Customer deletecustomer(@PathParam("id") int id )
	{
		crd.deleteCustomer(id);
		System.out.println("account deleted");
		return  crd.getCustomer(id);
	}
	
    
		
	}


