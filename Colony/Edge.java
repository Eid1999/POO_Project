package Colony;
/**
 * Represents an edge in a graph, connecting a source node to a destination node.
 */
public class Edge {
    private String source;
    private String destination;
    private double weight;
    private double pheromone;

    /**
     * Constructs an edge with the given source, destination, and weight.
     *
     * @param source      The source node of the edge.
     * @param destination The destination node of the edge.
     * @param weight      The weight of the edge.
     */
    public Edge(String source, String destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.pheromone = 0;
    }

    /**
     * Gets the destination node of the edge.
     *
     * @return The destination node.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Gets the weight of the edge.
     *
     * @return The weight of the edge.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Gets the source node of the edge.
     *
     * @return The source node.
     */
    public String getSource() {
        return source;
    }

    /**
     * Gets the pheromone level of the edge.
     *
     * @return The pheromone level.
     */
    public double getPheromone() {
        return pheromone;
    }

    /**
     * Sets the pheromone level of the edge.
     *
     * @param pheromone The pheromone level to set.
     */
    public void setPheromone(double pheromone) {
        this.pheromone = pheromone;
    }

    @Override
    public String toString() {
        return source + " -> " + destination + " (Weight: " + weight + ", Pheromone: " + pheromone + ")";
    }
}
