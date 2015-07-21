/*

Reproduced from Steven Skiena's The Algorithm Design Manual

------------------------------------------------------------
 
Copyright 2003 by Steven S. Skiena; all rights reserved. 

Permission is granted for use in non-commerical applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/

package com.tbmresearch.algorithm.sorting;

import com.tbmresearch.algorithm.data.structure.Queue;

public class MergeSort {
    
    public static <T extends Comparable<T>> void sort( T[] s ) {
        sortSkiena( s );
    }
    
    public static <T extends Comparable<T>> void sortSkiena( T[] s ) {
        sortSkiena( s, 0, s.length - 1 );
    }

    private static <T extends Comparable<T>> void sortSkiena( T[] s, int low, int high ) {
        if( low < high ) {
            final int middle = (low+high)/2;
            sortSkiena( s, low, middle );
            sortSkiena( s, middle + 1, high );
            merge(s, low, middle, high);
        }
    }
    
    private static <T extends Comparable<T>> void merge( T[] s, int low, int middle, int high ) {
        int i;
        final Queue<T> buffer1 = new Queue<>(), buffer2 = new Queue<>(); /* buffers to hold elements for merging */
        for( i = low; i <= middle; i++ ) 
            buffer1.enqueue( s[i] );
        
        for( i = middle + 1; i <= high; i++ ) 
            buffer2.enqueue( s[i] );

        i = low;

        while( !(buffer1.isEmpty() || buffer2.isEmpty() ) ) {
            if( buffer1.head() <= buffer2.head() )
                s[i++] = buffer1.dequeue();
            else
                s[i++] = buffer2.dequeue();
        }
        
        while( !buffer1.isEmpty())
            s[i++] = buffer1.dequeue();
        
        while( !buffer2.isEmpty()) 
            s[i++] = buffer2.dequeue();
    }
    
    
    public static void main( String[] args ) {
        // TODO Auto-generated method stub

    }

}
