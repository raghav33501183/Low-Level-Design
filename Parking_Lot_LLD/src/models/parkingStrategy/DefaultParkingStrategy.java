package models.parkingStrategy;

import java.util.List;

import models.ParkingSpot;
import models.vehicles.Vehicle;
import models.vehicles.VehicleType;

public class DefaultParkingStrategy implements ParkingStrategy {

	@Override
	public ParkingSpot findSpot(List<ParkingSpot> spots, VehicleType vehicleType) {
		for (var spot : spots) {
            if (spot.getIsEmpty()) {
                return spot;
            }
        }
		
		throw new RuntimeException("No empty parking spot found");
	}

}