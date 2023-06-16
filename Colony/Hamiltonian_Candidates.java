package Colony;

import java.util.ArrayList;

public class Hamiltonian_Candidates implements Comparable<Hamiltonian_Candidates> {
    protected float weight;
    protected ArrayList<String> nodes;

    public Hamiltonian_Candidates(float weight, ArrayList<String> nodes) {
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
    public int compareTo(Hamiltonian_Candidates other) {
        return Double.compare(this.getWeight(), other.getWeight());
    }

}
