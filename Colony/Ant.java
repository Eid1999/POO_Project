package Colony;

import java.util.*;

public class Ant implements AntI {
	private ArrayList<String> path = new ArrayList<String>();
	
	// constructor
	Ant(String nest) {
		this.path.add(nest);
	}

	// methods
	
	public String getPosition() {
		return this.path.get(path.size()-1);
	}
	
	public ArrayList<String> getPath() {
        return this.path;
	}
	
	// subset of adjacent
	public ArrayList<String> getUnvisited (List<String> nodelst) {
		 ArrayList<String> unvisited = new ArrayList<String>();
		 for (int i = 0; i < nodelst.size(); i++) {
		   if (!(this.path.contains(nodelst.get(i)))) {
			   unvisited.add(nodelst.get(i));
		   }
		 }
		 return unvisited;
	}
	
	//
	public ArrayList<Edge> getPathAsEdges(WeightedGraph graph, ArrayList<String> nodes) {
		ArrayList<Edge> edgelist = new ArrayList<Edge>();
		for (int i = 1; i < nodes.size(); i++) {
			edgelist.add(graph.getEdge(nodes.get(i-1), nodes.get(i)));
		}
		return edgelist;
	}
	
	// replace back
	public void preventLoop (String checkmark) {
		for (int i = this.path.indexOf(checkmark); i < this.path.size(); i++) {
			this.path.remove(i);
		}
	}
	
	// rework
	public ArrayList<String> Move(Edge edge) {
		String aux;
		ArrayList<String> hamilton = new ArrayList<String>();
		if (edge.equals(null)) {
			aux = this.getPosition();
			hamilton = this.getPath();
			this.path.clear();
			this.path.add(aux);
			return hamilton;
		}
		else {
			// comment out ifs
			if ((edge.getDestination()).equals(path.get(path.size()-1))) {
				this.path.add(edge.getSource());
			}
			if ((edge.getSource()).equals(path.get(path.size()-1))) {
				this.path.add(edge.getDestination());
			}
			return null;
		}

	}
	
	//
	public Edge Optimization(WeightedGraph graph, float alpha, float beta, float delta) {
		Edge nextedge = null;
		double p = 0.00;
		double sum = 0.00;
		double cumulativeProbability = 0.00;
		ArrayList<String> unvis = new ArrayList<String>();
		ArrayList<Double> prob = new ArrayList<Double>();
		List<String> adj = new ArrayList<String>();
		adj = graph.getNeighbors(this.getPosition());
		unvis = this.getUnvisited(adj);
		if (unvis.isEmpty()) {
			if (this.getPosition().equals(this.path.get(0))) {
				// String aux = this.path.get(0);
				// this.path.clear(); // removeAll ?
				// nextedge = graph.getEdge(this.getPosition(), aux);
				nextedge = null;
				// HERE: trigger pheromone release, need to keep path!!!
				return nextedge;
			}
			else {
				for (int i = 0; i < adj.size(); i++) {
					prob.add( 1.0/ (float) (adj.size()));
				}
				p = Math.random();
				for (int i = 0; i <= adj.size(); i++) {
				    cumulativeProbability += Double.valueOf(adj.get(i));
				    if (p <= cumulativeProbability) {
				    	this.preventLoop(adj.get(i)); //
				    	nextedge = graph.getEdge(this.getPosition(), adj.get(i));
				    	return nextedge;
				    }
				}
			}
		}
		else {
			for (int i = 0; i < adj.size(); i++) {
				nextedge = graph.getEdge(this.getPosition(), adj.get(i));
				prob.add((alpha + nextedge.getPheromone())
						/ (beta + nextedge.getWeight()));
				sum += Double.valueOf(adj.get(i));
			}
			p = Math.random();
			for (int i = 0; i <= adj.size(); i++) {
			    cumulativeProbability += Double.valueOf(adj.get(i)) / sum;
			    if (p <= cumulativeProbability) {
			    	nextedge = graph.getEdge(this.getPosition(), adj.get(i));
			    	return nextedge;
			    }
			}
		}
		return nextedge; // required out of else
	}
}
