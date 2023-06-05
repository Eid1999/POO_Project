package poo_proj;

import org.jgrapht.graph.*;
import java.util.*;
import org.jgrapht.GraphPath;
import org.jgrapht.Graph;
import org.jgrapht.alg.tour.HeldKarpTSP;

public class Main {
    public static <V, E> GraphPath<V, E> findHamiltonianCircuit(Graph<V, E> graph) {
        HeldKarpTSP<V, E> tspSolver = new HeldKarpTSP<>();
        GraphPath<V, E> tspPath = tspSolver.getTour(graph);
        
        if (tspPath != null && tspPath.getVertexList().size() == (graph.vertexSet().size()+1)) {
            return tspPath;
        } else {
            return null;
        }
    }
    public static void main(String[] args) {
        int numVertices = 7; // Specify the number of vertices in the graph
        int maxWeight = 20;
        SimpleWeightedGraph<String, DefaultWeightedEdge> simpleGraph = createGraphWithHamiltonianCircuit(numVertices, maxWeight);
        printGraph(simpleGraph);

        // Check if the graph is Hamiltonian and get the circuit if it exists
        GraphPath<String, DefaultWeightedEdge> hamiltonianCircuit = findHamiltonianCircuit(simpleGraph);

        if (hamiltonianCircuit != null) {
            System.out.println("The graph is Hamiltonian.");
            System.out.println("Hamiltonian Circuit: " + hamiltonianCircuit.getVertexList());
        } else {
            System.out.println("The graph is not Hamiltonian.");
        }
    }

    public static void printGraph(Graph<String, DefaultWeightedEdge> graph) {
        System.out.println("Vertices:");
        for (String vertex : graph.vertexSet()) {
            System.out.println(vertex);
        }

        System.out.println("Edges:");
        for (DefaultWeightedEdge edge : graph.edgeSet()) {
            String sourceVertex = graph.getEdgeSource(edge);
            String targetVertex = graph.getEdgeTarget(edge);
            double weight = graph.getEdgeWeight(edge);
            System.out.println(sourceVertex + " -> " + targetVertex + " (Weight: " + weight + ")");
        }
    }

    public static SimpleWeightedGraph<String, DefaultWeightedEdge> createGraphWithHamiltonianCircuit(int numVertices, int maxWeight) {
        SimpleWeightedGraph<String, DefaultWeightedEdge> simpleGraph = new SimpleWeightedGraph<>(
                DefaultWeightedEdge.class);
        Random random = new Random();

        // Add vertices to the graph
        for (int i = 1; i <= numVertices; i++) {
            simpleGraph.addVertex("v" + i);
        }

        // Create a list of vertices in random order
        List<String> vertices = new ArrayList<>(simpleGraph.vertexSet());
        Collections.shuffle(vertices);

        // Connect the vertices in a circular manner to guarantee an Hamiltonian circuit
        for (int i = 0; i < numVertices; i++) {
            String startVertex = vertices.get(i);
            String endVertex = vertices.get((i + 1) % numVertices);
            DefaultWeightedEdge edge = simpleGraph.addEdge(startVertex, endVertex);
            double weight = Math.round(random.nextDouble() * maxWeight*10) / 10.0; // Random weight between 0 and 10 with 2
                                                                          // decimal places
            simpleGraph.setEdgeWeight(edge, weight);
        }

        // Add additional random edges to each vertex
        for (String vertex : simpleGraph.vertexSet()) {
            int numEdges = random.nextInt(numVertices); // Generate a random number of edges (1 to numVertices)

            while (simpleGraph.edgesOf(vertex).size() < numEdges) {
                String targetVertex = vertices.get(random.nextInt(numVertices));

                if (!vertex.equals(targetVertex) && simpleGraph.getEdge(vertex, targetVertex) == null) {
                    DefaultWeightedEdge edge = simpleGraph.addEdge(vertex, targetVertex);
                    double weight = Math.round(random.nextDouble() * maxWeight *10) / 10.0; // Random weight between 0 and 10 with
                                                                                  // 2 decimal places
                    simpleGraph.setEdgeWeight(edge, weight);
                }
            }
        }

        return simpleGraph;
    }
}

