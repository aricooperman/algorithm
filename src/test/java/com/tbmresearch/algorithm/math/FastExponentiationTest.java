package com.tbmresearch.algorithm.math;

import static org.junit.Assert.*;

import org.junit.Test;

public class FastExponentiationTest {

    @Test
    public final void testPower() {
        final double a = 3.5;
        final int n = 246;
        
        long now = System.nanoTime();
        final double power = FastExponentiation.power( a, n );
        long diff = System.nanoTime() - now;
        System.out.format( "FastExponentiation.power took = %d%n", diff );
        
        now = System.nanoTime();
        final double powerRef = StrictMath.pow( a, n );
        diff = System.nanoTime() - now;
        System.out.format( "Math.pow took = %d%n", diff );
        
        assertEquals( power, powerRef, 1E133 );
        
        assertEquals( FastExponentiation.power( 2, 2 ), 4.0, 0.0 );
        
        assertEquals( FastExponentiation.power( 20, 0 ), 1.0, 0.0 );
    }

}
