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

package com.tbmresearch.algorithm.data.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

import com.tbmresearch.algorithm.util.Swap;

public class PriorityQueue<T extends Comparable<T>> {

    private final T[] queue;
    
    private int n = 0;
    
    
    @SuppressWarnings( "unchecked" )
    public PriorityQueue( int n ) {
        queue = (T[])new Comparable[n];
    }

    public PriorityQueue( T[] s ) {
        this( s.length );
        
        int i;
        n = s.length;
        
        for( i = 0; i < n; i++ ) 
            queue[i] = s[i];
        
        for( i = n-1; i >= 0; i-- ) 
            bubbleDown( i );
    }

    private int parentIndex( int idx ) {
        if( idx == 0 ) 
            return -1;
        else return idx/2;
    }
    
    /* implicitly take floor(n/2) */
    private int youngChildIndex( int idx ) {
        return (2 * idx) + 1;
    }

    public void insert( T x ) {
        if( n >= queue.length )
            Logger.getLogger( PriorityQueue.class.getName() ).severe( "Warning: priority queue overflow insert x = " + x );
        else {
            queue[ n ] = x;
            bubbleUp( n );
            n = n + 1;
        }
    }
    
    private void bubbleUp( int p ) {
        if( parentIndex( p ) == -1 )
            return; /* at root of heap, no parent */
        
        if( queue[parentIndex( p )].compareTo( queue[p] ) > 0 ) {
            Swap.swap( queue, p, parentIndex( p ) );
            bubbleUp( parentIndex( p ) );
        }
    }
    
    public T extractMin() {
        T min = null; /* minimum value */
        if( n <= 0) 
            Logger.getLogger( PriorityQueue.class.getName() ).warning( "Warning: empty priority queue." );
        else {
            min = queue[0];
            queue[0] = queue[ n - 1 ];
            n = n - 1;
            bubbleDown( 0 );
        }
        return min;
    }
    
    public void bubbleDown( int p ) {
        int minIndex; /* index of lightest child */
    
        int c = youngChildIndex( p );  /* child index */
        minIndex = p;
        for( int i= 0; i <= 1; i++ ) {
            int childIdx = c+i;
            if( childIdx < n ) {
                if( queue[minIndex].compareTo( queue[childIdx] ) > 0 )
                    minIndex = childIdx;
            }
        }
        
        if( minIndex != p ) {
            Swap.swap( queue, p, minIndex );
            bubbleDown( minIndex );
        }
    }
    
    public static <T extends Comparable<T>>void heapSort( T[] s ) {
        final PriorityQueue<T> q = new PriorityQueue<T>( s );
        for( int i = 0; i < s.length; i++ )
            s[i] = q.extractMin();
    }
    
    private int countGreaterThanOrEqual( T x ) {
        return heapCompare( 0, n, x );
    }
    
    private int heapCompare( int i, int count, T x ) {
        if( count <= 0 || i >= n )
            return count;
        
        if( queue[i].compareTo( x ) < 0 ) {
            count = heapCompare( youngChildIndex( i ), count-1, x );
            count = heapCompare( youngChildIndex( i ) + 1, count, x );
        }
        
        return count;
    }
    
    public static void main( String[] args ) {
        final java.util.List<Integer> intList = new ArrayList<>();
        final Random random = new Random( System.currentTimeMillis() );
        for( int i = 0; i < 10; ++i )
            intList.add( random.nextInt( 100 ) );
        
        Integer[] input = intList.toArray( new Integer[intList.size()] );
        
        System.out.println( Arrays.toString( input ) );
        
        final PriorityQueue<Integer> q = new PriorityQueue<>( input );
        System.out.println( "Heap compare " + (input[5] +1) + " = " + q.countGreaterThanOrEqual( input[5] + 1 ) );
        
        heapSort( input );
        
        System.out.println( Arrays.toString( input ) );
    }

}
