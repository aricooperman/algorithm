package com.tbmresearch.algorithm.sorting;

import static org.junit.Assert.*;

import org.junit.Test;

public class SelectionSortTest {

    @Test
    public void testSortChar() {
        final char[] charArray = "SELECTIONSORT".toCharArray();
        SelectionSort.sort( charArray );
        assertArrayEquals( charArray, "CEEILNOORSSTT".toCharArray() );
    }
}
