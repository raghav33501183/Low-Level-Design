import models.Building;
import models.Direction;
import models.ElevatorCar;
import models.Floor;
import services.ElevatorCreator;
import services.ElevatorController;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Create floors
        List<Floor> floorList = new ArrayList<>();
        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);
        Floor floor3 = new Floor(3);
        Floor floor4 = new Floor(4);
        Floor floor5 = new Floor(5);
        floorList.add(floor1);
        floorList.add(floor2);
        floorList.add(floor3);
        floorList.add(floor4);
        floorList.add(floor5);

        Building building = new Building(floorList);

        // Start elevator control threads
        for (ElevatorController controller : ElevatorCreator.elevatorControllerList) {
            Thread elevatorThread = new Thread(() -> controller.controlElevator());
            elevatorThread.setDaemon(true); // so JVM can exit when main ends
            elevatorThread.start();
        }

        // Simulate external floor button presses
        System.out.println("\n--- External Requests ---");
        floor1.pressButton(Direction.UP);
        floor3.pressButton(Direction.DOWN);
        floor5.pressButton(Direction.DOWN);

        // Simulate internal button presses inside elevators
        System.out.println("\n--- Internal Requests ---");

        // Find ElevatorCar 1 and 2
        ElevatorCar elevatorCar1 = ElevatorCreator.elevatorControllerList.get(0).elevatorCar;
        ElevatorCar elevatorCar2 = ElevatorCreator.elevatorControllerList.get(1).elevatorCar;

        // Inside elevator 1, press floor 4
        elevatorCar1.pressButton(4);

        // Inside elevator 2, press floor 2 and 3
        elevatorCar2.pressButton(2);
        elevatorCar2.pressButton(3);

        // Keep main thread alive so we can see movements
        try {
            Thread.sleep(10000); // Let the simulation run for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Simulation Complete ---");
    }
}