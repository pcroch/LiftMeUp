package api.liftMeUp.component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@ConfigurationProperties(prefix = "appConfig")
public class ApplicationConfiguration {
    private int floorTravelTime;
}
