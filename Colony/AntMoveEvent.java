package Colony;
import java.util.*;
// import java.io.*;
class AntMoveEvent extends Events {
	private Ant ant;
	// private Edge edge;
	private double selfTime;
	Random rand = new Random();
	String type="AntMove";
    private Edge edge;

    public AntMoveEvent(WeightedGraph graph,Ant ant, double currentTime, float alpha, float beta, float delta) {
        
        this.ant = ant;
        
        this.edge=ant.Optimization(graph,alpha,beta,delta);
	    selfTime=exponentialDistribution(delta)+currentTime;
        super.UpdateTime(selfTime);
    }
    
    public Ant get() {
        ant.Move(edge);
        return ant;
    }
}
