package com.tbmresearch.algorithm.sorting;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTest {

    @Test
    public final void testSearch() {
        final Integer[] ints = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        
        assertEquals( "Binary search failed to located 5th value", ints[5], ints[BinarySearch.search( ints, 5 )] );
        
        assertEquals( "Binary search failed to located 5th value", -1, BinarySearch.search( ints, 10 ) );
    }

}
