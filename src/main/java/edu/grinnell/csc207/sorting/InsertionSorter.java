package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using insertion sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class InsertionSorter<T> implements Sorter<T> {
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
  public InsertionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using insertion sort.
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
    // Marks the area of sorted vs unsorted with j
    for (int j = 0; j < values.length; j++) {
      int nextValtoSortIndex = j;

      insert(values, nextValtoSortIndex);

    } // for
  } // sort(T[])

  /**
   * Inserts the element located at insertedValIndex in values
   * into its correctly sorted place in the sorted area, the area/indices
   * to the right/less than insertedValIndex.
   *
   * @param values The values to be sorted.
   * @param insertedValIndex The index of the value to be inserted.
   */
  private void insert(T[] values, int insertedValIndex) {
    T insertedVal = values[insertedValIndex];

    // Moves insertedValIndex to the appropriate location in the sorted area
    for (int i = insertedValIndex - 1; i >= 0; i--) {
      // if nextVal is smaller than values[i]
      if (this.order.compare(insertedVal, values[i]) < 0) {
        // swap elements
        values[i + 1] = values[i];
        values[i] = insertedVal;
      } // if
    } // for
  } // insert(T[], int)
} // class InsertionSorter
