package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * Tests of Sorter objects. Please do not use this class directly.
 * Rather, you should subclass it and initialize stringSorter and
 * intSorter in a static @BeforeAll method.
 *
 * @author Richard Lin
 * @uathor Samuel A. Rebelsky
 */
public class TestSorter {

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  /**
   * The sorter we use to sort arrays of strings.
   */
  static Sorter<String> stringSorter = null;

  /**
   * The sorter we use to sort arrays of integers.
   */
  static Sorter<Integer> intSorter = null;

  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Given a sorted array and a permutation of the array, sort the
   * permutation and assert that it equals the original.
   *
   * @param <T>
   *   The type of values in the array.
   * @param sorted
   *   The sorted array.
   * @param perm
   *   The permuted sorted array.
   * @param sorter
   *   The thing to use to sort.
   */
  public <T> void assertSorts(T[] sorted, T[] perm, Sorter<? super T> sorter) {
    T[] tmp = perm.clone();
    sorter.sort(perm);
    assertArrayEquals(sorted, perm,
      () -> String.format("sort(%s) yields %s rather than %s",
          Arrays.toString(tmp), 
          Arrays.toString(perm), 
          Arrays.toString(sorted)));
  } // assertSorts

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * A fake test. I've forgotten why I've included this here. Probably
   * just to make sure that some test succeeds.
   */
  @Test
  public void fakeTest() {
    assertTrue(true);
  } // fakeTest()

  /**
   * Ensure that an array that is already in order gets sorted correctly.
   */
  @Test
  public void orderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    String[] expected = original.clone();
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that an array that is ordered backwards gets sorted correctly.
   */
  @Test
  public void reverseOrderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "foxtrot", "delta", "charlie", "bravo", "alpha" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that a randomly permuted version of a moderate-sized
   * array sorts correctly.
   */
  @Test 
  public void permutedIntegersTest() { 
    int SIZE = 100; 
    if (null == intSorter) { 
      return; 
    } // if
    Integer[] original = new Integer[SIZE];
    for (int i = 0; i < SIZE; i++) {
      original[i] = i;
    } // for
    Integer[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, intSorter);
  } // permutedIntegers

  /**
   * Tests to see that negative integers are sorted properly
   * amongst other negative integers.
   */
  @Test
  public void sortNegativeInts() {

    if (null == intSorter) {
      return;
    } // if

    Integer[] original = new Integer[100];
    Integer[] sorted = new Integer[100];

    for (int i = 0; i < 100; i++) {
      sorted[i] = i - 100;
      original[i] = i - 100;
    } // for
    ArrayUtils.permute(original);

    assertSorts(sorted, original, intSorter);
  } // sortNegativeInts

  /**
   * Checks to see that all Integers can be sorted
   * by sorting negatives, 0, and positives.
   */
  @Test
  public void sortAllInts() {
    if (null == intSorter) {
      return;
    } // if

    Integer[] original = new Integer[100];
    Integer[] sorted = new Integer[100];

    for (int i = 0; i < 100; i++) {
      sorted[i] = i - 50; 
      original[i] = i - 50;
    } // for
    ArrayUtils.permute(original);

    assertSorts(sorted, original, intSorter);
  } // sortAllInts

  /**
   * Tests that arrays of size one are sorted for ints.
   */
  @Test
  public void sortSizeOneInt() {

    if (null == intSorter) {
      return;
    } // if

    for (int i = -100; i < 100; i++) {

      Integer[] original = {i};
      Integer[] copy = original.clone();
      assertSorts(original, copy, intSorter);

    } // for
  } // sortSizeOne

  /**
   * Tests that arrays of the same element are sorted.
   */
  @Test
  public void sortAllElementsEqual() {
    if (null == intSorter) {
      return;
    } // if

    Integer[] original = new Integer[50];

    for (int i = 0; i < 50; i++) {
      original[i] = 0;
    } // for

    Integer[] sorted = original.clone();

    assertSorts(sorted, original, intSorter);
  } // sortAllElementsEqual

  /**
   * Checks that null string sorted properly.
   */
  @Test
  public void sortNullString() {

    if (null == stringSorter) {
      return;
    } // if

    String[] original = {"", "Hello", "Friend", "", "", "No"};

    String[] sorted = {"", "", "", "Friend", "Hello", "No"};

    assertSorts(sorted, original, stringSorter);
  } // sortNullString

  /**
   * Checks that array of ints of multiple varying sizes can be sorted.
   */
  @Test
  public void checkIntsArraySize() {

    if (null == intSorter) {
      return;
    } // if

    for (int i = 1; i < 100; i++) {
      Integer[] original = new Integer [i];
      
      for (int k = 0; k < original.length; k++) {
        original[k] = k;
      } // for

      Integer[] sorted = original.clone();
      ArrayUtils.permute(original);

      assertSorts(sorted, original, intSorter);
    } // for
  } // checkIntsArraySize



} // class TestSorter
