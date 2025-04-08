package models;

import models.vehicles.Vehicle;

public class ParkingSpot {
	private int id;
	private boolean isEmpty = true;
	private Vehicle vehicle;
	private double price;
	
	public ParkingSpot(int id, double price) {
		this.id = id;
        this.isEmpty = true;
        this.vehicle = null;
        this.price = price;
	}
	
	public void parkVehicle(Vehicle v) {
        this.vehicle = v;
        this.isEmpty = false;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isEmpty = true;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getIsEmpty() {
		return isEmpty;
	}

	public void setIsEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
