package Colony;

import java.util.List;


public interface Graph<V, E> {
    void addVertex(V vertex);
    void addEdge(V source, V destination, double weight);
    boolean hasVertex(V vertex);
    boolean hasEdge(V source, V destination);
    E getEdge(V source, V destination);
    List<V> getVertices();
    List<V> getNeighbors(V vertex);
    public double getAllWeightsSum();
    public List<Edge> getEdges();
    void printGraph();
    void printAdjMatrix();
}

