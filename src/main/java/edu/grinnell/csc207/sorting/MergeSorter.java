package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class MergeSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    T[] prevSorted = values.clone();
    boolean checkedRemaining = false;

    // Sorts in groups of length i or less, eventually having the entire array sorted.
    for (int i = 1; i < values.length || checkedRemaining; i *= 2) {
      int totalGroups = (values.length - values.length % i) / i;

      int groupWidth = i;
      int remainingGroupWidth = values.length - totalGroups * i;

      // Sorts pairs of groups that can be made
      for (int groupNum = 0; groupNum < totalGroups - 1; groupNum += 2) {
        int groupIndex1 = groupNum * groupWidth;
        int groupIndex2 = groupIndex1 + groupWidth;
        merge(prevSorted, values, groupIndex1, groupWidth, groupIndex2, groupWidth);
      } // for

      // When have enough for two groups of not complete size.
      // Makes sure that any sized array unsorted returns sorted with same size and elements.
      if (totalGroups % 2 != 0 && remainingGroupWidth != 0) {
        int lastGroupIndex = (totalGroups - 1) * groupWidth;
        int remainingGroupIndex = totalGroups * groupWidth;
        merge(prevSorted, values, lastGroupIndex,
            groupWidth, remainingGroupIndex, remainingGroupWidth);
        checkedRemaining = true;
      } // if

      // Ensure that we sorted and compared the entire array, since
      // values.length can be a non even number.
      if (!(i < values.length) && i < 2 * values.length) {
        checkedRemaining = true;
      } else {
        checkedRemaining = false;
      } // if/else

      prevSorted = values.clone(); // ? Questionable. But need to update values.
    } // for

  } // sort(T[])

  /**
   * Give two areas in an area of values to be sorted, where each area is already
   * sorted and where the start index of v1 is before v2, sort the values in both
   * areas and store that in the array sorted.
   *
   * @param values The array of values to sort.
   * @param sorted An array to store the sorted values.
   * @param v1StartIndex the start index of the first area to sort in values.
   * @param v1Length the length of the first area to sort in values.
   * @param v2StartIndex the start index of the second area to sort in values.
   * @param v2Length the length of the second area to sort in values.
   */
  public void merge(
      T[] values, T[] sorted, int v1StartIndex, int v1Length, int v2StartIndex, int v2Length) {

    int v1 = 0;
    int v2 = 0;

    while (v1 + v2 < v1Length + v2Length) {
      // v1 <= v2 (stability)
      if (v1 < v1Length && v2 < v2Length) {
        if ((this.order.compare(values[v1 + v1StartIndex], values[v2 + v2StartIndex]) <= 0)) {
          sorted[v1 + v2 + v1StartIndex] = values[v1 + v1StartIndex];
          v1++;
        } else if ((this.order.compare(values[v1 + v1StartIndex], values[v2 + v2StartIndex]) > 0)) {
          sorted[v1 + v2 + v1StartIndex] = values[v2 + v2StartIndex];
          v2++;
        } // if/else-if
      } else if (v2 == v2Length) {
        sorted[v1 + v2 + v1StartIndex] = values[v1 + v1StartIndex];
        v1++;
      } else if (v1 == v1Length) {
        sorted[v1 + v2 + v1StartIndex] = values[v2 + v2StartIndex];
        v2++;
      } // if/else-if/else-if
    } // while
  } // compare(T[], T[], int, int, int, int)
} // class MergeSorter
