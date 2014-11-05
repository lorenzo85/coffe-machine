package org.exercise.coffee.model.strategies;

import org.exercise.coffee.model.Engineer;
import org.exercise.coffee.model.Priority;

import static org.exercise.coffee.model.Engineer.getRandomPriority;
import static org.exercise.coffee.model.Priority.HIGH;
import static org.exercise.coffee.model.Priority.NORMAL;

public class NormalPriorityStrategy implements PriorityStrategy {

    @Override
    public void execute(Engineer engineer) {
        Integer totalBusyTime = engineer.getTotalBusyTime();

        if(!(totalBusyTime > 0)) {
            engineer.setPriority(NORMAL);
            return;
        }

        Priority randomPriority = getRandomPriority(engineer.getHighPriorityProbability());
        engineer.setPriority(randomPriority);

        if(randomPriority == HIGH) {
            engineer.setBusyTimeLeft(totalBusyTime - 1);
        }
    }
}
