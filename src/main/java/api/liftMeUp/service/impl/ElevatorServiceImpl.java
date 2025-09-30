package api.liftMeUp.service.impl;

import api.liftMeUp.service.ElevatorService;
import api.liftMeUp.utils.ElevatorAlgorithm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

@Service
public class ElevatorServiceImpl implements ElevatorService {


    int currentPosition = 0;
    String currentDirection = "stationary";

    ArrayList<Integer> requestArray = new ArrayList<>(Arrays.asList(1, 25, 34, 49));

    public void setDirection(String direction, int floor) {
        if ("stationary".equals(currentDirection)) { // bcse we dont want to change the direction as soon as it as started to move
            currentDirection = direction;
        }
        // logic to set current floor
        requestArray.add(floor);
        // update the elevator
        ElevatorAlgorithm.SCAN(requestArray, currentPosition, currentDirection);
    }
    // can be called only if lift is static after it is too late
    // needed for the lift to know where to go to it will assign a floor value to the lift array

    public void setFloor(int floor) {

        // remove the current floor from the list

        // add the new floor to the list
        requestArray.add(floor);

        // update the elevator
        ElevatorAlgorithm.SCAN(requestArray, currentPosition, currentDirection);
    }

}
