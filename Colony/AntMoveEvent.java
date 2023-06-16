package Colony;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Represents an event where an ant moves along an edge in the graph.
 */
class AntMoveEvent extends Events {
    private AntI ant;
    private Edge edge;
    private Graph<String, Edge> graph;
    private double W;
    private ArrayList<String> nodes = new ArrayList<String>();

    /**
     * Constructs an AntMoveEvent object.
     *
     * @param graph       The graph containing the edges and nodes.
     * @param ant         The ant that is moving.
     * @param currentTime The current simulation time.
     * @param alpha       The alpha parameter for ant movement.
     * @param beta        The beta parameter for ant movement.
     * @param delta       The delta parameter for ant movement.
     */
    public AntMoveEvent(Graph<String, Edge> graph, AntI ant, double currentTime, float alpha, float beta, float delta) {
        this.W = graph.getAllWeightsSum();
        this.ant = ant;
        this.graph = graph;
        this.edge = ant.Optimization(graph, alpha, beta, delta);
        super.selfTime = exponentialDistribution(delta) * edge.getWeight() + currentTime;
    }

    /**
     * Executes the AntMoveEvent and updates the state of the simulation.
     *
     * @param events       The priority queue of events.
     * @param currentTime The current simulation time.
     * @param Parameters  The parameters for the simulation.
     * @param nodes_Queue  The priority queue of Hamiltonian candidates.
     * @param top_Candidates The list of top candidate cycles.
     */
    public void get(PriorityQueue<Events> events, double currentTime,
                    HashMap<String, Float> Parameters, PriorityQueue<Hamiltonian_Candidates> nodes_Queue,
                    ArrayList<ArrayList<String>> top_Candidates) {

        float weight_circle = 0;
        ant.Move(edge);
        nodes = new ArrayList<String>(ant.getPath());

        ArrayList<Edge> array_edge = graph.checkHamilton(ant.getPath());
        if (!array_edge.isEmpty()) {
            for (Edge e : array_edge) {
                weight_circle += e.getWeight();
            }
            ant.releasePheromones(graph, (Parameters.get("gamma") * W) / weight_circle, events, currentTime,
                    Parameters.get("eta"));
            if (!top_Candidates.contains(nodes)) {
                ArrayList<String> aux = new ArrayList<String>();
                nodes.stream()
                        .filter(str -> str.length() >= 2)
                        .map(str -> String.valueOf(str.substring(1, str.length())))
                        .forEach(aux::add);
                nodes_Queue.add(new Hamiltonian_Candidates(weight_circle, new ArrayList<>(aux.subList(0, aux.size() - 1))));
                top_Candidates.add(nodes);
            }
        }

        events.add(new AntMoveEvent(graph, ant, currentTime, Parameters.get("alpha"), Parameters.get("beta"),
                Parameters.get("delta")));
    }
}

