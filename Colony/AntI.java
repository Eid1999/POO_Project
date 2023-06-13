package Colony;

public interface AntI {
	public void Move(Edge edge);
    public Edge Optimization(WeightedGraph graph, float alpha, float beta, float delta);
}

