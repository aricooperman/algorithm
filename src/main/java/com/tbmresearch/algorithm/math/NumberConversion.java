package com.tbmresearch.algorithm.math;

public class NumberConversion {
    private static char[] CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /* 
     * From topcoder article https://www.topcoder.com/community/data-science/data-science-tutorials/mathematics-for-topcoders/
     */
    public static int toDecimal( int n, int base ) {
        int result = 0;
        int multiplier = 1;

        while( n > 0 ) {
            result += (n % 10) * multiplier;
            multiplier *= base;
            n /= 10;
        }

        return result;
    }

    /* 
     * From topcoder article https://www.topcoder.com/community/data-science/data-science-tutorials/mathematics-for-topcoders/
     */
    public static int fromDecimal( int n, int base ) {
        assert base >= 2 && base <= 10;
        
        int result = 0;
        int multiplier = 1;

        while( n > 0 ) {
            result += (n % base) * multiplier;
            multiplier *= 10;
            n /= base;
        }

        return result;
    }
    
    /* 
     * From topcoder article https://www.topcoder.com/community/data-science/data-science-tutorials/mathematics-for-topcoders/
     */
    public static String fromDecimalLargeBase( int n, int base ) {
        assert base >= 2 && base <= 36;

        final StringBuilder result = new StringBuilder();

        while( n > 0 ) {
            result.insert( 0, CHARS[n % base] );
            n /= base;
        }

        return result.toString();
    }
}
