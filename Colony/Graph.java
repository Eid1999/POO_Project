package Colony;

import java.util.ArrayList;
import java.util.List;


public interface Graph<V, E> {
    public void setNestNode(String nestNode);
    void addVertex(V vertex);
    void addEdge(V source, V destination, double weight);
    public void add1Edge(String source, String destination, double weight);
    boolean hasEdge(V source, V destination);
    E getEdge(V source, V destination);
    List<V> getVertices();
    List<V> getNeighbors(V vertex);
    public double getAllWeightsSum();
    public List<E> getEdges();
    void printAdjMatrix();
    public void updatePheromones(String a, String b, double pheromone);
    public ArrayList<E> getPathAsEdges(ArrayList<String> vis);
    public ArrayList<E> checkHamilton(ArrayList<String> visited);
    public Graph<String,Edge> createGraphWithHamiltonianCircuit();

}

