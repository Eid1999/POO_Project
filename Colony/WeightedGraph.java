package Colony;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * Represents a weighted graph.
 */
public class WeightedGraph implements Graph<String, Edge> {
    private Map<String, List<Edge>> adjacencyMap;
    private int numVertices;
    private double maxWeight;
    private String nestNode;

    /**
     * Constructs a weighted graph with the given number of vertices, maximum weight, and nest node.
     *
     * @param numVertices the number of vertices in the graph
     * @param maxWeight   the maximum weight of edges in the graph
     * @param n1    the nest node of the graph
     */
    public WeightedGraph(int numVertices, double maxWeight, String n1) {
        adjacencyMap = new HashMap<>();
        this.nestNode = n1;
        this.numVertices = numVertices;
        this.maxWeight = maxWeight;
    }

    /**
     * Returns the adjacency map of the graph.
     *
     * @return the adjacency map of the graph
     */
    public Map<String, List<Edge>> getAdjancyMap() {
        return adjacencyMap;
    }

    /**
     * Sets the nest node of the graph.
     *
     * @param nestNode the nest node to set
     */
    public void setNestNode(String nestNode) {
        this.nestNode = nestNode;
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex to add
     */
    public void addVertex(String vertex) {
        adjacencyMap.put(vertex, new ArrayList<>());
    }

    /**
     * Adds a directed edge to the graph between the given source and destination vertices with the specified weight.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @param weight      the weight of the edge
     */
    public void add1Edge(String source, String destination, double weight) {
        List<Edge> sourceEdges = adjacencyMap.getOrDefault(source, new ArrayList<>());
        sourceEdges.add(new Edge(source, destination, weight));
        adjacencyMap.put(source, sourceEdges);
    }

    /**
     * Adds an undirected edge to the graph between the given vertices with the specified weight.
     *
     * @param source the first vertex of the edge
     * @param destination the second vertex of the edge
     * @param weight  the weight of the edge
     */
    public void addEdge(String source, String destination, double weight) {
        List<Edge> sourceEdges = adjacencyMap.getOrDefault(source, new ArrayList<>());
        sourceEdges.add(new Edge(source, destination, weight));
        adjacencyMap.put(source, sourceEdges);

        List<Edge> destinationEdges = adjacencyMap.getOrDefault(destination, new ArrayList<>());
        destinationEdges.add(new Edge(destination, source, weight));
        adjacencyMap.put(destination, destinationEdges);
    }

    /**
     * Checks if an edge exists between the given source and destination vertices.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @return true if an edge exists, false otherwise
     */
    public boolean hasEdge(String source, String destination) {
        List<Edge> edges = adjacencyMap.get(source);
        if (edges != null) {
            for (Edge edge : edges) {
                if (edge.getDestination().equals(destination)) {
                    return true;
                }
            }
        }
        return false;
    }

     /**
     * Returns the edge between the given source and destination vertices.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @return the edge between the vertices, or null if no such edge exists
     */
    public Edge getEdge(String source, String destination) {
        List<Edge> edges = adjacencyMap.get(source);
        if (edges != null) {
            for (Edge edge : edges) {
                if (edge.getDestination().equals(destination)) {
                    return edge;
                }
            }
        }
        return null;
    }

    /**
     * Returns a list of all vertices in the graph.
     *
     * @return a list of vertices
     */
    public List<String> getVertices() {
        return new ArrayList<>(adjacencyMap.keySet());
    }
    
    /**
     * Returns a list of neighboring vertices of the given vertex.
     *
     * @param vertex the vertex to get neighbors of
     * @return a list of neighboring vertices
     */
    public List<String> getNeighbors(String vertex) {
        List<Edge> edges = adjacencyMap.get(vertex);
        List<String> neighbors = new ArrayList<>();
        if (edges != null) {
            for (Edge edge : edges) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    /**
     * Returns a list of all edges in the graph.
     *
     * @return a list of edges
     */
    public List<Edge> getEdges() {
        List<Edge> allEdges = new ArrayList<>();
        for (List<Edge> edges : adjacencyMap.values()) {
            allEdges.addAll(edges);
        }
        return allEdges;
    }

    /**
     * Returns the sum of weights of all edges in the graph.
     *
     * @return the sum of weights of all edges
     */
    public double getAllWeightsSum() {
        double weightSum = 0;
        for (List<Edge> edges : adjacencyMap.values()) {
            for (Edge edge : edges) {
                weightSum += edge.getWeight();
            }
        }
        return weightSum / 2;
    }

    /**
     * Updates the pheromone level of the edge between the given vertices.
     *
     * @param a         the source vertex
     * @param b         the destination vertex
     * @param pheromone the new pheromone level
     */
    public void updatePheromones(String a, String b, double pheromone) {

        getEdge(a, b).setPheromone(pheromone);
        getEdge(b, a).setPheromone(pheromone);
    }

    /**
     * Converts a path represented by a list of nodes into a list of directed edges.
     *
     * @param vis,  visited the list of visited nodes representing a path
     * @return a list of directed edges representing the path
     */
    public ArrayList<Edge> getPathAsEdges(ArrayList<String> vis) {
        ArrayList<Edge> edgelist = new ArrayList<Edge>();
        for (int i = 1; i < vis.size(); i++) {
            edgelist.add(this.getEdge(vis.get(i - 1), vis.get(i)));
        }
        return edgelist;
    }

    /**
     * Checks if the visited nodes form a completed Hamiltonian cycle and returns the cycle as a list of edges.
     *
     * @param visited the list of visited nodes
     * @return a list of edges representing the Hamiltonian cycle, or an empty list if no cycle is found
     */
    public ArrayList<Edge> checkHamilton(ArrayList<String> visited) {
        int sz = visited.size();
        ArrayList<Edge> hamilton = new ArrayList<Edge>();
        if ((visited.get(0)).equals(visited.get(sz - 1)) && visited.size()==numVertices+1) {
            hamilton = getPathAsEdges(visited);
        }    
        return hamilton;
    }

    /**
     * Creates a graph with a Hamiltonian circuit by randomly connecting the vertices.
     *
     * @return the graph with a Hamiltonian circuit
     */
    public Graph<String,Edge> createGraphWithHamiltonianCircuit() {
        Random random = new Random();
        String startVertex, endVertex;

        // Add vertices to the graph
        for (int i = 1; i <= numVertices; i++) {
            this.addVertex("v" + i);
        }

        // Create a list of vertices in random order
        List<String> vertices = new ArrayList<>(this.getVertices());
        vertices.remove(this.nestNode);
        Collections.shuffle(vertices);
        vertices.add(0, this.nestNode);
        // Connect the vertices in a circular manner to guarantee a Hamiltonian circuit
        for (int i = 0; i < numVertices; i++) {

            startVertex = vertices.get(i);
            endVertex = vertices.get((i + 1) % numVertices);
            this.addEdge(startVertex, endVertex, getRandomWeight(maxWeight));
        }

        // Add additional random edges to each vertex
        for (String vertex : this.getVertices()) {
            int numEdges = random.nextInt(numVertices - 1); // Generate a random number of edges (1 to numVertices)
            while (this.getNeighbors(vertex).size() < numEdges) {
                String targetVertex = vertices.get(random.nextInt(numVertices));
                if (!vertex.equals(targetVertex) && !this.hasEdge(vertex, targetVertex)) {
                    this.addEdge(vertex, targetVertex, getRandomWeight(maxWeight));
                }
            }
        }

        return this;
    }

    /**
     * Generates a random weight between 0 and the maximum weight.
     *
     * @param maxWeight the maximum weight
     * @return a random weight
     */
    private static double getRandomWeight(double maxWeight) {
        Random random = new Random();
        return Math.round(random.nextDouble() * maxWeight * 10) / 10.0; // Random weight between 0 and maxWeight with 2
        // decimal places
    }

    /**
     * Prints the adjacency matrix of the graph.
     */
    public void printAdjMatrix() {
        List<String> vertices = new ArrayList<>();
        for (int i = 1; i <= numVertices; i++) {
            vertices.add("v" + i);
        }

        // Create a 2D matrix to represent the adjacency matrix
        double[][] adjMatrix = new double[numVertices][numVertices];

        // Initialize the matrix with zeros
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                adjMatrix[i][j] = 0.0;
            }
        }

        // Fill the matrix with edge weights
        for (String vertex : vertices) {
            int sourceIndex = vertices.indexOf(vertex);
            List<Edge> edges = adjacencyMap.get(vertex);
            if (edges != null) {
                for (Edge edge : edges) {
                    int destIndex = vertices.indexOf(edge.getDestination());
                    adjMatrix[sourceIndex][destIndex] = edge.getWeight();
                }
            }
        }
        System.out.println();
        // Print matrix rows
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}