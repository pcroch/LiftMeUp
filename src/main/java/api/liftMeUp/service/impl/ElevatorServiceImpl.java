package api.liftMeUp.service.impl;

import api.liftMeUp.service.ElevatorService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class ElevatorServiceImpl {

    private int currentFloor = 0;

    int floorTravelTimeMs = 2500;
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private Direction direction = Direction.STATIONARY;
    private final TreeSet<Integer> upRequests = new TreeSet<>();
    private final TreeSet<Integer> downRequests = new TreeSet<>();

    @PostConstruct
    public void startElevator()  {
        executor.scheduleAtFixedRate(this::scan, 0, 1000, TimeUnit.MILLISECONDS);
    }

    public synchronized void setDirection(String inputDirection, int floor) {
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
            System.err.println("Elevator thread was interrupted.");
        }
    }

    private void travelTo(int destinationFloor) throws InterruptedException {
        System.out.println("Elevator at floor " + currentFloor + ", moving to " + destinationFloor);
        int floorsToTravel = Math.abs(destinationFloor - currentFloor);
        for (int i = 0; i < floorsToTravel; i++) {
            Thread.sleep(floorTravelTimeMs); // Simulate travel time
            if (currentFloor < destinationFloor) {
                currentFloor++;
            } else {
                currentFloor--;
            }
            System.out.println("...now at floor " + currentFloor);
        }
        System.out.println("Elevator arrived at floor " + currentFloor + ". Doors opening.");
    }

//    @Override
//    public void run() {
//        try {
//            while (true) {
//                scan();
//            }
//        } catch (Exception e) { //InterruptedException
//            Thread.currentThread().interrupt();
//        }
//    }

    public enum Direction {
        UP, DOWN, STATIONARY
    }

}
