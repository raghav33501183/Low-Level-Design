package models.vehicles;

public class Bike extends Vehicle {

	public Bike(int vehicleNo) {
		super(vehicleNo, VehicleType.BIKE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calculateParkingFee(long parkingDuration) {
		// TODO Auto-generated method stub
		return 0;
	}

}