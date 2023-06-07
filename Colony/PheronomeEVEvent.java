package Colony;

class PheronomeEVEvent implements Comparable<PheronomeEVEvent> {
    private Edge edge;
    private double EvaporationTime;

    public PheronomeEVEvent(Edge edge, double  EvaporationTime) {
        this.edge = edge;
        this.EvaporationTime = EvaporationTime;
    }

    public Edge getEdge() {
        return edge;
    }

    public double getMoveTime() {
        return EvaporationTime;
    }

    @Override
    public int compareTo(PheronomeEVEvent other) {
        return Double.compare(this.EvaporationTime, other.EvaporationTime);
    }
}
