package Colony;

import java.util.PriorityQueue;

/**
 * Represents an event where pheromone evaporation occurs on an edge in the graph.
 */
class PheromoneEVEvent extends Events {
    private Edge edge;
    private Graph<String, Edge> graph;

    /**
     * Constructs a PheromoneEVEvent object.
     *
     * @param edge        The edge on which pheromone evaporation occurs.
     * @param currentTime The current simulation time.
     * @param eta         The eta parameter for pheromone evaporation.
     * @param graph       The graph containing the edges and nodes.
     */
    public PheromoneEVEvent(Edge edge, double currentTime, float eta, Graph<String, Edge> graph) {
        this.edge = edge;
        this.graph = graph;
        super.selfTime = currentTime + exponentialDistribution(eta);
    }

    /**
     * Executes the PheromoneEVEvent and updates the state of the simulation.
     *
     * @param events      The priority queue of events.
     * @param currentTime The current simulation time.
     * @param eta         The eta parameter for pheromone evaporation.
     * @param rho         The rho parameter for pheromone evaporation.
     */
    public void get(PriorityQueue<Events> events, double currentTime, float eta, float rho) {
        if (edge.getPheromone() - rho < 0) {
            edge.setPheromone(0);
            graph.getEdge(edge.getDestination(), edge.getSource()).setPheromone(0);
        } else {
            events.add(new PheromoneEVEvent(edge, currentTime, eta, graph));
            edge.setPheromone(edge.getPheromone() - rho);
            graph.getEdge(edge.getDestination(), edge.getSource()).setPheromone(edge.getPheromone() - rho);
        }
    }
}

