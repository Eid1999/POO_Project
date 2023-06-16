package Colony;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Represents an ant in a colony.
 */
public class Ant implements AntI {
    private ArrayList<String> path = new ArrayList<String>();

    /**
     * Constructs an ant with the specified nest node.
     *
     * @param nest The nest node for the ant.
     */
    public Ant(String nest) {
        path.add("v" + nest);
    }

    /**
     * Gets the current position of the ant.
     *
     * @return The current position of the ant.
     */
    public String getPosition() {
        int sz = path.size();
        return path.get(sz - 1);
    }

    /**
     * Gets the path of the ant.
     *
     * @return The path of the ant as a list of visited nodes.
     */
    public ArrayList<String> getPath() {
        return path;
    }

    /**
     * Returns a list of unvisited nodes from a given set of nodes.
     *
     * @param nodelst The list of nodes to check for unvisited nodes.
     * @return The list of unvisited nodes from the given set of nodes.
     */
    public ArrayList<String> getUnvisited(List<String> nodelst) {
        ArrayList<String> unvisited = new ArrayList<String>();
        for (int i = 0; i < nodelst.size(); i++) {
            if (!path.contains(nodelst.get(i))) {
                unvisited.add(nodelst.get(i));
            }
        }
        return unvisited;
    }

    /**
     * Releases pheromones in every edge of the path upon completing the cycle.
     * Resets the ant's path to start a new search.
     * Adds pheromone evaporation events to the event queue.
     *
     * @param graph       The graph containing the edges.
     * @param phero       The amount of pheromones to release.
     * @param events      The priority queue of events.
     * @param currentTime The current time of the simulation.
     * @param eta         The evaporation rate for pheromones.
     */
    public void releasePheromones(Graph<String, Edge> graph, double phero, PriorityQueue<Events> events,
            double currentTime, float eta) {
        String aux = getPosition();
        for (int i = 1; i < path.size() - 1; i++) {
            Edge edge = graph.getEdge(path.get(i - 1), path.get(i));
            if (edge.getPheromone() == 0) {
                events.add(new PheromoneEVEvent(edge, currentTime, eta, graph));
            }
            graph.updatePheromones(path.get(i - 1), path.get(i), phero + edge.getPheromone());
        }
        path.clear();
        path.add(aux);
    }

    /**
     * Removes a portion of the path to prevent loops.
     *
     * @param checkmark The index to start removing the path from.
     */
    public void preventLoop(int checkmark) {
        int size = path.size();
        for (int i = checkmark; i < size - 1; i++) {
            path.remove(checkmark);
        }
    }

    /**
     * Moves the ant along the given edge and updates the path array accordingly.
     *
     * @param edge The edge to move the ant along.
     */
    public void Move(Edge edge) {
        path.add(edge.getDestination());
    }

    /**
     * Picks the next edge for the ant to move to using optimization criteria.
     *
     * @param graph The graph containing the edges.
     * @param alpha The weight for pheromones in the edge probability calculation.
     * @param beta  The weight for the edge weight in the edge probability calculation.
     * @param delta A random factor to introduce some randomness in the edge probability calculation.
     * @return The next edge for the ant to move to.
     */
    public Edge Optimization(Graph<String, Edge> graph, float alpha, float beta, float delta) {
        double p = Math.random(); // random roll (double between 0 and 1)
        double sum = 0.00; // used as the sum of all probabilities
        double cumulativeProbability = 0.00; // used for the random roll
        Edge nextedge = null; // next position (return)
        Edge possedge = null; // auxiliary
        ArrayList<String> unvis = new ArrayList<String>(); // unvisited nodes
        ArrayList<Double> prob = new ArrayList<Double>(); // probability of each node
        List<String> adj = new ArrayList<String>(); // adjacent nodes
        adj = graph.getNeighbors(getPosition());
        unvis = getUnvisited(adj);
        if (path.size() >= 3) {
            for (int i = 0; i < path.size() - 1; i++) {
                if (path.get(path.size() - 1).equals(path.get(i))) {
                    preventLoop(i);
                }
            }
        } // prevents unwanted loop
        // case where all adjacent nodes have been visited
        if (unvis.isEmpty()) {
            // calculate (uniform) probability for each node
            for (int i = 0; i < adj.size(); i++) {
                prob.add(1.0 / ((double) (adj.size())));
            }
            // pick path by summing probabilities in list
            for (int i = 0; i <= adj.size(); i++) {
                cumulativeProbability += prob.get(i);
                if (p <= cumulativeProbability) {
                    nextedge = graph.getEdge(getPosition(), adj.get(i));
                    break;
                }
            }
        }
        // case where there are unvisited adjacent nodes
        else {
            // calculate edge probability for each node (parameter/weight/pheromone based)
            for (int i = 0; i < unvis.size(); i++) {
                possedge = graph.getEdge(getPosition(), unvis.get(i));
                prob.add((alpha + possedge.getPheromone()) / (beta + possedge.getWeight()));
                sum += prob.get(i); // increment sum for later calculation
            }
            // divide each member by sum for correct probability
            for (int i = 0; i <= unvis.size(); i++) {
                cumulativeProbability += prob.get(i) / sum;
                if (p <= cumulativeProbability) {
                    nextedge = graph.getEdge(getPosition(), unvis.get(i));
                    break;
                }
            }
        }
        return nextedge;
    }
}

