package Colony;

import java.util.*;



import java.io.*;


public class Main {
    static Discrete_Stochastic_Simulation DSS;
    
    public static void main(String[] args) {
        DSS=new Discrete_Stochastic_Simulation(args);
        DSS.Simulation();
    }
}
// public class Main {
//     public static void main(String[] args) {
//         WeightedGraph graph = new WeightedGraph(5, 10);
//         graph.createGraphWithHamiltonianCircuit();
//         //graph.printGraph();
//         graph.printAdjMatrix();
//     }
    
// }
