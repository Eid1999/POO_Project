package Colony;

import java.util.*;

public class Ant {
	private ArrayList<String> path = new ArrayList<String>();
	// private double time; // unused
	
	// Random rand = new Random(); // rem
	
	// constructor
	Ant(String nest) {
		this.path.add(nest);
		// this.time = 0.00000;
	}

	// methods (incomplete):

	public String getPosition() {
		return path.get(path.size()-1);
	}
	
	public ArrayList<String> getPath() {
        return path;
	}
	
	//this.getNeighbors(vertex).size()
	public Edge Optimisation(WeightedGraph graph) {
		double ckSum = 0;
		List <String> adjacent = graph.getNeighbors(path.get(path.size()-1));
		for (int i = 0; i < adjacent.size(); i++) {
			ckSum += 0;
		}

		// adj = graph.getAdjancyMap();
		Edge nextedge = new Edge("1","2",3); // remove
		// calc probabilities for all adjacent edges
		// ck = ((alpha + edge.phermoneLevel)/(beta + edge.weight))
		// ci = sum of ck (adjacent nodes)
		// prob = ck / ci;
		// roll dice and pick next edge
		return nextedge;
	}

	// public double getTime() {
	// 	return time;
	// }
	
	//Edge next = Optimisation();
	public void Move(Edge edge) {
		double delta = 0.5; // remove (global? or arg?)
		
		if ((edge.getDestination()).equals(path.get(path.size()-1))) {
			this.path.add(edge.getSource());
		}
		
		if ((edge.getSource()).equals(path.get(path.size()-1))) {
			this.path.add(edge.getDestination());
		}

		// this.time += exp((delta * edge.getWeight())); // delta from command lines params
		
	}
	
	// if required on top
	public void Back(Edge edge) {
		double delta = 0.5; // remove (global?)
		this.path.remove(path.size()-1);
		// this.time -= exp((delta * edge.getWeight()));
	}

	// copy rand exp from events
    // public double exp(double lambda) {
    //     return -Math.log(1 - rand.nextDouble()) / lambda;
    // }
}
