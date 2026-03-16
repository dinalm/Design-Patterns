/**
 * MergeSortStrategy - Concrete strategy implementing Merge Sort.
 *
 * Divides the array in half recursively, sorts each half, then merges
 * the two sorted halves back together.
 * Time complexity: O(n log n) in all cases.
 *
 * Reference: GeeksforGeeks Merge Sort
 * https://www.geeksforgeeks.org/merge-sort/
 */
public class MergeSortStrategy implements SortingStrategy {

    @Override
    public void sort(int[] array) {
        if (array == null || array.length <= 1) return;
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];

        System.arraycopy(array, left, leftArr, 0, leftSize);
        System.arraycopy(array, mid + 1, rightArr, 0, rightSize);

        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArr[i] <= rightArr[j]) array[k++] = leftArr[i++];
            else                           array[k++] = rightArr[j++];
        }
        while (i < leftSize)  array[k++] = leftArr[i++];
        while (j < rightSize) array[k++] = rightArr[j++];
    }

    @Override
    public String getName() { return "Merge Sort"; }
}