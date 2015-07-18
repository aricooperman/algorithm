package com.tbmresearch.algorithm.sorting;

import static org.junit.Assert.*;

import org.junit.Test;

public class InsertionSortTest {

    @Test
    public final void testSortChar() {
        final char[] charArray = "INSERTIONSORT".toCharArray();
        InsertionSort.sort( charArray );
        assertArrayEquals( charArray, "EIINNOORRSSTT".toCharArray() );
    }

}
