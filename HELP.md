### Spring Boot

Web server failed to start. Port 8080 was already in use.

Action:

    sudo lsof -i tcp:8080

then

    kill -15 PID 

where PID is the PID of port 8080