package api.liftMeUp.controller;

import api.liftMeUp.commun.constants.Direction;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
public abstract class BaseElevatorController {
    public abstract ResponseEntity getElevator(@RequestParam @NonNull Direction direction, @RequestParam @NonNull @Min(value = 0) @Max(value = 50) Integer floor);

    public abstract ResponseEntity getPriority(@RequestParam @NonNull Direction direction, @RequestParam @NonNull @Min(value = 0) @Max(value = 50) Integer floor);
}
