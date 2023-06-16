package Colony;
import java.util.Random;

/**
 * Abstract class representing an event in the simulation.
 */
abstract class Events implements Comparable<Events> {
    protected int obj = 0;
    protected double selfTime;
    private Random rand = new Random();
    protected String type;

    /**
     * Gets the time of the event.
     *
     * @return The time of the event.
     */
    public double getTime() {
        return selfTime;
    }

    @Override
    public int compareTo(Events other) {
        return Double.compare(this.getTime(), other.getTime());
    }

    /**
     * Generates a random number from an exponential distribution.
     *
     * @param lambda The rate parameter of the exponential distribution.
     * @return The generated random number.
     */
    public double exponentialDistribution(float lambda) {
        return -Math.log(1 - rand.nextDouble()) / lambda;
    }
}
