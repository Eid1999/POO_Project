package Colony;

class PheromoneEVEvent extends Events  {
    private Edge edge;
    private double selfTime;
    String type="Evaporation";

    public PheromoneEVEvent(Edge edge, double  currentTime,float delta) {
        this.edge = edge;
        this.selfTime = currentTime+exponentialDistribution(delta);
        super.UpdateTime(selfTime);
    }
    public Edge get() {
        return edge;
    }

    

}
