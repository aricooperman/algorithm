package com.tbmresearch.algorithm.math;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrimeNumberTest {

    @Test
    public final void testIsPrime() {
        assertTrue( PrimeNumber.isPrime( 2 ) );
        assertTrue( PrimeNumber.isPrime( 3 ) );
        assertTrue( PrimeNumber.isPrime( 17 ) );
        assertTrue( PrimeNumber.isPrime( 1163 ) );
        assertTrue( PrimeNumber.isPrime( 3461 ) );
        
        assertFalse( PrimeNumber.isPrime( 4 ) );
        assertFalse( PrimeNumber.isPrime( 9 ) );
        assertFalse( PrimeNumber.isPrime( 1024 ) );
    }

    @Test
    public final void testSieve() {
        final boolean[] primeExpected = new boolean[] { 
                false,  //0 
                false,
                true,
                true,
                false,
                true,   //5
                false,
                true,
                false,
                false,
                false,  //10
                true,
                false,
                true,
                false,
                false,  //15
                false,
                true,
                false,
                true,
                false   //20                
        };
        
        final boolean[] primeTest = PrimeNumber.sieve( 20 );
        
        assertArrayEquals( primeExpected, primeTest );
    }

    
    @Test
    public final void testAgreement() {
        final boolean[] primes = PrimeNumber.sieve( 4000 );
        for( int i = 0; i < primes.length; ++i ) {
            if( PrimeNumber.isPrime( i ) )
                assertTrue( "isPrime/sieve disagree on " + i, primes[i] );
            else
                assertFalse( "isPrime/sieve disagree on " + i, primes[i] );
        }
    }
    
}
