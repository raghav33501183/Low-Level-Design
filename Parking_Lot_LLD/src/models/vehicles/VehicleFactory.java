package models.vehicles;

public class VehicleFactory {
	public static Vehicle createVehicle(int vehicleNo, VehicleType type) {
        switch (type) {
            case CAR:
                return new Car(vehicleNo);
            case BIKE:
                return new Bike(vehicleNo);
            case BUS:
                return new Bus(vehicleNo);
            default:
                throw new IllegalArgumentException("Invalid vehicle type");
        }
    }
}