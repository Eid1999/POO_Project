package Colony;

/**
 * The Main class represents the entry point of the program. It initializes a Discrete_Stochastic_Simulation object
 * and runs the simulation.
 */
public class Main {
    private static Discrete_Stochastic_Simulation DSS;

    /**
     * The main method is the starting point of the program.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        DSS = new Discrete_Stochastic_Simulation(args);

        DSS.Simulation();
    }
}