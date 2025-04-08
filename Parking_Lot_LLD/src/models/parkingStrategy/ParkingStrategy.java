package models.parkingStrategy;

import java.util.List;

import models.ParkingSpot;
import models.vehicles.Vehicle;
import models.vehicles.VehicleType;

public interface ParkingStrategy {
	ParkingSpot findSpot(List<ParkingSpot> spots, VehicleType vehicleType);
}
