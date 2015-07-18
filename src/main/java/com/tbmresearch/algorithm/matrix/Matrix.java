/*
  
Reproduced from Steven Skiena's The Algorithm Design Manual

------------------------------------------------------------
  
Copyright 2005 by Steven S. Skiena; all rights reserved. 

Permission is granted for use in non-commerical applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/


package com.tbmresearch.algorithm.matrix;

import java.util.Arrays;

public class Matrix {

//    private static final int MAXV = 100;     /* maximum number of vertices */
//    private static final int MAXDEGREE = 50;      /* maximum outdegree of a vertex */

//    #define MAXINT  100007

    private final int m[][];      /* adjacency/weight info */
//    private final int rows;           /* number of rows */
//    private final int columns;            /* number of columns */


    public Matrix( int rows, int columns ) {
        m = new int[rows][columns];
    }
    
    public Matrix( int rows, int columns, int initialVal ) {
        this( rows, columns );
        fill( initialVal );
    }

    public void fill( int initialVal ) {
        for( int i = 0; i < m.length; i++ ) {
            for( int j = 0; j < m[i].length; j++ )
                m[i][j] = initialVal;
        }
    }

//    read_matrix(matrix *m)
//{
//    int i,j;            /* counters */
//
//
//    scanf("%d %d\n",&(m->rows),&(m->columns));
//
//    for (i=1; i<=m->rows; i++) {
//        for (j=1; j<=m->columns; j++) 
//            scanf("%d",&m->m[i][j]);
//    }
//}


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for( int i=0; i < m.length; i++ ) {
            for( int j=0; j < m[i].length; j++ )
                sb.append( " " ).append( m[i][j] );
            sb.append( "\n" );
        }
        return sb.toString();
    }

    public Matrix multiply( Matrix other ) {
        return multiply( this, other );
    }

    public static Matrix multiply( Matrix a, Matrix b ) {
        if( a.m[0].length != b.m.length )
            throw new IllegalArgumentException( "Error: bounds dont match!" );
    
        final Matrix c = new Matrix( a.m.length, b.m[0].length );
    
        for( int i = 0; i < a.m.length; i++ ) {
            for( int j = 0; j < b.m[0].length; j++ ) {
                for( int k = 0; k < b.m.length; k++ )
                    c.m[i][j] += a.m[i][k] * b.m[k][j];
            }
        }
        
        return c;
    }
    
    
    public static void main( String[] args ) {
        Matrix a = new Matrix( 10, 5, 1 ), b = new Matrix( 5, 20, 2 );
        
//        read_matrix(&a);
        System.out.println( a );
//    
//            read_matrix(&b);
        System.out.println( b );
    
        Matrix c = multiply( a, b );
    
        System.out.println( c );
    
    }
}
