package Colony;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Represents an ant interface.
 */
public interface AntI {
    /**
     * Gets the current position of the ant.
     *
     * @return The current position of the ant.
     */
    public String getPosition();

    /**
     * Gets the path of the ant (list of visited nodes).
     *
     * @return The path of the ant.
     */
    public ArrayList<String> getPath();

    /**
     * Returns a list of unvisited nodes from a given set of nodes.
     *
     * @param nodelst The list of nodes.
     * @return The list of unvisited nodes.
     */
    public ArrayList<String> getUnvisited(List<String> nodelst);

    /**
     * Releases pheromones in every edge of the path upon completing the cycle.
     *
     * @param graph      The graph containing the edges.
     * @param phero      The pheromone value to be released.
     * @param events     The priority queue of events.
     * @param currentTime The current time.
     * @param eta        The eta value.
     */
    public void releasePheromones(Graph<String, Edge> graph, double phero, PriorityQueue<Events> events,
                                 double currentTime, float eta);

    /**
     * Removes a portion of the path to prevent loops.
     *
     * @param checkmark The starting index to remove the path from.
     */
    public void preventLoop(int checkmark);

    /**
     * Moves the ant along an edge and updates the path.
     *
     * @param edge The edge to move along.
     */
    public void Move(Edge edge);

    /**
     * Picks the next edge for the ant to move to using optimization rules.
     *
     * @param graph The graph containing the edges.
     * @param alpha The alpha value.
     * @param beta  The beta value.
     * @param delta The delta value.
     * @return The next edge for the ant to move to.
     */
    public Edge Optimization(Graph<String, Edge> graph, float alpha, float beta, float delta);
}
