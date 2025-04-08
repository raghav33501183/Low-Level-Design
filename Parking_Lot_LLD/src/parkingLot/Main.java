package parkingLot;

import java.util.ArrayList;

import models.ParkingSpot;
import models.parkingSpotManager.ParkingSpotManagerFactory;
import models.vehicles.VehicleFactory;
import models.vehicles.VehicleType;
import services.EntranceGate;
import services.ExitGate;

public class Main {

	public static void main(String[] args) {
		var spots = new ArrayList<ParkingSpot>();
        for (int i = 0; i < 100; ++i) {
            if (i < 50)
                spots.add(new ParkingSpot(i, 10));
            else
                spots.add(new ParkingSpot(i, 20));
        }
        
        var entranceGate = new EntranceGate();
        var exitGate = new ExitGate();
        
        var car = VehicleFactory.createVehicle(1, VehicleType.CAR);
        var bike = VehicleFactory.createVehicle(2, VehicleType.BIKE);
        var bus = VehicleFactory.createVehicle(3, VehicleType.BUS);
        
        var manager = ParkingSpotManagerFactory.getParkingSpotManager(car.getVehicleType(), spots);
        var ticket = entranceGate.generateTicket(car, manager);
        exitGate.removeVehicle(ticket, manager);
	}

}
