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
    for (int j = 0; j < values.length; j ++) {

      T nextSmallestVal = values[j];
      int nextSmallestIndex = j;

      // Find the smallest value in T[]
      for (int i = 0; i < values.length; i ++) {
        if (this.order.compare(values[i], nextSmallestVal) < 0) {
          nextSmallestVal = values[i];
          nextSmallestIndex = i;
        } // if
      } // for

      // stablitily
      for (int k = nextSmallestIndex; k > j; k--) {
        // every value between sorted area (exclusive)
        // and smallest val (inclusvie) shift right one.
        values[k] = values[k - 1];
      } // for

      // Put smallest Val in last position of Sorted area
      values[j] = nextSmallestVal;
    } // for
  } // sort(T[])
} // class SelectionSorter
