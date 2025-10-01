### Spring Boot

## JAVA and Spring versions
    java 11 and spring 2.7.9

## Build the app

    mvn clean install

## Run the app

    mvn spring-boot:run

## Technical specification 

 1. direction is a enum and I can only be DOWN, UP or STATIONARY 
 2. floor is a number between 0 and 50

# Command for a non-authenticated user

    curl -X POST http://localhost:8080/api/v1/elevator/call?direction=UP&requesterFloor=50

# Command for the fireman

user: fireman
password: fireman

     curl -X POST -u fireman:fireman http://localhost:8080/api/v1/elevator/priority?direction=UP&floor=1

Note that the V1 version is for when only one lift is available

# Optional Settings

You can choose the time to travel of the lift via the config file.


 ### Have fun and enjoy!

-------------------------------------------

### The task

In order to validate your Java skills, we offer you a coding exercise (Practical Home Work).

We invite you to come to us with your laptop, your dev environment and the code. We will ask you to run the program with different input to see its execution and result. In addition, we will explore the code with you to ask you questions about it.
You need to create and code in Java 11+, SpringBoot 2.5+ and maven the algorithm for how an elevator works.

To achieve this, a lift knows

* the floor(s) from which it is called (buttons on the wall outside the elevator)
* its current position
* its current direction (up, down or stationary)
* the floor(s) to which they are asked to go (via the buttons inside the elevator) -> a person who goes up in the elevator will give a destination floor
* in front of each elevator door there is a pair of “up/down” buttons except on the first and last floor
* no constraints on the maximum number of people in the elevator
* a firefighter can request priority access (to be managed with Spring Security)

The building in which the elevator circulates is 50 floors high, the elevator can stop at all floors. The elevator takes X seconds to travel a floor (X configurable or hardcoded), the idea being to be able to send it requests while it is already moving and see its behavior.

You must create a program which takes the above points as parameters and which indicates in output the optimal routing of the elevator (the order of the floors at which it stops and whether a person goes up or down on them).

* Bonus 1: There are three elevators, so the floor(s) they are called from are therefore shared
* Bonus 2: maximum 2 people per lift
* Bonus 3: have a graphical return that represents the whole (ascii art is accepted)
* Bonus 4: provide the UML schema
    



