package api.liftMeUp.controller.v1;

import api.liftMeUp.controller.BaseRestController;
import api.liftMeUp.service.impl.ElevatorServiceImpl;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/v1/elevator")
public class ElevatorControllerV1 extends BaseRestController {

    private final ElevatorServiceImpl elevatorService;

    public ElevatorControllerV1(ElevatorServiceImpl elevatorService) {
        this.elevatorService = elevatorService;
    }

    @RequestMapping("/call")
    @PostMapping(value = "/url", produces = "application/json")
    public ResponseEntity<String> setDirection(@RequestParam @NonNull String direction, @RequestParam @NonNull Integer requesterFloor) {
        elevatorService.setDirection(direction, requesterFloor);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @RequestMapping("/setFloor")
    @PostMapping(value = "/url", produces = "application/json")
    public ResponseEntity<String> setFloor(@RequestParam @NonNull Integer floor) {
        elevatorService.setFloor(floor);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
