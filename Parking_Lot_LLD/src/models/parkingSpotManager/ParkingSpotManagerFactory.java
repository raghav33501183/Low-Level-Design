package models.parkingSpotManager;

import java.util.List;

import models.ParkingSpot;
import models.vehicles.VehicleType;

public class ParkingSpotManagerFactory {
	public static ParkingSpotManager getParkingSpotManager(VehicleType vehicleType, List<ParkingSpot> spots) {
        if (vehicleType == VehicleType.BIKE)
            return new TwoWheelerManager(spots);
        else 
            return new FourWheelerManager(spots);
    }
}
