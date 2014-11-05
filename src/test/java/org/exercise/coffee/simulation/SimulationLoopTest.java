package org.exercise.coffee.simulation;

import com.google.common.collect.ImmutableSet;
import org.exercise.coffee.model.Engineer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;
import static org.mockito.Mockito.*;

public class SimulationLoopTest extends BaseSimulationTest<SimulationLoopTest> {

    @Mock
    private SimulationListener listener;

    private SimulationLoop simulationLoop;
    private Engineer engineer1;
    private Engineer engineer2;

    @Before
    public void before() {
        super.before();
        simulationLoop = injector.getInstance(SimulationLoopImpl.class);
        simulationLoop.setListener(listener);

        engineer1 = new Engineer(1, 3, 0.2f);
        engineer2 = new Engineer(2, 3, 0.2f);
    }

    @Test
    public void testShouldThrowExceptionWhenInitAndListenerNotSet() {
        // Given
        simulationLoop.removeListener();

        // Expect
        try {
            simulationLoop.init(engineers);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testAllEngineersShouldBeAddedToEngineersMap() {
        // Given
        Set<Engineer> engineers = ImmutableSet.of(engineer1, engineer2);

        // When
        simulationLoop.init(engineers);

        // Then
        verify(engineersMap, times(1)).put(engineer1.getId(), engineer1);
        verify(engineersMap, times(1)).put(engineer2.getId(), engineer2);
    }

    @Test
    public void testListenerShouldBeNotifiedOfAllIdleUsersWhenInit() {
        // Given
        Set<Engineer> engineers = ImmutableSet.of(engineer1, engineer2);

        // When
        simulationLoop.init(engineers);

        // Then
        verify(listener, times(1)).onEngineerIdle(engineer1);
        verify(listener, times(1)).onEngineerIdle(engineer2);
    }

    @Test
    public void testListenerNotifiedWhenStopped() throws InterruptedException {
        // When
        simulationLoop.onStopped();

        // Then
        verify(listener, times(1)).onSimulationLoopTerminated();
    }

    @Test
    public void testListenerNotifiedWhenCoffeeMachineTerminated() {
        // When
        simulationLoop.onCoffeeMachineTerminated();

        // Then
        verify(listener, times(1)).onCoffeeMachineTerminated();
    }

    @Test
    public void testListenerNotifiedWhenPreparingCoffee() {
        // When
        simulationLoop.preparingCoffee(engineer1);

        // Then
        verify(listener, times(1)).onCoffeePreparing(engineer1);
    }

    @Test
    public void testListenerNotifiedWhenOnCoffeeReady() {
        // When
        simulationLoop.onCoffeeReady(engineer1);

        // Then
        verify(listener, times(1)).onCoffeeReady(engineer1);
    }

    @Test
    public void testListenerNotifiedWithEngineerIdleWhenOnCoffeeReady() {
        // When
        simulationLoop.onCoffeeReady(engineer1);

        // Then
        verify(listener, times(1)).onEngineerIdle(engineer1);
    }

    @Test
    public void testShouldAddEngineerToEngineersMapWhenOnCoffeeReady() {
        // When
        simulationLoop.onCoffeeReady(engineer2);

        // Then
        verify(engineersMap, times(1)).put(engineer2.getId(), engineer2);
    }

    @Test
    public void testSetListenerShouldThrowExceptionWhenNull() {
        // Given
        SimulationListener simulationListener = null;

        // Expect
        try {
            simulationLoop.setListener(simulationListener);
            fail("Should have thrown NullPointerException");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testLoopShouldRemoveFromEngineersMapObjectWithCorrectId() {
        // Given
        Integer id = engineer1.getId();

        when(random.nextInt(anyInt())).thenReturn(id);

        // When
        simulationLoop.loop();

        // Then
        verify(engineersMap, times(1)).remove(id);
    }

    @Test
    public void testLoopShouldNotCallListenerIfMapDoesNotContainObjectWithId() {
        // Given
        Integer id = engineer1.getId();

        when(random.nextInt(anyInt())).thenReturn(id);
        when(engineersMap.remove(id)).thenReturn(null);

        // When
        simulationLoop.loop();

        // Then
        verifyNoMoreInteractions(listener);
    }

    @Test
    public void testLoopShouldCallNextStepToEngineer() {
        // Given
        Integer id = 1;
        Engineer engineer = mock(Engineer.class);

        when(random.nextInt(anyInt())).thenReturn(id);
        when(engineersMap.remove(id)).thenReturn(engineer);

        // When
        simulationLoop.loop();

        // Then
        verify(engineer, times(1)).nextStep();
    }

    @Test
    public void testLoopShouldAddToSimulationQueueEngineer() {
        // Given
        Integer id = engineer1.getId();

        when(random.nextInt(anyInt())).thenReturn(id);
        when(engineersMap.remove(id)).thenReturn(engineer1);

        // When
        simulationLoop.loop();

        // Then
        verify(queue, times(1)).add(engineer1);
    }

    @Override
    protected Map<Class, Object> getMockedBindings() {
        return new HashMap<Class, Object>();
    }

    @Override
    protected SimulationLoopTest getInstance() {
        return this;
    }
}
