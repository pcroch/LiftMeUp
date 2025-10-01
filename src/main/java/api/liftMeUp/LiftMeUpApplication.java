package api.liftMeUp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LiftMeUpApplication  {

    public static void main(String[] args) {
        SpringApplication.run(LiftMeUpApplication.class, args);
    }

}
