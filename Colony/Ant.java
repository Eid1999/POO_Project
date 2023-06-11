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
		return path.get(path.size()-1);
	}
	
	public ArrayList<String> getPath() {
        return path;
	}
	
	// moved code to antmoveevent
	// public Edge Optimization(WeightedGraph graph, float alpha, float beta, float delta) {

	// rework
	public void Move(Edge edge) {

		if ((edge.getDestination()).equals(path.get(path.size()-1))) {
			this.path.add(edge.getSource());
		}
		
		if ((edge.getSource()).equals(path.get(path.size()-1))) {
			this.path.add(edge.getDestination());
		}
		
	}
	
	// in case of loop next move should be picked uniformly, unneeded?
	public void Back(Edge edge) {
		this.path.remove(path.size()-1);
	}

}
