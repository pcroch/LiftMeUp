package api.springblueprint.service;

import org.springframework.stereotype.Service;

@Service
public class PingService {

    public String getPingBody(){
       return this.getClass().getSimpleName() + " Ping";
    }

}
