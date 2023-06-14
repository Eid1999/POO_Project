package Colony;

import java.util.*;

public class Ant implements AntI {
	private ArrayList<String> path = new ArrayList<String>();

	// constructor
	Ant(String nest) {
		path.add(nest);
	}

	// methods

	// gets current position from path
	public String getPosition() {
		int sz = path.size();
		return path.get(sz - 1);
	}

	// gets path (list of visited nodes as strings)
	public ArrayList<String> getPath() {
		return path;
	}

	// returns a list that only contains the unvisited nodes
	// from a given set of nodes
	public ArrayList<String> getUnvisited(List<String> nodelst) {
		ArrayList<String> unvisited = new ArrayList<String>();
		for (int i = 0; i < nodelst.size(); i++) {
			if (!(path.contains(nodelst.get(i)))) {
				unvisited.add(nodelst.get(i));
			}
		}
		return unvisited;
	}

	// releases pheromones in every edge of the path upon completing the cycle
	// and also resets the ant's path in order to start a new search.
	// also returns the completed cycle as a <directed> edge list
	public void releasePheromones(WeightedGraph graph, double phero, PriorityQueue<Events> events, double currentTime,
			float eta) {
		String aux = getPosition();
		for (int i = 1; i < path.size(); i++) {
			graph.updatePheromones(path.get(i - 1), path.get(i), phero);
			Edge edge = graph.getEdge(path.get(i - 1), path.get(i - 1));
			if (edge.getPheromone() != 0) {
				events.add(new PheromoneEVEvent(edge, currentTime, eta, graph));
			}
		}
		path.clear();
		path.add(aux);
	}

	// removes a portion of the path in order to prevent loops
	public void preventLoop(String checkmark) {
		for (int i = path.indexOf(checkmark); i < path.size(); i++) {
			path.remove(i);
		}
	}

	// move ant along edge and update path array accordingly
	public void Move(Edge edge) {
		int sz = path.size();
		if ((edge.getDestination()).equals(path.get(sz - 1))) {
			path.add(edge.getSource());
		}
		if ((edge.getSource()).equals(path.get(sz - 1))) {
			path.add(edge.getDestination());
		}
	}

	// picks next edge to move to
	public Edge Optimization(WeightedGraph graph, float alpha, float beta, float delta) {
		double p = Math.random(); // random roll (double between 0 and 1)
		double sum = 0.00; // used as the sum of all probabilities
		double cumulativeProbability = 0.00; // used for the random roll
		Edge nextedge = null; // next position (return)
		Edge possedge = null; // auxiliary
		ArrayList<String> unvis = new ArrayList<String>(); // unvisited nodes
		ArrayList<Double> prob = new ArrayList<Double>(); // probability of each node
		List<String> adj = new ArrayList<String>(); // adjacent nodes
		adj = graph.getNeighbors("v"+getPosition());
		unvis = getUnvisited(adj);

		// case where all adjacent nodes have been visited
		if (unvis.isEmpty()) {
			// calculate (uniform) probability for each node
			for (int i = 0; i < adj.size(); i++) {
				prob.add(1.0 / ((double) (adj.size())));
			}
			// pick path by summing probabilities in list
			for (int i = 0; i <= adj.size(); i++) {
				cumulativeProbability += prob.get(i);
				if (p <= cumulativeProbability) {
					preventLoop(adj.get(i)); // prevents unwanted loop
					nextedge = graph.getEdge("v"+getPosition(), adj.get(i));
					break;
				}
			}
		}
		// case where there's unvisited adjacent nodes
		else {
			// calculate prpossedgeobability for each node (parameter/weight/pheromone based)
			for (int i = 0; i < adj.size(); i++) {
				possedge = graph.getEdge("v"+getPosition(), adj.get(i));
				prob.add((alpha + possedge.getPheromone())
						/ (beta + possedge.getWeight()));
				sum += prob.get(i); // increment sum for later calculation
			}
			// divide each member by sum for correct probability
			for (int i = 0; i <= adj.size(); i++) {
				cumulativeProbability += prob.get(i) / sum;
				if (p <= cumulativeProbability) {
					nextedge = graph.getEdge("v"+getPosition(), adj.get(i));
					break;
				}
			}
		}
		return nextedge;
	}
}
