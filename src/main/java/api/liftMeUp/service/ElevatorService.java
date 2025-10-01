package api.liftMeUp.service;

import api.liftMeUp.commun.constants.Direction;

import java.util.TreeSet;

public interface ElevatorService {


    void startElevator();

    void setDirection(Direction inputDirection, int floor);

    void setPriority(Direction inputDirection, int floor);

}
