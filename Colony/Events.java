package Colony;
import java.util.*;
// import java.io.*;

abstract class Events implements Comparable<Events> {
    protected int obj=0;
    protected double selfTime;
    Random rand = new Random();
    String type;
    

    public double getTime() {
        
        return selfTime;
    }


    @Override
    public int compareTo(Events other) {
        return Double.compare(this.getTime(), other.getTime());
    }
    public double exponentialDistribution(float lambda) {
        return -Math.log(1 - rand.nextDouble()) / lambda;
    }
}
