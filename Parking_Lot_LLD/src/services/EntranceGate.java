package services;

import java.util.Date;
import java.util.List;

import models.ParkingSpot;
import models.Ticket;
import models.parkingSpotManager.ParkingSpotManager;
import models.parkingSpotManager.ParkingSpotManagerFactory;
import models.vehicles.Vehicle;
import models.vehicles.VehicleType;

public class EntranceGate {

    public Ticket generateTicket(Vehicle vehicle, ParkingSpotManager manager) {
    	var spot = manager.parkVehicle(vehicle);
    	return new Ticket(new Date().getTime(), spot, vehicle);
    }
}
