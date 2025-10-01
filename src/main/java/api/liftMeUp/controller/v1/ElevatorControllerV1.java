package api.liftMeUp.controller.v1;

import api.liftMeUp.commun.annotations.isFireman;
import api.liftMeUp.commun.constants.Direction;
import org.springframework.beans.factory.annotation.Autowired;

import api.liftMeUp.controller.BaseElevatorController;
import api.liftMeUp.service.impl.ElevatorServiceImpl;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Slf4j
@RestController
@RequestMapping("/api/v1/elevator")
public class ElevatorControllerV1 implements BaseElevatorController {
    private final ElevatorServiceImpl elevatorService;

    @Autowired
    public ElevatorControllerV1(ElevatorServiceImpl elevatorService) {
        this.elevatorService = elevatorService;
    }

    @RequestMapping("/pickup")
    @PostMapping
    public ResponseEntity requestPickup(@RequestParam @NonNull Direction direction,
                                        @RequestParam @NonNull @Min(value = 0) @Max(value = 50) Integer userCurrentFloor) {

        elevatorService.requestPickup(direction, userCurrentFloor);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping("/destination")
    @PostMapping
    public ResponseEntity requestDestination(
            @RequestParam @NonNull @Min(value = 0) @Max(value = 50) Integer destinationFloor) {

        elevatorService.requestDestination(destinationFloor);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @isFireman
    @RequestMapping("/priority-pickup")
    @PostMapping
    public ResponseEntity getPriorityPickUp(@RequestParam @NonNull Direction direction,
                                            @RequestParam @NonNull @Min(value = 0) @Max(value = 50) Integer userCurrentFloor) {
//        elevatorService.setPriority(direction, firemanCurrentFloor, destinationFloor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @isFireman
    @RequestMapping("/priority-destination")
    @PostMapping
    public ResponseEntity getPriorityDestination(
            @RequestParam @NonNull @Min(value = 0) @Max(value = 50) Integer destinationFloor) {
//        elevatorService.setPriority(direction, firemanCurrentFloor, destinationFloor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
