package Colony;

public class Edge implements EdgeInterface<String> {
    private String source;
    private String destination;
    private double weight;
    private double pheromone;

    public Edge(String source, String destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.pheromone = 0;
    }

    public String getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }

    public String getSource() {
        return source;
    }

    public double getPheromone(){
        return pheromone;
    }
    public void setPheromone(double pheromone){
        this.pheromone = pheromone;
    }
}
