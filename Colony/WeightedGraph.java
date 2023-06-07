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
        List<Edge> ownEdges = adjacencyMap.get(vertex);
        List<String> neighbors = new ArrayList<>();
        if (ownEdges != null) {
            for (Edge edge : ownEdges) {
                neighbors.add(edge.getDestination());
            }
        }
        for (Map.Entry<String, List<Edge>> entry : adjacencyMap.entrySet()) {
            List<Edge> edges = entry.getValue();
            for (Edge edge : edges) {
                if (edge.getDestination().equals(vertex)) {
                    neighbors.add(entry.getKey());
                    break; // No need to continue searching in the current vertex's edges
                }
            }
        }
        return neighbors;
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

        // Add additional random edges to each vertex
        for (String vertex : this.getVertices()) {
            int numEdges = random.nextInt(numVertices - 1); // Generate a random number of edges (1 to numVertices)
            while (this.getNeighbors(vertex).size() < numEdges) {
                String targetVertex = vertices.get(random.nextInt(numVertices));
                if (!vertex.equals(targetVertex) && !this.hasEdge(vertex, targetVertex)
                        && !this.hasEdge(targetVertex, vertex)) {
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

    public void printAdjMatrix() {
        List<String> vertices = getVertices();
        int numVertices = vertices.size();

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