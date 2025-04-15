package services;

import models.ElevatorCar;

import java.util.List;

public class InternalDispatcher {
    List<ElevatorController> elevatorControllerList = ElevatorCreator.elevatorControllerList;

    public void submitInternalRequest(int floor, ElevatorCar elevatorCar) {
        // Find controller for this elevator
        for (ElevatorController controller : elevatorControllerList) {
            if (controller.elevatorCar.equals(elevatorCar)) {
                controller.submitInternalRequest(floor);
            }
        }
    }
}
