package Colony;

import java.util.*;

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
        super.selfTime = exponentialDistribution(delta) + currentTime;

    }

    public void get(PriorityQueue<Events> events, double currentTime,
            HashMap<String, Float> Parameters, PriorityQueue<Hamiltonian_Candidates> nodes_Queue) {
        array_edge = ant.Move(graph, Edge);
        float weight_circle = 0;

        events.add(new AntMoveEvent(graph, ant, currentTime, Parameters.get("alpha"), Parameters.get("beta"),
                Parameters.get("delta")));
        if (!array_edge.isEmpty()) {
            for (Edge edge : array_edge) {
                weight_circle += edge.getWeight();
            }
            for (Edge edge : array_edge) {
                if (edge.getPheromone() != 0) {
                    events.add(new PheromoneEVEvent(edge, currentTime, Parameters.get("eta"), graph));
                }
                graph.updatePheromones(edge.getSource(), edge.getDestination(),
                        (Parameters.get("gamma") * W) / weight_circle);
                nodes.add(edge.getSource());

            }
            nodes.add(array_edge.get(array_edge.size() - 1).getDestination());
            nodes_Queue.add(new Hamiltonian_Candidates(weight_circle, nodes));
        }

    }
}
