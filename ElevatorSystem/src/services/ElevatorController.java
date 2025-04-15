package services;

import models.Direction;
import models.ElevatorCar;
import models.ElevatorState;

import java.util.*;

public class ElevatorController {

    PriorityQueue<Integer> upMinPQ;
    PriorityQueue<Integer> downMaxPQ;
    Queue<Integer> pendingJobs;
    public ElevatorCar elevatorCar;

    public ElevatorController(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
        upMinPQ = new PriorityQueue<>();
        downMaxPQ = new PriorityQueue<>((a, b) -> b - a);
        pendingJobs = new LinkedList<>();
    }

    public void submitExternalRequest(int floor, Direction direction) {
        Direction currentDir = elevatorCar.elevatorDirection;

        if (currentDir == Direction.UP) {
            if (floor >= elevatorCar.currentFloor && direction == Direction.UP) {
                upMinPQ.offer(floor);
            } else {
                pendingJobs.offer(floor);
            }
        } else if (currentDir == Direction.DOWN) {
            if (floor <= elevatorCar.currentFloor && direction == Direction.DOWN) {
                downMaxPQ.offer(floor);
            } else {
                pendingJobs.offer(floor);
            }
        } else {
            // Elevator is idle — decide direction based on the external request
            elevatorCar.elevatorDirection = direction;
            if (direction == Direction.UP) {
                upMinPQ.offer(floor);
            } else {
                downMaxPQ.offer(floor);
            }
            elevatorCar.elevatorState = ElevatorState.MOVING;
        }
    }

    public void submitInternalRequest(int floor) {
        if (floor == elevatorCar.currentFloor) {
            System.out.println("Already at floor: " + floor);
            return;
        }

        Direction currentDir = elevatorCar.elevatorDirection;

        if (currentDir == Direction.UP) {
            if (floor > elevatorCar.currentFloor) {
                upMinPQ.offer(floor);
            } else {
                pendingJobs.offer(floor);
            }
        } else if (currentDir == Direction.DOWN) {
            if (floor < elevatorCar.currentFloor) {
                downMaxPQ.offer(floor);
            } else {
                pendingJobs.offer(floor);
            }
        } else {
            // Elevator idle — decide direction
            if (floor > elevatorCar.currentFloor) {
                elevatorCar.elevatorDirection = Direction.UP;
                upMinPQ.offer(floor);
            } else {
                elevatorCar.elevatorDirection = Direction.DOWN;
                downMaxPQ.offer(floor);
            }
        }
    }

    public void controlElevator() {
        while (true) {
            if (elevatorCar.elevatorDirection == Direction.UP) {
                if (!upMinPQ.isEmpty()) {
                    int nextFloor = upMinPQ.poll();
                    moveTo(nextFloor);
                } else if (!downMaxPQ.isEmpty()) {
                    elevatorCar.elevatorDirection = Direction.DOWN;
                } else {
                    processPendingJobs();
                }
            } else if (elevatorCar.elevatorDirection == Direction.DOWN) {
                if (!downMaxPQ.isEmpty()) {
                    int nextFloor = downMaxPQ.poll();
                    moveTo(nextFloor);
                } else if (!upMinPQ.isEmpty()) {
                    elevatorCar.elevatorDirection = Direction.UP;
                } else {
                    processPendingJobs();
                }
            }
        }
    }

    private void moveTo(int floor) {
        System.out.println("Elevator " + elevatorCar.id + " moving from floor " + elevatorCar.currentFloor + " to floor " + floor);
        elevatorCar.moveElevator(elevatorCar.elevatorDirection, floor);
        elevatorCar.elevatorDoor.openDoor();
        elevatorCar.elevatorDoor.closeDoor();
    }

    private void processPendingJobs() {
        if (pendingJobs.isEmpty()) {
            System.out.println("Elevator " + elevatorCar.id + " no pending jobs, staying idle at floor " + elevatorCar.currentFloor);
            elevatorCar.elevatorState = ElevatorState.IDLE;
            return;
        }

        Queue<Integer> remainingJobs = new LinkedList<>();
        while (!pendingJobs.isEmpty()) {
            int floor = pendingJobs.poll();
            if (floor > elevatorCar.currentFloor) {
                upMinPQ.offer(floor);
                elevatorCar.elevatorDirection = Direction.UP;
            } else if (floor < elevatorCar.currentFloor) {
                downMaxPQ.offer(floor);
                elevatorCar.elevatorDirection = Direction.DOWN;
            } else {
                System.out.println("Already at requested floor: " + floor);
            }
        }

        elevatorCar.elevatorState = ElevatorState.MOVING;
    }
}
