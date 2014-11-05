package org.exercise.coffee.bootstrap;


import com.google.inject.Injector;
import org.exercise.coffee.simulation.Simulation;
import org.exercise.coffee.ui.Controller;
import org.exercise.coffee.ui.SimulationUi;
import org.exercise.coffee.ui.UiEventQueueDispatcher;

import java.io.IOException;

import static com.google.inject.Guice.createInjector;
import static javax.swing.SwingUtilities.invokeLater;

class Main  {

    public static void main(String[] args) throws IOException {
        final Injector injector = createInjector(new SimulationModule());

        invokeLater(new Runnable() {
            @Override
            public void run() {
                SimulationUi simulationUi = injector.getInstance(UiEventQueueDispatcher.class);
                Simulation simulation = injector.getInstance(Simulation.class);
                Controller controller = injector.getInstance(Controller.class);

                simulationUi.init();
                controller.init(simulationUi, simulation);
            }
        });
    }
}
