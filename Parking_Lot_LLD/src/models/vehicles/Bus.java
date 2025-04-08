package models.vehicles;

public class Bus extends Vehicle {

	public Bus(int vehicleNo) {
		super(vehicleNo, VehicleType.BUS);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calculateParkingFee(long parkingDuration) {
		// TODO Auto-generated method stub
		return 0;
	}

}