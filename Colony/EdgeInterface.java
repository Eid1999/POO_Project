package Colony;

public interface EdgeInterface<V> {
    V getDestination();
    double getWeight();
    V getSource();
}
