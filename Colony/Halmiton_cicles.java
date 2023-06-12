package Colony;

import java.util.ArrayList;

public class Halmiton_cicles implements Comparable<Halmiton_cicles> {
    protected float weight;
    protected ArrayList<String> nodes;

    public Halmiton_cicles(float weight, ArrayList<String> nodes) {
        this.weight = weight;
        this.nodes = nodes;

    }

    public float getWeight() {
        return this.weight;
    }

    public ArrayList<String> getNodes() {
        return this.nodes;
    }

    @Override
    public int compareTo(Halmiton_cicles other) {
        return Double.compare(this.getWeight(), other.getWeight());
    }

}
