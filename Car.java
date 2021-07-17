package org.pwc.application.secondTask;
@XmlRootElement
public class Car {
	private int id;
	private String type;
	private int numberOfSeats;
	private int RentPerHour;
	public Car(int id, String type, int numberOfSeats, int RentPerHour) {
		super();
		this.id = id;
		this.type = type;
		this.numberOfSeats = numberOfSeats;
		this.RentPerHour = RentPerHour;
	}
	
	public Car(int id) {
		super();
		this.id = id;
	}

	public Car() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public int getRentPerHour() {
		return RentPerHour;
	}
	public void setRentPerHour(int rentPerHour) {
		RentPerHour = rentPerHour;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + RentPerHour;
		result = prime * result + id;
		result = prime * result + numberOfSeats;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (RentPerHour != other.RentPerHour)
			return false;
		if (id != other.id)
			return false;
		if (numberOfSeats != other.numberOfSeats)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", type=" + type + ", numberOfSeats=" + numberOfSeats + ", RentPerHour=" + RentPerHour
				+ "]";
	}
}
