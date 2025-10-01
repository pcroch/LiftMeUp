package api.liftMeUp.controller.v2;

import api.liftMeUp.commun.annotations.isFireman;
import api.liftMeUp.commun.constants.Direction;
import api.liftMeUp.controller.BaseElevatorController;
import api.liftMeUp.service.impl.ElevatorServiceImpl;
import api.liftMeUp.service.impl.MultipleElevatorServiceImpl;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Slf4j
@RestController
@RequestMapping("/api/v2/elevator")
public class ElevatorControllerV2 extends BaseElevatorController {

    private final MultipleElevatorServiceImpl elevatorService;

    public ElevatorControllerV2(MultipleElevatorServiceImpl elevatorService) {
        this.elevatorService = elevatorService;
    }


    @RequestMapping("/call")
    @PostMapping
    public ResponseEntity getElevator(@RequestParam @NonNull Direction direction, @RequestParam @NonNull  @Min(value=0) @Max(value=50) Integer floor) {
        elevatorService.setDirection(direction, floor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @isFireman
    @RequestMapping("/priority")
    @PostMapping
    public ResponseEntity getPriority(@RequestParam @NonNull Direction direction, @RequestParam @NonNull  @Min(value=0) @Max(value=50) Integer floor) {
        elevatorService.setPriority(direction, floor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
