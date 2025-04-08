package models.vehicles;

public class Car extends Vehicle {

	public Car(int vehicleNo) {
		super(vehicleNo, VehicleType.CAR);
	}

	@Override
	public double calculateParkingFee(long parkingDuration) {
		// TODO Auto-generated method stub
		return 0;
	}

}