package org.exercise.coffee.model.strategies;

import org.exercise.coffee.model.Priority;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static org.exercise.coffee.model.Priority.HIGH;
import static org.exercise.coffee.model.Priority.NORMAL;

public class PriorityStrategyProvider {

    private static final Map<Priority, PriorityStrategy> PRIORITY_STRATEGIES =
            of(NORMAL, new NormalPriorityStrategy(), HIGH, new HighPriorityStrategy());

    public static PriorityStrategy getPriorityStrategy(Priority priority) {
        PriorityStrategy strategy = PRIORITY_STRATEGIES.get(priority);

        if(strategy == null) {
            throw new IllegalArgumentException("The provided priority does not have a matching strategy");
        }

        return strategy;
    }
}
