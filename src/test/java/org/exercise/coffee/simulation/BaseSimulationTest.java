package org.exercise.coffee.simulation;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.exercise.coffee.machine.CoffeeMachine;
import org.exercise.coffee.model.Engineer;
import org.exercise.coffee.queue.SimulationQueue;
import org.junit.Before;
import org.mockito.Mock;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static com.google.inject.Guice.createInjector;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public abstract class BaseSimulationTest<T extends BaseSimulationTest> {

    @Mock
    protected Random random;
    @Mock
    protected Set<Engineer> engineers;
    @Mock
    protected CoffeeMachine coffeeMachine;
    @Mock
    protected SimulationLoop simulationLoop;
    @Mock
    protected ExecutorService executorService;
    @Mock
    protected SimulationQueue<Engineer> queue;
    @Mock
    protected Map<Integer, Engineer> engineersMap;
    @Mock
    protected Future mockedFuture;


    Injector injector;

    @Before
    public void before() {
        initMocks(getInstance());
        injector = createInjector(new MockedObjects(getMockedBindings()));
        injector.injectMembers(getInstance());

        when(executorService.submit(any(Runnable.class))).thenReturn(mockedFuture);
    }

    protected abstract T getInstance();
    protected abstract Map<Class, Object> getMockedBindings();


    class MockedObjects extends AbstractModule {

        private final Map<Class, Object> mockedBindings;

        public MockedObjects(Map<Class, Object> mockedBindings) {
            this.mockedBindings = mockedBindings;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void configure() {
            for (Map.Entry<Class, Object> entry : mockedBindings.entrySet()) {
                Class clazz = entry.getKey();
                Object value = entry.getValue();
                bind(clazz).toInstance(value);
            }
        }

        @Provides
        @Singleton
        SimulationQueue<Engineer> provideSimulationQueue() {
            return queue;
        }

        @Provides
        Set<Engineer> provideEngineersSet() {
            return engineers;
        }

        @Provides
        Random provideRandomGenerator() {
            return random;
        }

        @Provides
        Map<Integer, Engineer> provideEngineersMap() {
            return engineersMap;
        }

        @Provides
        ExecutorService provideExecutorService() {
            return executorService;
        }
    }

    static final Integer NUM_ENGINEERS = 10;
    static final Integer HIGH_PRIORITY_TIME_STEPS = 2;
    static final Float HIGH_PRIORITY_PROBABILITY = 0.2f;
}