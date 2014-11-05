package org.exercise.coffee.model.strategies;

import org.exercise.coffee.model.Engineer;

import static org.exercise.coffee.model.Priority.NORMAL;

public class HighPriorityStrategy implements PriorityStrategy {

    @Override
    public void execute(Engineer engineer) {
        Integer busyTimeLeft = engineer.getBusyTimeLeft();

        if(busyTimeLeft == 0) {
            engineer.setPriority(NORMAL);

        } else {
            engineer.setBusyTimeLeft(busyTimeLeft - 1);
        }
    }
}
