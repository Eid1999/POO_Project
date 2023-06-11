package Colony;

import java.util.*;



public class Main {
    static Discrete_Stochastic_Simulation DSS;
    
    public static void main(String[] args) {
        String[] args_main = {"-r", "7", "10", "1", "4", "5", "6", "7", "8", "9", "10", "11"};
        DSS=new Discrete_Stochastic_Simulation(args_main);
        DSS.Simulation();
    }
}
// public class Main {
//     public static void main(String[] args) {
        
//         WeightedGraph graph = new WeightedGraph(4, 10, "v1");
//         graph.createGraphWithHamiltonianCircuit();
//         graph.printGraph();
//         graph.printAdjMatrix();
//         graph.getEdge("v1", graph.getAdjancyMap().get("v1").get(0).getDestination()).setPheromone(4);
//         List <Edge> edgeWithPhero = graph.getEdgesWithPheromones();
//         System.out.println(edgeWithPhero);

//     }
// }
