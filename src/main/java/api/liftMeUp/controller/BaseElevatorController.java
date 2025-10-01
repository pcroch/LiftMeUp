package api.liftMeUp.controller;

import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public abstract class BaseElevatorController {
    public abstract ResponseEntity<String> setDirection(@RequestParam @NonNull String direction, @RequestParam @NonNull Integer requesterFloor); //todo return type should be a map with floor and direction

}
