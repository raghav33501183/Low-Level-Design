package models.parkingStrategy;

import java.util.List;

import models.vehicles.VehicleType;

public class ParkingStrategyFactory {
	public static ParkingStrategy getParkingStrategy(VehicleType vehicleType) {
        return new DefaultParkingStrategy();
    }
}
