package com.tbmresearch.algorithm.math;

import java.util.Arrays;

public class PrimeNumber {

    /* 
     * From topcoder article https://www.topcoder.com/community/data-science/data-science-tutorials/mathematics-for-topcoders/
     */
    public static boolean isPrime( int n ) {
        if( n <= 1 )
            return false;
        
        if( n == 2 ) 
            return true;
        
        if( n % 2 == 0 ) 
            return false;
        
        final int m = (int)Math.sqrt( n );

        for( int i = 3; i <= m; i += 2 ) {
           if( n % i == 0 )
              return false;
        }

        return true;
    }
    
    /* 
     * From topcoder article https://www.topcoder.com/community/data-science/data-science-tutorials/mathematics-for-topcoders/
     */
    public static boolean[] sieve( int n ) {
        final boolean[] prime = new boolean[n+1];
        Arrays.fill( prime, true );
        prime[0] = false;
        prime[1] = false;
        final int m = (int)Math.sqrt(n);

        for( int i = 2; i <= m; i++ )
           if( prime[i] )
              for( int k = i*2; k <= n; k += i )
                 prime[k] = false;

        return prime;
     } 
}
