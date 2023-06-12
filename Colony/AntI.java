package Colony;

import java.util.ArrayList;

// import java.util.HashMap;

public interface AntI {
	public ArrayList<Edge> Move(WeightedGraph graph, Edge edge);
    public Edge Optimization(WeightedGraph graph, float alpha, float beta, float delta);
}

