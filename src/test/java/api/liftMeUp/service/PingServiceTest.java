package api.liftMeUp.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
//@ActiveProfiles
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Unit Testing on Ping Service")
class PingServiceTest {

    @InjectMocks
    private PingService pingService;

    @DisplayName("is Ping a string?")
    @Test
    @Order(0)
    void test_ping_service() {
        assert (pingService.getPingBody()).equals("PingService Ping");
    }


}
