package org.exercise.coffee.bootstrap;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.exercise.coffee.machine.CoffeeMachine;
import org.exercise.coffee.machine.CoffeeMachineImpl;
import org.exercise.coffee.model.Engineer;
import org.exercise.coffee.queue.SimulationQueue;
import org.exercise.coffee.queue.SimulationQueueImpl;
import org.exercise.coffee.simulation.SimulationLoop;
import org.exercise.coffee.simulation.SimulationLoopImpl;
import org.exercise.coffee.ui.SimulationUi;
import org.exercise.coffee.ui.SimulationUiImpl;

import java.util.*;
import java.util.concurrent.ExecutorService;

import static java.lang.System.nanoTime;
import static java.util.concurrent.Executors.newFixedThreadPool;

class SimulationModule extends AbstractModule {

    private static final int N_THREADS = 2;

    @Override
    protected void configure() {
        bind(SimulationUi.class).to(SimulationUiImpl.class);
        bind(CoffeeMachine.class).to(CoffeeMachineImpl.class);
        bind(SimulationLoop.class).to(SimulationLoopImpl.class);
    }

    @Provides
    @Singleton
    SimulationQueue<Engineer> provideSimulationQueue() {
        return new SimulationQueueImpl<Engineer>();
    }

    @Provides
    Set<Engineer> provideEngineersSet() {
        return new HashSet<Engineer>();
    }

    @Provides
    Random provideRandomGenerator() {
        return new Random(nanoTime());
    }

    @Provides
    Map<Integer, Engineer> provideEngineerMap() {
        return new HashMap<Integer, Engineer>();
    }

    @Provides
    @Singleton
    ExecutorService provideExecutorService() {
        return newFixedThreadPool(N_THREADS);
    }
}
