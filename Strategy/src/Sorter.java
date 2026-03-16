/**
 * Sorter - Context class in the Strategy pattern.
 *
 * Holds a SortingStrategy and delegates sorting to it.
 * The strategy can be swapped at runtime via setStrategy().
 */
public class Sorter {

    private SortingStrategy strategy;

    public Sorter(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public String getStrategyName() {
        return strategy.getName();
    }

    /** Sorts the array in-place and returns elapsed time in nanoseconds. */
    public long sortAndMeasure(int[] array) {
        long start  = System.nanoTime();
        strategy.sort(array);
        return System.nanoTime() - start;
    }
}