package com.tbmresearch.algorithm.math;

public class MathUtilities {

    // 2336 and 1314 is 146
    /* 
     * From topcoder article https://www.topcoder.com/community/data-science/data-science-tutorials/mathematics-for-topcoders/
     */
    public static int greatestCommonDivisor( int a, int b ) {
        assert a != 0 || b != 0;

        if( b==0 )
            return a;
        
        return greatestCommonDivisor( b, (a % b) );
    }
    
    // 6 and 9 is 18
    /* 
     * From topcoder article https://www.topcoder.com/community/data-science/data-science-tutorials/mathematics-for-topcoders/
     */
    public static int lowestCommonMultiple( int a, int b ) {
       return b*a/greatestCommonDivisor( a, b );
    }
}
