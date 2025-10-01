### Spring Boot

## JAVA and Spring versions
    java 11 and spring 2.7.9

## Build the app

    mvn clean install

## Run the app

    mvn spring-boot:run

# Command

    curl -X POST http://localhost:8080/api/v1/elevator/call?direction=UP&requesterFloor=50

Where direction can only be DOWN or UP and  the floor is a number between 0 and 50

Note that the V1 version is for when only one lift is available

# Optional Settings

You can choose the time to travel of the lift via the config file.


 ### Have fun and enjoy!

    



