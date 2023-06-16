package Colony;

import java.util.ArrayList;

/**
 * Represents a colony of ants.
 */
public class Colony implements ColonyI {
    /**
     * Nest node of the colony
     */
    protected String nest;

    /**
     * The list of ants in the colony.
     */
    protected ArrayList<AntI> ants = new ArrayList<AntI>();

    /**
     * Constructs a Colony object with the specified size and nest node.
     *
     * @param size      The size of the colony (number of ants).
     * @param nestNode  The nest node of the colony.
     */
    public Colony(int size, String nestNode) {
        this.nest = nestNode;

        if (size < 1) {
            System.out.println("Number of ants generated should be at least 1.");
            System.exit(0);
        }

        for (int i = 0; i < size; i++) {
            this.ants.add(new Ant(nestNode));
        }
    }

    /**
     * Returns the size of the colony.
     *
     * @return The size of the colony.
     */
    public int getSize() {
        return ants.size();
    }

    /**
     * Returns the nest node of the colony.
     *
     * @return The nest node of the colony.
     */
    public String getNest() {
        return nest;
    }

    /**
     * Returns the list of ants in the colony.
     *
     * @return The list of ants in the colony.
     */
    public ArrayList<AntI> getAnts() {
        return ants;
    }
}

