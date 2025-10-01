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

@Slf4j
@RestController
@RequestMapping("/api/v1/elevator")
public class ElevatorControllerV1 extends BaseElevatorController {

    private final ElevatorServiceImpl elevatorService;

    @Autowired
    public ElevatorControllerV1(ElevatorServiceImpl elevatorService) {
        this.elevatorService = elevatorService;
    }

//    @PreAuthorize("permitAll()")
    @RequestMapping("/call")
    @PostMapping(value = "/url", produces = "application/json") // thsi ligne should  be removed
    public ResponseEntity getElevator(@RequestParam @NonNull Direction direction, @RequestParam @NonNull Integer floor) {
        elevatorService.setDirection(direction, floor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @isFireman
    @RequestMapping("/priority")
    @PostMapping(value = "/url", produces = "application/json")
    public ResponseEntity getPriority(@RequestParam @NonNull Direction direction, @RequestParam @NonNull Integer floor) {  // using overloading? dupliacte method?
        elevatorService.setPriority(direction, floor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
