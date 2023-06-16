package Colony;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public interface AntI {
	public String getPosition();
	public ArrayList<String> getPath();
	public ArrayList<String> getUnvisited(List<String> nodelst);
	public void releasePheromones(Graph<String,Edge> graph, double phero, PriorityQueue<Events> events, double currentTime,
			float eta);
	public void preventLoop(int checkmark);
	public void Move(Edge edge);
    public Edge Optimization(Graph<String,Edge> graph, float alpha, float beta, float delta);
}