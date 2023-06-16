package Colony;

// import java.util.*;

public class Main {
    static Discrete_Stochastic_Simulation DSS;

    public static void main(String[] args) {
    	String[] args_main = { "-r", "5", "1", "1", "4", "5", "6", "7", "8", "9", "1", "10" };
    	//String[] args_main = { "-f", "SIM/input5.txt"};
        DSS = new Discrete_Stochastic_Simulation(args_main);
        DSS.Simulation();
    }
}
// public class Main {
//     public static void main(String[] args) {

//         Colony colony = new Colony(7, "n1");
//         System.out.println("The size of the Colony is: " + colony.getSize());
//         WeightedGraph graph = new WeightedGraph(4, 3, "v1");
//         graph.createGraphWithHamiltonianCircuit();
//         graph.printGraph();
//         graph.printAdjMatrix();
//         System.out.println(" " + graph.getAllWeightsSum());
//     }
// }
