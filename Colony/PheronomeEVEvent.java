package Colony;

class PheronomeEVEvent extends Events  {
    private Edge edge;
    private double selfTime;
    String type="Evaporation";

    public PheronomeEVEvent(Edge edge, double  currentTime,float delta) {
        this.edge = edge;
        this.selfTime = currentTime+exponentialDistribution(delta);
        super.UpdateTime(selfTime);
    }
    public Edge get() {
        return edge;
    }

    

}
