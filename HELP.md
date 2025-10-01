### Spring Boot

Web server failed to start. Port 8080 was already in use.

Action:

    sudo lsof -i tcp:8080

then

    kill -15 PID 

where PID is the PID of port 8080


### Nice to have

1. Unit testing
2. Having  polling to have async requests
3. @PreAuthorize("permitAll()") on call method
