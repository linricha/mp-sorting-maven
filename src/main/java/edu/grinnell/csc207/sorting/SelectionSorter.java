package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using selection sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Richard Lin
 * @author Samuel A. Rebelsky
 */

public class SelectionSorter<T> implements Sorter<T> {
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
  public SelectionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // SelectionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using selection sort.
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
      int nextSmallestIndex = j;

      select(values, nextSmallestIndex);
    } // for
  } // sort(T[])

  /**
   * Selects the smallest element in values from the
   * lower bound lb to the end of values and places that
   * element to the front of the unsorted area, lb.
   *
   * @param values The array of T elements to sort.
   * @param lb The lowerbound of the area that needs sorting.
   */
  private void select(T[] values, int lb) {
    T currentSmallestVal = values[lb];
    int currentSmallestIndex = lb;

    // Find the smallest value in values
    for (int i = lb; i < values.length; i++) {
      if (this.order.compare(values[i], currentSmallestVal) < 0) {
        currentSmallestVal = values[i];
        currentSmallestIndex = i;
      } // if
    } // for

    // Provides stablitily
    for (int k = currentSmallestIndex; k > lb; k--) {
      // every value between sorted area (exclusive)
      // and smallest val (inclusvie) shift right one.
      values[k] = values[k - 1];
    } // for

    // Put smallest Val in last position of Sorted area
    values[lb] = currentSmallestVal;
  } // select()

} // class SelectionSorter
