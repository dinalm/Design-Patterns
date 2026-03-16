/**
 * ShellSortStrategy - Concrete strategy implementing Shell Sort.
 *
 * A generalisation of Insertion Sort. Compares elements that are "gap"
 * positions apart and shrinks the gap each pass until gap = 1 (plain
 * Insertion Sort on a nearly-sorted array). Uses Knuth's gap sequence:
 * 1, 4, 13, 40, 121, ...
 * Time complexity: approximately O(n^1.5) with Knuth's sequence.
 *
 * Reference: GeeksforGeeks Shell Sort
 * https://www.geeksforgeeks.org/shellsort/
 */
public class ShellSortStrategy implements SortingStrategy {

    @Override
    public void sort(int[] array) {
        if (array == null || array.length <= 1) return;
        int n = array.length;

        int gap = 1;
        while (gap < n / 3) gap = gap * 3 + 1;

        while (gap >= 1) {
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j = i;
                while (j >= gap && array[j - gap] > temp) {
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = temp;
            }
            gap /= 3;
        }
    }

    @Override
    public String getName() { return "Shell Sort"; }
}