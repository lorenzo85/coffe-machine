package org.exercise.coffee;

import com.google.inject.Singleton;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Thread.*;

@Singleton
public class SimulationProperties extends Properties {

    private Properties properties;

    public SimulationProperties() throws IOException {
        properties = loadApplicationProperties();
    }

    public String getAppName() {
        return properties.getProperty(KEY_APP_NAME);
    }

    public Float getHighPriorityProbability() {
        return Float.parseFloat(properties.getProperty(KEY_DEFAULT_HIGH_PRIORITY_PROBABILITY));
    }

    public int getWindowWith() {
       return Integer.parseInt(properties.getProperty(KEY_WINDOW_WIDTH));
    }

    public int getWindowHeight() {
        return Integer.parseInt(properties.getProperty(KEY_WINDOW_HEIGHT));
    }

    public int getNumEngineers() {
        return Integer.parseInt(properties.getProperty(KEY_DEFAULT_NUM_ENGINEERS));
    }

    public int getHighPriorityTimeSteps() {
        return Integer.parseInt(properties.getProperty(KEY_DEFAULT_HIGH_PRIORITY_TIME_STEPS));
    }

    public long getSimulationTimeStep() {
        return Long.parseLong(properties.getProperty(KEY_SIMULATION_TIME_STEP));
    }

    public long getTimeToMakeCoffee() {
        return Long.parseLong(properties.getProperty(KEY_TIME_TO_MAKE_COFFEE));
    }

    private Properties loadApplicationProperties() throws IOException {
        Properties prop = new Properties();
        ClassLoader loader = currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(CONFIG_PROPERTIES_FILE);
        prop.load(stream);
        return prop;
    }

    private static final String CONFIG_PROPERTIES_FILE = "config.properties";

    private static final String KEY_APP_NAME = "app_name";
    private static final String KEY_WINDOW_WIDTH = "window_width";
    private static final String KEY_WINDOW_HEIGHT = "window_height";
    private static final String KEY_TIME_TO_MAKE_COFFEE = "time_to_make_coffee";
    private static final String KEY_SIMULATION_TIME_STEP = "simulation_time_step";
    private static final String KEY_DEFAULT_NUM_ENGINEERS = "default_num_engineers";
    private static final String KEY_DEFAULT_HIGH_PRIORITY_TIME_STEPS = "default_high_priority_time_steps";
    private static final String KEY_DEFAULT_HIGH_PRIORITY_PROBABILITY = "default_high_priority_probability";
}