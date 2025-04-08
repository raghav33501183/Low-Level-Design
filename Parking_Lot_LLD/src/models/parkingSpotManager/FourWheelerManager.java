package models.parkingSpotManager;

import java.util.List;

import models.ParkingSpot;
import models.parkingStrategy.ParkingStrategyFactory;
import models.vehicles.VehicleType;

public class FourWheelerManager extends ParkingSpotManager {
	FourWheelerManager(List<ParkingSpot> spots) {
        super(spots);
    }

    @Override
    public ParkingSpot findParkingSpace(List<ParkingSpot> spots, VehicleType vehicleType) {
        // Implementation to find nearest parking spot for Four Wheelers
        return (ParkingStrategyFactory.getParkingStrategy(vehicleType)).findSpot(spots, vehicleType);
    }
}
