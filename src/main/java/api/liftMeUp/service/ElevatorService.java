package api.liftMeUp.service;

import api.liftMeUp.commun.constants.Direction;

public interface ElevatorService {


    void startElevator();

    void setDirection(Direction inputDirection, int userCurrentFloor, int destinationFloor);

    void setPriority(Direction inputDirection, int firemanCurrentFloor, int destinationFloor);

}
