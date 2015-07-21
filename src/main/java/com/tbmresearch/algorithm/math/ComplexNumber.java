/*
 * Adapted from topcoder article https://www.topcoder.com/community/data-science/data-science-tutorials/mathematics-for-topcoders/
 */

package com.tbmresearch.algorithm.math;

public class ComplexNumber {
    
    private final int realPart;
    private final int imaginaryPart;
    
    //a + ib
    private ComplexNumber( int a, int b ) {
        this.realPart = a;
        this.imaginaryPart = b;
    }
    
    public int getRealPart() {
        return realPart;
    }

    public int getImaginaryPart() {
        return imaginaryPart;
    }

    @Override
    public String toString() {
        return String.format( "%d + i%d", realPart, imaginaryPart );
    }

    public static ComplexNumber of( int realPart, int imaginaryPart ) {        
        return new ComplexNumber( realPart, imaginaryPart );
    }
    
    public static ComplexNumber multiply( ComplexNumber a, ComplexNumber b ) {
        return ComplexNumber.of( a.realPart*b.realPart - a.imaginaryPart*b.imaginaryPart, 
                a.realPart*b.imaginaryPart + a.imaginaryPart*b.realPart );
    }
    
    public ComplexNumber multiply( ComplexNumber other ) {
        return multiply( this, other );
    }
    
    public static ComplexNumber add( ComplexNumber a, ComplexNumber b ) {
       return ComplexNumber.of( a.realPart + b.realPart, a.imaginaryPart + b.imaginaryPart );
    }
    
    public ComplexNumber add( ComplexNumber other ) {
        return add( this, other );
    }
    
    public ComplexNumber reduce() {
       final int b = MathUtilities.greatestCommonDivisor( realPart, imaginaryPart );
       return ComplexNumber.of( realPart/b, imaginaryPart/b );
    }
}
