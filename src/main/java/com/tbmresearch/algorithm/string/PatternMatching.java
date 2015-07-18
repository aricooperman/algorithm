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

package com.tbmresearch.algorithm.string;

public class PatternMatching {

    public static int findMatch( String pattern, String text ) {        
        return findMatch( pattern.toCharArray(), text.toCharArray() );
    }
    
    public static int findMatch( char[] pattern, char[] text ) {
        
        for( int i = 0; i <= (text.length-pattern.length); ++i ) {
            int j = 0;
            while( j < pattern.length && text[i+j] == pattern[j] )
                ++j;
            if( j == pattern.length )
                return i;
        }
        
        return -1;
    }
}
