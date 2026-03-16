import java.util.Random;

/**
 * Main - Entry point for the Algorithm Performance Comparison.
 *
 * Creates two data sets (small and large), sorts each with all three
 * strategies, and prints the measured time for every combination.
 */
public class Main {

    private static final int  SMALL_SIZE = 30;
    private static final int  LARGE_SIZE = 100_000;
    private static final long SEED       = 42L;

    public static void main(String[] args) {

        SortingStrategy[] strategies = {
                new MergeSortStrategy(),
                new HeapSortStrategy(),
                new ShellSortStrategy()
        };

        Sorter sorter = new Sorter(strategies[0]);

        System.out.println("=".repeat(60));
        System.out.println("  Algorithm Performance Comparison (Strategy Pattern)");
        System.out.println("=".repeat(60));

        runComparison(sorter, strategies, SMALL_SIZE, "SMALL");
        runComparison(sorter, strategies, LARGE_SIZE, "LARGE");

        System.out.println("=".repeat(60));
        System.out.println("Done.");
    }

    private static void runComparison(Sorter sorter, SortingStrategy[] strategies,
                                      int size, String label) {

        System.out.printf("%n--- %s data set (%,d elements) ---%n", label, size);
        System.out.printf("%-20s  %15s  %12s%n", "Algorithm", "Time (ns)", "Time (ms)");
        System.out.println("-".repeat(52));

        int[] baseData = generateRandomArray(size, SEED);

        for (SortingStrategy strategy : strategies) {
            int[] dataCopy = copyArray(baseData);
            sorter.setStrategy(strategy);
            long ns = sorter.sortAndMeasure(dataCopy);
            System.out.printf("%-20s  %,15d  %12.3f%n",
                    strategy.getName(), ns, ns / 1_000_000.0);

            if (!isSorted(dataCopy))
                System.out.println("  *** WARNING: array not sorted correctly! ***");
        }
    }

    private static int[] generateRandomArray(int size, long seed) {
        Random rng = new Random(seed);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) array[i] = rng.nextInt(1_000_000);
        return array;
    }

    private static int[] copyArray(int[] source) {
        int[] copy = new int[source.length];
        System.arraycopy(source, 0, copy, 0, source.length);
        return copy;
    }

    private static boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++)
            if (array[i] < array[i - 1]) return false;
        return true;
    }
}