package Colony;

import java.util.HashMap;

public interface AntI {
    public int exponentialDistribution(HashMap<String, Integer> Parameters);
    public void Move();
    public Edge Optimization();
}
