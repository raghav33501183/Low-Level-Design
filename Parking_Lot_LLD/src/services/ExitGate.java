package services;

import java.util.ArrayList;

import models.Ticket;
import models.parkingSpotManager.ParkingSpotManager;
import models.parkingSpotManager.ParkingSpotManagerFactory;

public class ExitGate {
	
    public void removeVehicle(Ticket ticket, ParkingSpotManager manager) {
        manager.removeVehicle(ticket.getVehicle());
    }
}
