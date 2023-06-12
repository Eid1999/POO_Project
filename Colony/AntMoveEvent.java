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
    protected ArrayList<Edge> array_edge = new ArrayList<Edge>();
    protected ArrayList<String> nodes = new ArrayList<String>();

    public AntMoveEvent(WeightedGraph graph, Ant ant, double currentTime, float alpha, float beta, float delta) {

        this.ant = ant;
        this.graph = graph;
        this.Edge = ant.Optimization(graph, alpha, beta, delta);
        super.selfTime = exponentialDistribution(delta) + currentTime;

    }

    public void get(PriorityQueue<Events> events, double currentTime,
            HashMap<String, Float> Parameters, PriorityQueue<Halmiton_cicles> nodes_Queue) {
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
                edge.setPheromone(edge.getPheromone() + Parameters.get("gamma") / weight_circle);
                graph.getEdge(edge.getDestination(), edge.getSource())
                        .setPheromone(edge.getPheromone() + Parameters.get("gamma") / weight_circle);

                nodes.add(edge.getSource());

            }
            nodes.add(array_edge.get(array_edge.size() - 1).getDestination());
            nodes_Queue.add(new Halmiton_cicles(weight_circle, nodes));
        }

    }
}
