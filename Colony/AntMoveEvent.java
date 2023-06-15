package Colony;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

// import java.io.*;
class AntMoveEvent extends Events {
    private Ant ant;
    // private Edge edge;
    Random rand = new Random();
    String type = "AntMove";
    private Edge Edge;
    WeightedGraph graph;
    double W;
    protected ArrayList<Edge> array_edge = new ArrayList<Edge>();
    protected ArrayList<String> nodes = new ArrayList<String>();

    public AntMoveEvent(WeightedGraph graph, Ant ant, double currentTime, float alpha, float beta, float delta) {
        this.W = graph.getAllWeightsSum();
        this.ant = ant;
        this.graph = graph;
        this.Edge = ant.Optimization(graph, alpha, beta, delta);
        super.selfTime = exponentialDistribution(delta)*Edge.getWeight() + currentTime;

    }

    public void get(PriorityQueue<Events> events, double currentTime,
            HashMap<String, Float> Parameters, PriorityQueue<Hamiltonian_Candidates> nodes_Queue,ArrayList<ArrayList<String>> top_Candidates) {

        float weight_circle = 0;
        ant.Move(Edge);
        nodes = new ArrayList<String>(ant.getPath());
        
        array_edge = graph.checkHamilton(ant.getPath());
        if (!array_edge.isEmpty()) {
            for (Edge edge : array_edge) {
                weight_circle += edge.getWeight();
            }
            ant.releasePheromones(graph, (Parameters.get("gamma") * W) / weight_circle, events, currentTime,
                    Parameters.get("eta"));
            if(!top_Candidates.contains(nodes)){
                ArrayList<String> aux = new ArrayList<>();
                nodes.stream()
                .filter(s -> s.length() >= 2)
                .map(s -> String.valueOf(s.charAt(1)))
                .forEach(aux::add);
                nodes_Queue.add(new Hamiltonian_Candidates(weight_circle,  new ArrayList<>(aux.subList(0, aux.size()-1))));
                top_Candidates.add(nodes);
            }
        }
        
        events.add(new AntMoveEvent(graph, ant, currentTime, Parameters.get("alpha"), Parameters.get("beta"),
                Parameters.get("delta")));
        

    }
}
