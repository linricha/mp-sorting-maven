package edu.grinnell.csc207.sorting;
import java.util.Arrays;
import java.util.Comparator;


/**
 * A utility class for performing Timsort on an array of objects with a specified comparator.
 * This class uses a hybrid sorting algorithm that combines Merge Sort and Insertion Sort for
 * efficient sorting of any object type.
 *
 * @param <T> the type of elements to be sorted by this class
 */
public class LinRichardSort<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  /**
   *  Minimum run length for Timsort, typically set to 32.
   */
  private static final int MIN_RUN = 32;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public LinRichardSort(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sorts the specified array using Timsort algorithm.
   *
   * @param array the array to be sorted
   */
  public void sort(T[] array) {
    int n = array.length;

    // Step 1: Sort small subarrays with Insertion Sort
    for (int i = 0; i < n; i += MIN_RUN) {
      insertionSort(array, i, Math.min(i + MIN_RUN - 1, n - 1));
    } // for

    // Step 2: Merge runs into increasingly larger runs
    for (int size = MIN_RUN; size < n; size = 2 * size) {
      for (int left = 0; left < n; left += 2 * size) {
        int mid = left + size - 1;
        int right = Math.min(left + 2 * size - 1, n - 1);
        if (mid < right) {
          merge(array, left, mid, right);
        } // if
      } // for
    } // for
  } // sort(T[])

  /**
   * Sorts a subarray from the specified left index to the right index using Insertion Sort.
   *
   * @param array the array containing the subarray to be sorted
   * @param left  the starting index of the subarray
   * @param right the ending index of the subarray
   */
  private void insertionSort(T[] array, int left, int right) {
    for (int i = left + 1; i <= right; i++) {
      T temp = array[i];
      int j = i - 1;
      while (j >= left && this.order.compare(array[j], temp) > 0) {
        array[j + 1] = array[j];
        j--;
      } // while
      array[j + 1] = temp;
    } // for
  } // insertionSort(T[], int, int)

  /**
   * Merges two consecutive sorted subarrays in place.
   *
   * @param array the array containing the subarrays to be merged
   * @param left  the starting index of the first subarray
   * @param mid   the ending index of the first subarray
   * @param right the ending index of the second subarray
   */
  private void merge(T[] array, int left, int mid, int right) {
    int len1 = mid - left + 1;
    int len2 = right - mid;

    // Create temporary arrays for left and right subarrays
    T[] leftArray = Arrays.copyOfRange(array, left, mid + 1);
    T[] rightArray = Arrays.copyOfRange(array, mid + 1, right + 1);

    int i = 0;
    int j = 0;
    int k = left;

    // Merge the temporary arrays back into the original array
    while (i < len1 && j < len2) {
      if (this.order.compare(leftArray[i], rightArray[j]) <= 0) {
        array[k++] = leftArray[i++];
      } else {
        array[k++] = rightArray[j++];
      } // if/else
    } // while

    // Copy any remaining elements from the left subarray, if any
    while (i < len1) {
      array[k++] = leftArray[i++];
    } // while

    // Copy any remaining elements from the right subarray, if any
    while (j < len2) {
      array[k++] = rightArray[j++];
    } // while
  } // merge(T[], int, int, int)

} // class LinRichardSort