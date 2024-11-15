package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class Quicksorter<T> implements Sorter<T> {
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
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
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
    Random rand = new Random();

    sortHelper(values, 0, values.length, rand);

  } // sort(T[])

  /**
   * Recursively sorts T[] values through quicksort.
   *
   * @param values The array of values to sort.
   * @param lb The lower bound of the area to sort (inclusive).
   * @param ub The upper bound of the area to sort (exclusive).
   * @param rand Give a random int.
   */
  public void sortHelper(T[] values, int lb, int ub, Random rand) {
    int pivot;
    if (ub <= lb){
      return;
    } else {
      pivot = rand.nextInt(lb, ub);
    } // if/else

    int[] bounds = dutchNationalFlag(values, lb, ub, pivot);

    if (bounds[0] == -1 || bounds[1] == -1) {
      return;
    } // if
    sortHelper(values, lb, bounds[0], rand );
    sortHelper(values, bounds[1], ub, rand );
  } // sortHelper(T[], int, int, Random)

  /**
   * Sorts values into three different sections, elements less than the
   * element at the pivot index, equal to the pivot element, and greater
   * than the pivot element.
   * 
   * @param values The array of values to sort.
   * @param lb The lower bound of the area to sort (inclusive).
   * @param ub The upper bound of the area to sort (exclusive).
   * @param pivotIndex The index of the pivot.
   * @return An integer array containing the index separating how
   * the area was sorted (less than | equal | greater than).
   */
  public int[] dutchNationalFlag(T[] values, int lb, int ub, int pivotIndex) {
    T pivot = values[pivotIndex];
    
    if (ub - lb <= 1) {
      return new int[] {-1, -1};
    } // if

    int lowerBound = 0;
    int middleBound = 0;
    int upperBound = 0;


    for (int i = 0; i < ub - lb; i++) {
      int comparison = this.order.compare(values[upperBound + lb], pivot);

      if (comparison < 0) {
        swap(values, upperBound + lb, middleBound + lb);
        swap(values, middleBound + lb, lowerBound + lb);
        lowerBound++;
        middleBound++;
        upperBound++;
      } else if (comparison == 0) {
        swap(values, upperBound + lb, middleBound + lb);
        middleBound++;
        upperBound++;
      } else { // comparision > 0
        upperBound++;
      } // if/else-if/else
    } // for

    return new int[] {lowerBound + lb, middleBound + lb};
    
  } // dutchNationalFlag(T[], int, int, Random)

  public void swap(T[] values, int index1, int index2) {
    T placeholderVal = values[index2];
    values[index2] = values[index1];
    values[index1] = placeholderVal;
  }
} // class Quicksorter
