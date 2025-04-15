package services;

import models.Direction;
import models.ElevatorCar;
import models.ElevatorState;

import static services.ElevatorCreator.elevatorControllerList;

public class ExternalDispatcher {
    public void submitExternalRequest(int floor, Direction direction) {
        ElevatorController bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (ElevatorController controller : elevatorControllerList) {
            ElevatorCar car = controller.elevatorCar;
            int distance = Math.abs(car.currentFloor - floor);

            // Check if elevator is idle or moving in the same direction and can pick up on the way
            if (car.elevatorState == ElevatorState.IDLE || (car.elevatorDirection == direction && ((direction == Direction.UP && car.currentFloor <= floor) || (direction == Direction.DOWN && car.currentFloor >= floor)))) {

                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = controller;
                }
            }
        }

        if (bestElevator != null) {
            bestElevator.submitExternalRequest(floor, direction);
        } else {
            // No suitable elevator, fallback strategy (like assigning to the least busy one)
            elevatorControllerList.get(0).submitExternalRequest(floor, direction);
        }
    }

}
