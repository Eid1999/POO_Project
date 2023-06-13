package Colony;
import java.util.*;

class PheromoneEVEvent extends Events  {
    private Edge edge;
    WeightedGraph graph;
    String type="Evaporation";


    public PheromoneEVEvent(Edge edge, double  currentTime, float eta,WeightedGraph graph) {
        this.edge = edge;
        this.graph=graph;

        super.selfTime= currentTime+exponentialDistribution(eta);
        
        
        
    }
    public void get(PriorityQueue<Events> events,double currentTime,float eta,float rho) {
        events.add(new PheromoneEVEvent(edge, currentTime,eta,graph));
        edge.setPheromone(edge.getPheromone()-rho);
        graph.getEdge(edge.getDestination(), edge.getSource()).setPheromone(edge.getPheromone()-rho);
    }    

}