package org.exercise.coffee.simulation;

import com.google.inject.Inject;
import org.exercise.coffee.machine.CoffeeMachine;
import org.exercise.coffee.model.Engineer;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.*;

public class SimulationTest extends BaseSimulationTest<SimulationTest> {

    @Inject
    private Simulation simulation;
    @Mock
    private CoffeeMachine coffeeMachine;
    @Mock
    private SimulationLoop simulationLoop;
    @Captor
    private final ArgumentCaptor<Engineer> engineerArgumentCaptor = forClass(Engineer.class);

    @Test
    public void testStartShouldThrowExceptionWhenNumEngineersIsNull() {
        // Expect
        try {
            simulation.start(null, HIGH_PRIORITY_TIME_STEPS, HIGH_PRIORITY_PROBABILITY);
            fail("Should have thrown IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testStartShouldThrowExceptionWhenHighPriorityTimeStepsIsNull() {
        // Expect
        try {
            simulation.start(NUM_ENGINEERS, null, HIGH_PRIORITY_PROBABILITY);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testStartShouldThrowExceptionWhenHighPriorityProbabilityIsNull() {
        // Expect
        try {
            simulation.start(NUM_ENGINEERS, HIGH_PRIORITY_TIME_STEPS, null);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testStartShouldAddCorrectNumberOfEngineersInSet() {
        // Given
        Integer numEngineers = NUM_ENGINEERS;

        // When
        simulation.start(numEngineers, HIGH_PRIORITY_TIME_STEPS, HIGH_PRIORITY_PROBABILITY);

        // Then
        verify(engineers, times(numEngineers)).add(any(Engineer.class));
    }

    @Test
    public void testStartShouldCreateEngineerWithNotNullId() {
        // When
        simulation.start(1, HIGH_PRIORITY_TIME_STEPS, HIGH_PRIORITY_PROBABILITY);

        // Then
        verify(engineers, atLeastOnce()).add(engineerArgumentCaptor.capture());

        Engineer captured = engineerArgumentCaptor.getValue();
        assertNotNull(captured.getId());
    }

    @Test
    public void testSetListenerShouldThrowExceptionIfListenerIsNull() {
        // Given
        SimulationListener listener = null;

        // Expect
        try {
            simulation.setListener(listener);
            fail("Should have thrown null pointer exception");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testStartShouldSetCorrectListenerToSimulationLoop() {
        // Given
        SimulationListener listener = mock(SimulationListener.class);
        simulation.setListener(listener);

        // When
        simulation.start(NUM_ENGINEERS, HIGH_PRIORITY_TIME_STEPS, HIGH_PRIORITY_PROBABILITY);

        // Then
        verify(simulationLoop, times(1)).setListener(listener);
    }

    @Test
    public void testStartShouldSetCorrectListenerToCoffeeMachine() {
        // When
        simulation.start(NUM_ENGINEERS, HIGH_PRIORITY_TIME_STEPS, HIGH_PRIORITY_PROBABILITY);

        // Then
        verify(coffeeMachine, times(1)).setListener(simulationLoop);
    }

    @Test
    public void testStartShouldInitializeSimulationLoopWithCorrectParameters() {
        // When
        simulation.start(NUM_ENGINEERS, HIGH_PRIORITY_TIME_STEPS, HIGH_PRIORITY_PROBABILITY);

        // Then
        verify(simulationLoop, times(1)).init(engineers);
    }

    @Test
    public void testStartShouldStartCoffeeMachine() {
        // When
        simulation.start(NUM_ENGINEERS, HIGH_PRIORITY_TIME_STEPS, HIGH_PRIORITY_PROBABILITY);

        // Then
        verify(coffeeMachine, times(1)).start();
    }

    @Test
    public void testStartShouldStartSimulationLoop() {
        // When
        simulation.start(NUM_ENGINEERS, HIGH_PRIORITY_TIME_STEPS, HIGH_PRIORITY_PROBABILITY);

        // Then
        verify(simulationLoop, times(1)).start();
    }

    @Test
    public void testStopShouldThrowIllegalStateExceptionWhenCalledBeforeStart() {
        // Expect
        try {
            simulation.stop();
            fail("Should have thrown IllegalStateException");
        } catch (IllegalStateException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testStopShouldStopSimulationLoop() {
        // Given
        simulation.start(NUM_ENGINEERS, HIGH_PRIORITY_TIME_STEPS, HIGH_PRIORITY_PROBABILITY);

        // When
        simulation.stop();

        // Then
        verify(simulationLoop, times(1)).stop();
    }

    @Test
    public void testStopShouldStopCoffeeMachine() {
        // Given
        simulation.start(NUM_ENGINEERS, HIGH_PRIORITY_TIME_STEPS, HIGH_PRIORITY_PROBABILITY);

        // When
        simulation.stop();

        // Then
        verify(coffeeMachine, times(1)).stop();
    }

    @Test
    public void testStopShouldClearEngineersSet() {
        // Given
        simulation.start(NUM_ENGINEERS, HIGH_PRIORITY_TIME_STEPS, HIGH_PRIORITY_PROBABILITY);

        // When
        simulation.stop();

        // Then
        verify(engineers, times(1)).clear();
    }

    @Test
    public void testStopShouldClearSimulationQueue() {
        // Given
        simulation.start(NUM_ENGINEERS, HIGH_PRIORITY_TIME_STEPS, HIGH_PRIORITY_PROBABILITY);

        // When
        simulation.stop();

        // Then
        verify(queue, times(1)).clear();
    }

    @Test
    public void testStartSimulationThrowsIllegalStateExceptionIfAlreadyStarted() {
        // Given
        simulation.start(NUM_ENGINEERS, HIGH_PRIORITY_TIME_STEPS, HIGH_PRIORITY_PROBABILITY);

        // Expect
        try {
            simulation.start(NUM_ENGINEERS, HIGH_PRIORITY_TIME_STEPS, HIGH_PRIORITY_PROBABILITY);
            fail("Should have thrown IllegalStateException");
        } catch (IllegalStateException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testIsRunningReturnsTrue() {
       // Given
        simulation.start(NUM_ENGINEERS, HIGH_PRIORITY_TIME_STEPS, HIGH_PRIORITY_PROBABILITY);

        // When
        boolean actual = simulation.isRunning();

        // Then
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Override
    protected SimulationTest getInstance() {
        return this;
    }

    @Override
    protected Map<Class, Object> getMockedBindings() {
        Map<Class, Object> mockedBindings = new HashMap<Class, Object>();
        mockedBindings.put(CoffeeMachine.class, coffeeMachine);
        mockedBindings.put(SimulationLoop.class, simulationLoop);
        return mockedBindings;
    }
}
