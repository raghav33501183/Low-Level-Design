package services;

import models.ElevatorCar;

public class InternalButtons {
    InternalDispatcher dispatcher = new InternalDispatcher();

    int[] availableButtons = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public void pressButton(int destination, ElevatorCar elevatorCar) {
        // Check if destination is in available floors
        boolean valid = false;
        for (int btn : availableButtons) {
            if (btn == destination) {
                valid = true;
                break;
            }
        }

        if (!valid) {
            System.out.println("Invalid floor: " + destination);
            return;
        }

        dispatcher.submitInternalRequest(destination, elevatorCar);
    }
}
