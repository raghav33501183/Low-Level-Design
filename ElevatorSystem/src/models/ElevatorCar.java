package models;

import services.InternalButtons;

public class ElevatorCar {
    public int id;
    public ElevatorDisplay display;
    public InternalButtons internalButtons;
    public ElevatorState elevatorState;
    public int currentFloor;
    public Direction elevatorDirection;
    public ElevatorDoor elevatorDoor;

    public ElevatorCar(int id) {
        this.id = id;
        display = new ElevatorDisplay();
        internalButtons = new InternalButtons();
        elevatorState = ElevatorState.IDLE;
        currentFloor = 0;
        elevatorDirection = Direction.UP;
        elevatorDoor = new ElevatorDoor();
    }

    private void showDisplay() {
        display.showDisplay();
    }

    private void setDisplay() {
        display.setDisplay(currentFloor, elevatorDirection);
    }

    public void openDoor() {
        elevatorDoor.openDoor();
    }

    public void closeDoor() {
        elevatorDoor.closeDoor();
    }

    public void pressButton(int destination) {
        internalButtons.pressButton(destination, this);
    }

    public boolean moveElevator(Direction dir, int destinationFloor) {
        int startFloor = currentFloor;
        if (dir == Direction.UP) {
            for (int i = startFloor; i <= destinationFloor; i++) {

                this.currentFloor = i;
                setDisplay();
                showDisplay();
                if (i == destinationFloor) {
                    return true;
                }
            }
        }

        if (dir == Direction.DOWN) {
            for (int i = startFloor; i >= destinationFloor; i--) {

                this.currentFloor = i;
                setDisplay();
                showDisplay();
                if (i == destinationFloor) {
                    return true;
                }
            }
        }
        return false;
    }

}
