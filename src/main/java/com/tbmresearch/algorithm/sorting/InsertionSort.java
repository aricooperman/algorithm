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

public class InsertionSort {

    public static void sort( final char[] source ) {
        for( int i = 1; i < source.length; ++i ) {
            int j = i;
            while( j > 0 && source[j] < source[j-1] )
                Swap.swap( source, j, --j );
            
//            System.out.println( String.valueOf( source ) );
        }
    }
}
