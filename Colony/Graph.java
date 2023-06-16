package Colony;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph Interface 
 */
public interface Graph<V, E> {
    /**
     * Sets the nest node in the graph.
     *
     * @param nestNode the nest node
     */
    public void setNestNode(V nestNode);

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex to add
     */
    void addVertex(V vertex);

    /**
     * Adds an undirected edge to the graph between the source and destination vertices with the specified weight.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @param weight      the weight of the edge
     */
    void addEdge(V source, V destination, double weight);

    /**
     * Adds a directed edge from the source to the destination vertex with the specified weight.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @param weight      the weight of the edge
     */
    public void add1Edge(V source, V destination, double weight);

    /**
     * Checks if there is an edge between the source and destination vertices.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @return true if there is an edge between the vertices, false otherwise
     */
    boolean hasEdge(V source, V destination);

    /**
     * Returns the edge between the source and destination vertices, or null if no edge exists.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @return the edge between the vertices, or null if no edge exists
     */
    E getEdge(V source, V destination);

    /**
     * Returns a list of all vertices in the graph.
     *
     * @return a list of vertices
     */
    List<V> getVertices();

    /**
     * Returns a list of neighboring vertices for the given vertex.
     *
     * @param vertex the vertex
     * @return a list of neighboring vertices
     */
    List<V> getNeighbors(V vertex);

    /**
     * Returns the sum of weights for all edges in the graph.
     *
     * @return the sum of weights
     */
    public double getAllWeightsSum();

    /**
     * Returns a list of all edges in the graph.
     *
     * @return a list of edges
     */
    public List<E> getEdges();

    /**
     * Prints the adjacency matrix of the graph.
     */
    void printAdjMatrix();

    /**
     * Updates the pheromone value for the edge between vertices a and b.
     *
     * @param a         the source vertex
     * @param b         the destination vertex
     * @param pheromone the new pheromone value
     */
    public void updatePheromones(V a, V b, double pheromone);

    /**
     * Converts a path represented as a list of vertices into a list of directed edges.
     *
     * @param vis the path as a list of vertices
     * @return a list of directed edges
     */
    public ArrayList<E> getPathAsEdges(ArrayList<String> vis);

    /**
     * Checks for a completed Hamiltonian Cycle based on the visited vertices.
     *
     * @param visited the list of visited vertices
     * @return a list of edges representing a Hamiltonian Cycle if found, otherwise an empty list
     */
    public ArrayList<E> checkHamilton(ArrayList<String> visited);

    /**
     * Creates a graph with a Hamiltonian circuit.
     *
     * @return a graph with a Hamiltonian circuit
     */
    public Graph<V, E> createGraphWithHamiltonianCircuit();
}
