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


package com.tbmresearch.algorithm.math;

import java.util.logging.Logger;

public class FastExponentiation {

    public static double power( double a, int n ) {
        Logger.getAnonymousLogger().finer( String.format( "a = %f, n = %d", a, n ) );
        
        if( n == 0 ) 
            return 1;
        
        final double x = power( a, n/2 );
        if( n % 2 == 0 )
            return x*x;
        else 
            return a * x * x;
    }
}
