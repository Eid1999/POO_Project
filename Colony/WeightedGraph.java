package Colony;

import java.util.*;

public class WeightedGraph implements Graph<String, Edge> {
    private Map<String, List<Edge>> adjacencyMap;
    private int numVertices;
    private double maxWeight;

    public WeightedGraph(int numVertices, double maxWeight) {
        adjacencyMap = new HashMap<>();
        this.numVertices = numVertices;
        this.maxWeight = maxWeight;
    }

    public void addVertex(String vertex) {
        adjacencyMap.put(vertex, new ArrayList<>());
    }

    public void addEdge(String source, String destination, double weight) {
        List<Edge> edges = adjacencyMap.getOrDefault(source, new ArrayList<>());
        edges.add(new Edge(source, destination, weight));
        adjacencyMap.put(source, edges);
    }

    public boolean hasVertex(String vertex) {
        return adjacencyMap.containsKey(vertex);
    }

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

    public List<String> getVertices() {
        return new ArrayList<>(adjacencyMap.keySet());
    }

    public List<String> getNeighbors(String vertex) {
        List<Edge> edges = adjacencyMap.get(vertex);
        if (edges != null) {
            List<String> neighbors = new ArrayList<>();
            for (Edge edge : edges) {
                neighbors.add(edge.getDestination());
            }
            return neighbors;
        }
        return new ArrayList<>();
    }

    public List<Edge> getEdges() {
        List<Edge> allEdges = new ArrayList<>();
        for (List<Edge> edges : adjacencyMap.values()) {
            for (Edge edge : edges) {
                allEdges.add(edge);
            }
        }
        return allEdges;
    }

    public WeightedGraph createGraphWithHamiltonianCircuit() {
        Random random = new Random();
        String startVertex, endVertex;

        // Add vertices to the graph
        for (int i = 1; i <= numVertices; i++) {
            this.addVertex("v" + i);
        }

        // Create a list of vertices in random order
        List<String> vertices = new ArrayList<>(this.getVertices());
        Collections.shuffle(vertices);
        // Connect the vertices in a circular manner to guarantee a Hamiltonian circuit
        for (int i = 0; i < numVertices; i++) {
            startVertex = vertices.get(i);
            endVertex = vertices.get((i + 1) % numVertices);
            this.addEdge(startVertex, endVertex, getRandomWeight(maxWeight));
        }

        this.printGraph();

        // Add additional random edges to each vertex
        for (String vertex : this.getVertices()) {
            int numEdges = random.nextInt(numVertices); // Generate a random number of edges (1 to numVertices)
    
            while (this.getNeighbors(vertex).size() < numEdges) {
                String targetVertex = vertices.get(random.nextInt(numVertices));
    
                if (!vertex.equals(targetVertex) && !this.hasEdge(vertex, targetVertex) && !this.hasEdge(targetVertex, vertex)) {
                    this.addEdge(vertex, targetVertex, getRandomWeight(maxWeight));
                }
            }
        }

        return this;
    }

    private static double getRandomWeight(double maxWeight) {
        Random random = new Random();
        return Math.round(random.nextDouble() * maxWeight * 10) / 10.0; // Random weight between 0 and maxWeight with 2
                                                                        // decimal places
    }

    public void printGraph() {
        System.out.println("Vertices:");
        for (String vertex : this.getVertices()) {
            System.out.println(vertex);
        }
    
        System.out.println("Edges:");
        for (Edge edge : this.getEdges()) {
            String sourceVertex = edge.getSource();
            String targetVertex = edge.getDestination();
            double weight = edge.getWeight();
            System.out.println(sourceVertex + " -> " + targetVertex + " (Weight: " + weight + ")");
        }
    }
    
}