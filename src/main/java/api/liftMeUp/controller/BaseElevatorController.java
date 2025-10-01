package api.liftMeUp.controller;

import api.liftMeUp.commun.constants.Direction;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public abstract class BaseElevatorController {
    public abstract ResponseEntity<String> getElevator(@RequestParam @NonNull Direction direction, @RequestParam @NonNull Integer floor); //todo return type should be a map with floor and direction

    public abstract ResponseEntity<String> getPriority(@RequestParam @NonNull Direction direction, @RequestParam @NonNull Integer floor);
}
