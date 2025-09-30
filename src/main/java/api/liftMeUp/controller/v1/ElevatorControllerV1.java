package api.liftMeUp.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;

import api.liftMeUp.controller.BaseElevatorController;
import api.liftMeUp.service.impl.ElevatorServiceImpl;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping("/call")
    @PostMapping(value = "/url", produces = "application/json")
    public ResponseEntity<String> setDirection(@RequestParam @NonNull String direction, @RequestParam @NonNull Integer requesterFloor) { // variable name should be changes
        elevatorService.setDirection(direction, requesterFloor); //  method name should be changed
        return ResponseEntity.status(HttpStatus.OK).build(); // perhaps making it not waiting? as it hangs forever with "socket hang up" error
    }
}
