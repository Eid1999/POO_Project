package Colony;

import java.util.ArrayList;

// import java.util.HashMap;

public interface AntI {
	public ArrayList<String> Move(Edge edge);
    public Edge Optimization(WeightedGraph graph, float alpha, float beta, float delta);
}

