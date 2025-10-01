package api.liftMeUp.service.impl;

import api.liftMeUp.commun.constants.Direction;
import api.liftMeUp.component.ApplicationConfiguration;
import api.liftMeUp.model.Elevator;
import api.liftMeUp.service.ElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class ElevatorServiceImpl { //implements ElevatorService

    private final Elevator elevator = new Elevator.Builder("elevator").capacity(0).currentFloor(10).build();

    private Direction requestDirection;
    private final ScheduledExecutorService elevatorThread = Executors.newSingleThreadScheduledExecutor();
    private final TreeSet<Integer> upRequests = new TreeSet<>();
    private final TreeSet<Integer> downRequests = new TreeSet<>();

    private final TreeSet<Integer> upPriorityRequests = new TreeSet<>();
    private final TreeSet<Integer> downPriorityRequests = new TreeSet<>();

    private final ApplicationConfiguration configuration;

    @Autowired
    public ElevatorServiceImpl(ApplicationConfiguration configuration) {
        this.configuration = configuration;
    }

    @PostConstruct
    public void startElevator() {
        elevatorThread.scheduleAtFixedRate(this::scan, 0, configuration.getThreadScheduler(), TimeUnit.MILLISECONDS);
    }

    public synchronized void requestPickup(Direction direction, int userCurrentFloor) {
        requestDirection = direction;

        if (userCurrentFloor > elevator.getCurrentFloor()) {
            elevator.setDirection(Direction.UP);
            upRequests.add(userCurrentFloor);
        } else if (userCurrentFloor < elevator.getCurrentFloor()) {
            elevator.setDirection(Direction.DOWN);
            downRequests.add(userCurrentFloor);

        }
        notify();
    }

    public synchronized void requestDestination(int destinationFloor) {
        if (Direction.UP.equals(requestDirection)) {
            elevator.setDirection(Direction.UP);
            upRequests.add(destinationFloor);
        } else if (Direction.DOWN.equals(requestDirection)) {
            elevator.setDirection(Direction.DOWN);
            downRequests.add(destinationFloor);
        }
        notify();
    }

    public synchronized void requestPriorityPickUp(Direction direction, int firemanCurrentFloor) {
        requestDirection = direction;

        if (firemanCurrentFloor > elevator.getCurrentFloor()) {
            elevator.setDirection(Direction.UP);
            upPriorityRequests.add(firemanCurrentFloor);
        } else if (firemanCurrentFloor < elevator.getCurrentFloor()) {
            elevator.setDirection(Direction.DOWN);
            downPriorityRequests.add(firemanCurrentFloor);

        }
        notify();
    }

    public synchronized void requestPriorityDestination(int destinationFloor) {
        if (Direction.UP.equals(requestDirection)) {
            elevator.setDirection(Direction.UP);
            upPriorityRequests.add(destinationFloor);
        } else if (Direction.DOWN.equals(requestDirection)) {
            elevator.setDirection(Direction.DOWN);
            downPriorityRequests.add(destinationFloor);
        }
        notify();
    }

    private synchronized void scan() {
        try {
            if (!upPriorityRequests.isEmpty() || !downPriorityRequests.isEmpty()) {
                if (Direction.UP.equals(elevator.getDirection())) {
                    while (!upPriorityRequests.isEmpty()) {
                        int nextFloor = upPriorityRequests.pollFirst();
                        travelTo(nextFloor);
                    }
                    elevator.setDirection(Direction.DOWN);
                }

                if (Direction.DOWN.equals(elevator.getDirection())) {
                    while (!downPriorityRequests.isEmpty()) {
                        int nextFloor = downPriorityRequests.pollFirst();
                        travelTo(nextFloor);
                    }
                    elevator.setDirection(Direction.UP);
                }
            }

            if (upRequests.isEmpty() && downRequests.isEmpty()) {
                elevator.setDirection(Direction.STATIONARY);
                wait();
            }


            if (Direction.UP.equals(elevator.getDirection())) {
                while (!upRequests.isEmpty()) {
                    // using generic to make it array agnostic for fireman queue of user queue

                    int nextFloor = upRequests.pollFirst();
                    travelTo(nextFloor);
                }
                elevator.setDirection(Direction.DOWN);
            }

            if (Direction.DOWN.equals(elevator.getDirection())) {
                while (!downRequests.isEmpty()) {

                    int nextFloor = downRequests.pollFirst();
                    travelTo(nextFloor);
                }
                elevator.setDirection(Direction.UP);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Elevator thread was interrupted."); //todo changing the logging
        }
    }

    private void travelTo(int destinationFloor) throws InterruptedException {
        System.out.println("Elevator at floor " + elevator.getCurrentFloor() + ", moving to " + destinationFloor); //todo changing the logging
        int floorsToTravel = Math.abs(destinationFloor - elevator.getCurrentFloor());
        for (int i = 0; i < floorsToTravel; i++) {
            Thread.sleep(configuration.getFloorTravelTime());

            if (elevator.getCurrentFloor() < destinationFloor) {
                elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
            } else {
                elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                ;
            }
            System.out.println("...now at floor " + elevator.getCurrentFloor() + " and direction is " + elevator.getDirection().name()); //todo changing the logging
        }
        elevator.setCurrentFloor(destinationFloor);

        System.out.println("Elevator arrived at floor " + elevator.getCurrentFloor() + ". Doors opening."); //todo changing the logging
    }

}
