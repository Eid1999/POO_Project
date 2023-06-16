package Colony;

import java.util.ArrayList;

/**
 * Represents a Hamiltonian candidate, which includes a weight and a list of nodes.
 */
public class Hamiltonian_Candidates implements Comparable<Hamiltonian_Candidates> {
    protected float weight;
    protected ArrayList<String> nodes;

    /**
     * Constructs a Hamiltonian candidate with the given weight and nodes.
     *
     * @param weight The weight of the candidate.
     * @param nodes  The list of nodes in the candidate.
     */
    public Hamiltonian_Candidates(float weight, ArrayList<String> nodes) {
        this.weight = weight;
        this.nodes = nodes;
    }

    /**
     * Gets the weight of the Hamiltonian candidate.
     *
     * @return The weight of the candidate.
     */
    public float getWeight() {
        return this.weight;
    }

    /**
     * Gets the nodes in the Hamiltonian candidate.
     *
     * @return The list of nodes in the candidate.
     */
    public ArrayList<String> getNodes() {
        return this.nodes;
    }

    @Override
    public int compareTo(Hamiltonian_Candidates other) {
        return Float.compare(this.getWeight(), other.getWeight());
    }
}
