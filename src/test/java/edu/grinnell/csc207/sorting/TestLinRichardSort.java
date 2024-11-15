package edu.grinnell.csc207.sorting;

import org.junit.jupiter.api.BeforeAll;

/**
 * Tests of our MergeSorter.
 */
public class TestLinRichardSort extends TestSorter {
  /**
   * Set up the sorters.
   */
  @BeforeAll
  static void setup() {
    stringSorter = new LinRichardSort<String>((x,y) -> x.compareTo(y));
    intSorter = new LinRichardSort<Integer>((x,y) -> x.compareTo(y));
  } // setup()

} // class TestMergeSorter
