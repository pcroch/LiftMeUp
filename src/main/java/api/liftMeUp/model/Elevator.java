package api.liftMeUp.model;


import api.liftMeUp.commun.constants.Direction;
import lombok.Getter;
import lombok.Setter;

import java.util.TreeSet;

@Getter
@Setter
public final class Elevator {
    private String id;
    private int currentFloor;
    private Direction direction;
    private int capacity;
    private int maxCapacity;
    private final TreeSet<Integer> upRequests = new TreeSet<>();
    private final TreeSet<Integer> downRequests = new TreeSet<>();

    private Elevator(Builder builder) {
        this.id = builder.id;
        this.currentFloor = builder.currentFloor;
        this.direction = builder.direction;
        this.capacity = builder.capacity;
        this.maxCapacity = builder.maxCapacity;

    }

    public static class Builder {
        private String id;
        private int currentFloor = 0;
        private Direction direction = Direction.STATIONARY;
        private int capacity = 0;
        private int maxCapacity = 2;

        public Builder(String id) {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("Elevator ID must not be null or empty.");
            }
            this.id = id;
        }

        public Builder currentFloor(int currentFloor) {
            this.currentFloor = currentFloor;
            return this;
        }

        public Builder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Elevator build() {
            return new Elevator(this);
        }
    }
    //todo builder for elevators?
}