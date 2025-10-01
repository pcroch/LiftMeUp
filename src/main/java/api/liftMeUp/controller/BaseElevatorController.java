package api.liftMeUp.controller;

import api.liftMeUp.commun.annotations.isFireman;
import api.liftMeUp.commun.constants.Direction;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public interface  BaseElevatorController {
    public abstract ResponseEntity requestPickup(@RequestParam @NonNull Direction direction,
                                                 @RequestParam @NonNull @Min(value = 0) @Max(value = 50) Integer userCurrentFloor);

    public abstract ResponseEntity requestDestination(
            @RequestParam @NonNull @Min(value = 0) @Max(value = 50) Integer destinationFloor);

    @isFireman
    public abstract ResponseEntity getPriorityPickUp(@RequestParam @NonNull Direction direction,
                                                     @RequestParam @NonNull @Min(value = 0) @Max(value = 50) Integer userCurrentFloor);

    @isFireman
    public abstract ResponseEntity getPriorityDestination(
            @RequestParam @NonNull @Min(value = 0) @Max(value = 50) Integer destinationFloor);


}
