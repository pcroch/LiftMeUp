package api.liftMeUp.controller;

import api.liftMeUp.service.impl.ElevatorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/v1/elevator")
public class ElevatorController {

    private final ElevatorServiceImpl elevatorService;

    public ElevatorController(ElevatorServiceImpl elevatorService) {
        this.elevatorService = elevatorService;
    }

    @RequestMapping("/call")
    @GetMapping(value = "/url", produces = "application/json")
    public ResponseEntity<String> getLift() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
