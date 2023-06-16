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

        // test:
    	// String[] args_main = { "-r", "5", "1", "1", "4", "5", "6", "7", "8", "9", "1", "10" };
        String[] args_main = { "-f", "SIM/input7.txt" };
        DSS = new Discrete_Stochastic_Simulation(args_main);

        DSS.Simulation();
    }
}