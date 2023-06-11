package Colony;

class PheromoneEVEvent extends Events  {
    private Edge edge;
    private double selfTime;
    String type="Evaporation";

    public PheromoneEVEvent(Edge edge, double  currentTime, float eta) {
        this.edge = edge;
        this.selfTime = currentTime+exponentialDistribution(eta);
        super.UpdateTime(selfTime);
    }
    public Edge get() {
        return edge;
    }

    

}
