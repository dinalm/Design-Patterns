/**
 * HeapSortStrategy - Concrete strategy implementing Heap Sort.
 *
 * Phase 1: Build a max-heap from the array.
 * Phase 2: Repeatedly move the root (largest value) to the end
 *          and re-heapify the remaining elements.
 * Time complexity: O(n log n) in all cases. In-place, O(1) extra space.
 *
 * Reference: GeeksforGeeks Heap Sort
 * https://www.geeksforgeeks.org/heap-sort/
 */
public class HeapSortStrategy implements SortingStrategy {

    @Override
    public void sort(int[] array) {
        if (array == null || array.length <= 1) return;
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i);

        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }
    }

    private void heapify(int[] array, int heapSize, int i) {
        int largest = i;
        int left    = 2 * i + 1;
        int right   = 2 * i + 2;

        if (left  < heapSize && array[left]  > array[largest]) largest = left;
        if (right < heapSize && array[right] > array[largest]) largest = right;

        if (largest != i) {
            swap(array, i, largest);
            heapify(array, heapSize, largest);
        }
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a]; array[a] = array[b]; array[b] = temp;
    }

    @Override
    public String getName() { return "Heap Sort"; }
}