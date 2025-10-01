package api.liftMeUp.service;

import api.liftMeUp.commun.constants.Direction;

public interface ElevatorService {

    void startElevator();

    void requestPickup(Direction direction, int userCurrentFloor);

    void requestDestination(int destinationFloor);

    void requestPriorityPickUp(Direction direction, int firemanCurrentFloor);

    void requestPriorityDestination(int destinationFloor);
}
