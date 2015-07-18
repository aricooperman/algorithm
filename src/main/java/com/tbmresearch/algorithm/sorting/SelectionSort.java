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

import com.tbmresearch.algorithm.util.Swap;

public class SelectionSort {

    public static void sort( final int[] source ) {
        int n = source.length;
        for( int i = 0; i < n; ++i ) {
            int min = i;
            for( int j = i+1; j < n; ++j ) {
                if( source[j] < source[min] )
                    min = j;
            }
            Swap.swap( source, i, min );
        }
    }
    
    public static void sort( final char[] source ) {
        int n = source.length;
        for( int i = 0; i < n; ++i ) {
            int min = i;
            for( int j = i+1; j < n; ++j ) {
                if( source[j] < source[min] )
                    min = j;
            }
            Swap.swap( source, i, min );
            
//            System.out.println( String.valueOf( source ) );
        }
    }
   
    public static <T extends Comparable<T>> void sort( final T[] source ) {
        int n = source.length;
        for( int i = 0; i < n; ++i ) {
            int min = i;
            for( int j = i+1; j < n; ++j ) {
                if( source[j].compareTo( source[min] ) < 0 )
                    min = j;
            }
            Swap.swap( source, i, min );
        }
    }
}
