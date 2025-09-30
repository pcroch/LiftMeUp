package api.liftMeUp.controller;

import api.liftMeUp.service.PingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/pingController")
public class PingController {

    private final PingService pingService;

    public PingController(PingService pingService) {
        this.pingService = pingService;
    }

    @RequestMapping("/ping")
    @GetMapping(value = "/url", produces = "application/json")
    public ResponseEntity<String> getPing() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", this.getClass().getSimpleName() + " endpoints has been pinged");
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .headers(headers)
                .body(pingService.getPingBody());
    }
}
