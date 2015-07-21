/*
 * Adapted from topcoder article https://www.topcoder.com/community/data-science/data-science-tutorials/mathematics-for-topcoders/
 */

package com.tbmresearch.algorithm.math;

public class Fraction {
    
    private final int numerator;
    private final int denominator;
    
    private Fraction( int n, int d ) {
        numerator = n;
        denominator = d;
    }
    
    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    @Override
    public String toString() {
        return String.format( "%d / %d", numerator, denominator );
    }

    public static Fraction of( int numerator, int denominator ) {
        assert denominator != 0;
        
        return new Fraction( numerator, denominator );
    }
    
    public static Fraction multiply( Fraction a, Fraction b ) {
       return Fraction.of( a.numerator*b.numerator, a.denominator*b.denominator );
    }
    
    public Fraction multiply( Fraction other ) {
        return multiply( this, other );
    }
    
    public static Fraction add( Fraction a, Fraction b ) {
       final int denom = MathUtilities.lowestCommonMultiple( a.denominator, b.denominator );
       return Fraction.of( denom/a.denominator*a.numerator + denom/b.denominator*b.numerator, denom );
    }
    
    public Fraction add( Fraction other ) {
        return add( this, other );
    }
    
    public Fraction reduce() {
       final int b = MathUtilities.greatestCommonDivisor( numerator, denominator );
       return Fraction.of( numerator/b, denominator/b );
    }
}
