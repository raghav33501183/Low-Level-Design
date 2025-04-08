package models.parkingSpotManager;

import java.util.List;

import models.ParkingSpot;
import models.vehicles.Vehicle;
import models.vehicles.VehicleType;

public abstract class ParkingSpotManager {
	protected List<ParkingSpot> spots;

	public ParkingSpotManager(List<ParkingSpot> spots) {
        this.spots = spots;
    }
	
	public abstract ParkingSpot findParkingSpace(List<ParkingSpot> spots, VehicleType vehicleType);

    public ParkingSpot parkVehicle(Vehicle v) {
        var spot = findParkingSpace(spots, v.getVehicleType());
        spot.parkVehicle(v);
        return spot;
    }

    public void removeVehicle(Vehicle v) {
        for (var spot : spots) {
            if (spot.getVehicle() != null && spot.getVehicle().equals(v)) {
                spot.removeVehicle();
                break;
            }
        }
    }
}
