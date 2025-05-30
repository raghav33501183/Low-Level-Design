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
            if (direction == Direction.DOWN) {
                downMaxPQ.offer(floor);
            } else if (floor >= elevatorCar.currentFloor) {
                upMinPQ.offer(floor);
            } else {
                pendingJobs.offer(floor);
            }
        } else if (currentDir == Direction.DOWN) {
            if (direction == Direction.UP) {
                upMinPQ.offer(floor);
            } else if (floor <= elevatorCar.currentFloor) {
                downMaxPQ.offer(floor);
            } else {
                pendingJobs.offer(floor);
            }
        } else {
            // Elevator is idle
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
                downMaxPQ.offer(floor); // Request for a lower floor, add to downMaxPQ (already passed floor)
            }
        } else if (currentDir == Direction.DOWN) {
            if (floor < elevatorCar.currentFloor) {
                downMaxPQ.offer(floor);
            } else {
                upMinPQ.offer(floor); // Request for a higher floor, add to upMinPQ (already passed floor)
            }
        } else {
            if (floor > elevatorCar.currentFloor) {
                elevatorCar.elevatorDirection = Direction.UP;
                upMinPQ.offer(floor);
            } else {
                elevatorCar.elevatorDirection = Direction.DOWN;
                downMaxPQ.offer(floor);
            }
            elevatorCar.elevatorState = ElevatorState.MOVING;
        }
    }

    public void controlElevator() {
        while (true) {
            if (elevatorCar.elevatorDirection == Direction.UP) {
                if (!upMinPQ.isEmpty()) {
                    int nextFloor = upMinPQ.poll();
                    moveTo(nextFloor);
                } else if (!downMaxPQ.isEmpty()) {
                    // Add pending jobs for the unfulfilled request for UP direction to the UP queue
                    addPendingJobsToQueue(Direction.UP);
                    elevatorCar.elevatorDirection = Direction.DOWN;
                }
            } else if (elevatorCar.elevatorDirection == Direction.DOWN) {
                if (!downMaxPQ.isEmpty()) {
                    int nextFloor = downMaxPQ.poll();
                    moveTo(nextFloor);
                } else if (!upMinPQ.isEmpty()) {
                    // Add pending jobs for the unfulfilled request for DOWN direction to the DOWN queue
                    addPendingJobsToQueue(Direction.DOWN);
                    elevatorCar.elevatorDirection = Direction.UP;
                }
            }

            try {
                Thread.sleep(100);  // Adjust based on your use case
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Handle thread interruption
                break;
            }

            if (upMinPQ.isEmpty() && downMaxPQ.isEmpty()) {
                System.out.println("No more requests. Elevator idle.");
                elevatorCar.elevatorState = ElevatorState.IDLE;
                break;  // Exit the loop if no more requests
            }
        }
    }

    private void addPendingJobsToQueue(Direction direction) {
        // Here, you would need to transfer the pending jobs into the correct priority queue
        if (direction == Direction.UP) {
            while (!pendingJobs.isEmpty()) {
                upMinPQ.add(pendingJobs.poll()); // Add pending jobs to the UP priority queue
            }
        } else {
            while (!pendingJobs.isEmpty()) {
                downMaxPQ.add(pendingJobs.poll()); // Add pending jobs to the DOWN priority queue
            }
        }
    }

    private void moveTo(int floor) {
        System.out.println("Elevator " + elevatorCar.id + " moving from floor " + elevatorCar.currentFloor + " to floor " + floor);
        elevatorCar.moveElevator(elevatorCar.elevatorDirection, floor);
        elevatorCar.openDoor();
        elevatorCar.closeDoor();
    }
}
