package api.liftMeUp.service.impl;

import api.liftMeUp.service.ElevatorService;
import org.springframework.stereotype.Service;

@Service
public class ElevatorServiceImpl implements ElevatorService {

    public String getPingBody(){
       return this.getClass().getSimpleName() + " Ping";
    }

}
