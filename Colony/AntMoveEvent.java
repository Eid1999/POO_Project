package Colony;
import java.util.*;
import java.io.*;
class AntMoveEvent extends Events {
    protected int ant;
    protected double selfTime;
    Random rand = new Random();
    String type="AntMove";

    public AntMoveEvent(int ant, double currentTime,float delta) {
        //Edge edge=ant.optimization();
        this.ant = ant;

        selfTime=exponentialDistribution(delta)+currentTime;
        super.UpdateTime(selfTime);
    }
    public int get() {
        return ant;
    }
}
