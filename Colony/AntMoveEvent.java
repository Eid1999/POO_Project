package Colony;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

class AntMoveEvent extends Events {
    private AntI ant;
    private Edge Edge;
    private Graph<String,Edge> graph;
    private double W;
    private ArrayList<String> nodes = new ArrayList<String>();

    public AntMoveEvent(Graph<String,Edge> graph, AntI ant, double currentTime, float alpha, float beta, float delta) {
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
        
        ArrayList<Edge> array_edge = graph.checkHamilton(ant.getPath());
        if (!array_edge.isEmpty()) {
            for (Edge edge : array_edge) {
                weight_circle += edge.getWeight();
            }
            ant.releasePheromones(graph, (Parameters.get("gamma") * W) / weight_circle, events, currentTime,
                    Parameters.get("eta"));
            if(!top_Candidates.contains(nodes)){
                ArrayList<String> aux = new ArrayList<String>();
                nodes.stream()
                .filter(str -> str.length() >= 2)
                .map(str -> String.valueOf(str.substring(1, str.length())))
                .forEach(aux::add);
                nodes_Queue.add(new Hamiltonian_Candidates(weight_circle,  new ArrayList<>(aux.subList(0, aux.size()-1))));
                top_Candidates.add(nodes);
            }
        }
        
        events.add(new AntMoveEvent(graph, ant, currentTime, Parameters.get("alpha"), Parameters.get("beta"),
                Parameters.get("delta")));
        

    }
}
