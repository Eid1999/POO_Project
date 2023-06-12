package Colony;
import java.util.*;
import java.io.*;
class AntMoveEvent extends Events {
	private int ant;
	// private Edge edge;
	private double selfTime;
	Random rand = new Random();
	String type="AntMove";

    public AntMoveEvent(int ant, double currentTime, float alpha, float beta, float delta) {
        
        this.ant = ant;
        
        
        //Edge edge=ant.Optimization(graph, alpha, beta, delta);
        // ant.Move(edge);
	selfTime=exponentialDistribution(delta)+currentTime;
        //selfTime=exponentialDistribution(edge.getWeight() * delta) + currentTime;
        super.UpdateTime(selfTime);
    }
    
    public int get() {
        return ant;
    }
}
