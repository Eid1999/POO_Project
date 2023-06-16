package Colony;

import java.util.ArrayList;

/**
 * Represents a colony interface.
 */
public interface ColonyI {
    /**
     * Gets the size of the colony.
     *
     * @return The size of the colony.
     */
    public int getSize();

    /**
     * Gets the nest of the colony.
     *
     * @return The nest of the colony.
     */
    public String getNest();

    /**
     * Gets the ants in the colony.
     *
     * @return The list of ants in the colony.
     */
    public ArrayList<AntI> getAnts();
}