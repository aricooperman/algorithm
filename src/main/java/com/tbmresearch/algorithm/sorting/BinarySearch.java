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

public class BinarySearch {
    
    public static int search( Integer[] ints, int i ) {
        return search( ints, i, 0, ints.length - 1 );
    }

    public static <T extends Comparable<T>> int search( T[] s, T key, int low, int high ) {
        if( low > high ) 
            return -1;
        int middle = (low + high)/2;

        if( s[middle].equals( key ) )
            return middle;

        if( s[middle].compareTo( key ) > 0 )
            return search( s, key, low, middle - 1 );
        else
            return search( s, key, middle + 1, high );
    }
}
