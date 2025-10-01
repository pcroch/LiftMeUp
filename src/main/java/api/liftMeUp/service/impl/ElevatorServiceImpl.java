package api.liftMeUp.service.impl;

import api.liftMeUp.commun.constants.Direction;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class ElevatorServiceImpl {

    private int currentFloor;
    int floorTravelTimeMs = 500; // should be configurable
    private final ScheduledExecutorService elevatorThread = Executors.newSingleThreadScheduledExecutor();
    private Direction direction = Direction.STATIONARY;
    private final TreeSet<Integer> upRequests = new TreeSet<>();
    private final TreeSet<Integer> downRequests = new TreeSet<>();

    @PostConstruct
    public void startElevator() {
        elevatorThread.scheduleAtFixedRate(this::scan, 0, 1000, TimeUnit.MILLISECONDS); //todo check request is the traveltime?
    } // ScheduledExecutorService is better?

    public synchronized void setDirection(String inputDirection, int floor) {
        //todo inputDirection should be an enum
        if (Direction.UP.toString().equals(inputDirection)) {
            direction = Direction.UP;
            upRequests.add(floor);
        } else if (Direction.DOWN.toString().equals(inputDirection)) {
            downRequests.add(floor);
            direction = Direction.DOWN;
        }
        notify();
    }

    private synchronized void scan() {
        try {
            if (upRequests.isEmpty() && downRequests.isEmpty()) {
                direction = Direction.STATIONARY;
                wait();
            }
            if (direction == Direction.UP) {
                while (!upRequests.isEmpty()) {
                    int nextFloor = upRequests.pollFirst();
                    travelTo(nextFloor);
                }
                direction = Direction.DOWN;
            }

            if (direction == Direction.DOWN) {
                while (!upRequests.isEmpty()) {
                    int nextFloor = downRequests.pollFirst();
                    travelTo(nextFloor);
                }
                direction = Direction.UP;
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Elevator thread was interrupted."); //todo changing the logging
        }
    }

    private void travelTo(int destinationFloor) throws InterruptedException {
        System.out.println("Elevator at floor " + currentFloor + ", moving to " + destinationFloor); //todo changing the logging
        int floorsToTravel = Math.abs(destinationFloor - currentFloor);
        for (int i = 0; i < floorsToTravel; i++) {
            Thread.sleep(floorTravelTimeMs); // need top be set up externally or remvoed as startElevator is already doing the job?
            if (currentFloor < destinationFloor) {
                currentFloor++;
            } else {
                currentFloor--;
            }
            System.out.println("...now at floor " + currentFloor); //todo changing the logging
        }
        System.out.println("Elevator arrived at floor " + currentFloor + ". Doors opening."); //todo changing the logging
    }
}
