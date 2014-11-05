package org.exercise.coffee.model;

import java.util.Random;

import static org.exercise.coffee.model.Priority.HIGH;
import static org.exercise.coffee.model.Priority.NORMAL;
import static org.exercise.coffee.model.strategies.PriorityStrategyProvider.getPriorityStrategy;

public class Engineer implements Comparable<Engineer> {

    private static final Random random = new Random();

    private final Integer id;
    private final Integer totalBusyTime;
    private final Float highPriorityProbability;

    private Priority priority;
    private Integer busyTimeLeft;

    public Engineer(Integer id, Integer totalBusyTime, Float highPriorityProbability) {
        this.id = id;
        this.priority = NORMAL;
        this.totalBusyTime = totalBusyTime;
        this.highPriorityProbability = highPriorityProbability;
    }

    public Integer getId() {
        return id;
    }

    Priority getPriority() {
        return priority;
    }

    public Integer getTotalBusyTime() {
        return totalBusyTime;
    }

    public Integer getBusyTimeLeft() {
        return busyTimeLeft;
    }

    public void setBusyTimeLeft(Integer busyTimeLeft) {
        this.busyTimeLeft = busyTimeLeft;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Float getHighPriorityProbability() {
        return highPriorityProbability;
    }

    public void nextStep() {
        getPriorityStrategy(priority).execute(this);
    }

    @Override
    public int compareTo(Engineer other) {
        return priority.getValue() - other.getPriority().getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Engineer engineer = (Engineer) o;

        if (!id.equals(engineer.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Engineer{" +
                "priority=" + priority +
                ", id='" + id + '\'' +
                '}';
    }

    public static Priority getRandomPriority(float probabilityHigh) {
        double randomNumber = random.nextDouble();
        return randomNumber < probabilityHigh ? HIGH : NORMAL;
    }
}