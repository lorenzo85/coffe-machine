package org.exercise.coffee.machine;

import org.exercise.coffee.model.Engineer;

public interface CoffeeMachineListener {

    void onCoffeeReady(Engineer engineer);

    void onCoffeeMachineTerminated();

    void preparingCoffee(Engineer engineer);
}
