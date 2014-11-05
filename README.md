Coffee Machine Simulator
========================
Just a simple app which models a coffee machine. 

- The app uses a Priority queue with FIFO policy for same priorities values.
- The app uses Swing for the UI

* Building the project

- requires maven, used version: 3.2.1):
- From root directory run: mvn clean install (to compile and run the tests and create executable jar with dependencies)
- Go to 'target' folder:  cd target/
- Run the jar: java -jar CoffeeMachine-1.0-SNAPSHOT-jar-with-dependencies.jar


* Improvements that could be done in the code

- Add more tests
- Improve the algorithm in SimulationLoop implementation.
- Enforce more preconditions on values and application states checks 
- Move on a config file all the hardcoded text used in the application
- Use DTOs instead of passing directly objects (instances of Engineers) from the core Simulation to the Controller (hence the UI layer)
- Improve abstractions on the implementations of PriorityStrategy interface such that we could apply these strategies not to only Engineer instances but to generic Objects having a proper interface provided with methods needed to manipulate the Priority state
- Improve error messages provided on the UI according to the error cause and to the specific parameter

* Comments on user inputs

- In general, the more is the number of engineers in the Simulation, the more is likely for a given engineer to spend greater time in queue.
- The high priority probability (for an engineer is the probability to become super busy) impacts on the engineers in queue with “normal priority”, but also on the engineers in queue with “high priority” - this is because given the same priority level, the simulation uses a FIFO policy to serve the next coffee -.
- The number of steps for which an engineer stays super-busy (high – priority -steps) impacts on the time spent by engineers in the queue as well. The greater it is, the greater is the potential amount of time spent in the queue by engineers with “normal priority”.
