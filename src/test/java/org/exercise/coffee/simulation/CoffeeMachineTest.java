package org.exercise.coffee.simulation;

import org.exercise.coffee.machine.CoffeeMachine;
import org.exercise.coffee.machine.CoffeeMachineImpl;
import org.exercise.coffee.machine.CoffeeMachineListener;
import org.exercise.coffee.model.Engineer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;
import static org.mockito.Mockito.*;

public class CoffeeMachineTest extends BaseSimulationTest<CoffeeMachineTest> {

    private CoffeeMachine coffeeMachine;
    private Engineer engineer;

    @Mock
    private CoffeeMachineListener listener;

    @Before
    public void before() {
        super.before();
        coffeeMachine = injector.getInstance(CoffeeMachineImpl.class);
        coffeeMachine.setListener(listener);

        engineer = new Engineer(1, 3, 0.2f);
    }

    @Test
    public void testShouldNotifyListenerWhenOnStopped() {
        // When
        coffeeMachine.onStopped();

        // Then
        verify(listener, times(1)).onCoffeeMachineTerminated();
    }

    @Test
    public void testShouldThrowExceptionWhenListenerIsNull() {
        // Given
        CoffeeMachineListener listener = null;

        // Expect
        try {
            coffeeMachine.setListener(listener);
            fail("Should have thrown NullPointerException");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testDoRunShouldTakeNextElementFromQueue() throws InterruptedException {
        // Given
        when(queue.take()).thenReturn(engineer);

        // When
        coffeeMachine.doRun();

        // Then
        verify(queue, times(1)).take();
    }

    @Test
    public void testShouldCallListenerPreparingCoffee() throws InterruptedException {
        // Given
        when(queue.take()).thenReturn(engineer);

        // When
        coffeeMachine.doRun();

        // Then
        verify(listener, times(1)).preparingCoffee(engineer);
    }

    @Test
    public void testShouldCallListenerOnCoffeeReady() throws InterruptedException {
        // Given
        when(queue.take()).thenReturn(engineer);

        // When
        coffeeMachine.doRun();

        // Then
        verify(listener, times(1)).preparingCoffee(engineer);
    }

    @Override
    protected CoffeeMachineTest getInstance() {
        return this;
    }

    @Override
    protected Map<Class, Object> getMockedBindings() {
        return new HashMap<Class, Object>();
    }
}
