package Colony;

public class Main {
    static Discrete_Stochastic_Simulation DSS;

    public static void main(String[] args) {
    	// test:
    	// String[] args_main = { "-r", "5", "1", "1", "4", "5", "6", "7", "8", "9", "1", "10" };
    	// String[] args_main = { "-f", "SIM/input8.txt"};
        // DSS = new Discrete_Stochastic_Simulation(args_main);
    	DSS = new Discrete_Stochastic_Simulation(args);
        DSS.Simulation();
    }
}