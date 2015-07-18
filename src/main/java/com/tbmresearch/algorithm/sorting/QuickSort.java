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

public class QuickSort {
    
    public static <T extends Comparable<T>> void quickSort( final T[] source, final int low, final int high ) {
        if( (high - low) > 0 ) {
            int p = quickPartition( source, low, high );
            
            System.out.format( "p = %d%n", p );
            
            quickSort( source, low, p-1 );
            quickSort( source, p+1, high );
        }
    }
    
    public static void quickSort( char[] source, int low, int high ) {
        if( (high - low) > 0 ) {
            int p = quickPartition( source, low, high );
            
            System.out.format( "p = %d%n", p );
            
            quickSort( source, low, p-1 );
            quickSort( source, p+1, high );
        }
    }
    
    private static <T extends Comparable<T>> int quickPartition( final T[] source, final int l, final int h ) {
        int i;     /* counter */
        int p;     /* pivot element index */
        int firsthigh; /* divider position for pivot element */
        p = h;
        firsthigh = l;
        
        for( i=l; i<h; i++ ) {
            if( source[i].compareTo( source[p] ) < 0 ) {
                Swap.swap( source, i, firsthigh );
                firsthigh ++;
            }
        }
        
        Swap.swap( source, p, firsthigh );
        
        return firsthigh;
    }
    
    private static int quickPartition( char[] s, int l, int h ) {
        int i;     /* counter */
        int p;     /* pivot element index */
        int firsthigh; /* divider position for pivot element */
        p = h;
        firsthigh = l;
        for( i=l; i<h; i++ )
            if( s[i] < s[p] ) {
                Swap.swap( s, i, firsthigh );
                firsthigh ++;
            }
        Swap.swap( s, p, firsthigh );
        
        return firsthigh;
    }
}
