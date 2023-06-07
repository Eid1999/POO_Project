package Colony;

class AntMoveEvent implements Comparable<AntMoveEvent> {
    private AntI ant;
    private double moveTime;

    public AntMoveEvent(AntI ant, double moveTime) {
        this.ant = ant;
        this.moveTime = moveTime;
        ant.Move();
    }

    public AntI getAnt() {
        return ant;
    }

    public double getMoveTime() {
        return moveTime;
    }

    @Override
    public int compareTo(AntMoveEvent other) {
        return Double.compare(moveTime, other.moveTime);
    }
}
