package Colony;

public interface EdgeInterface<V> {
    V getDestination();
    double getWeight();
    V getSource();
    public double getPheromone();
    public void setPheromone(double pheromone);
}

