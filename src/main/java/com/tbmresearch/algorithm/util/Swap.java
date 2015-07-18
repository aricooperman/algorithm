package com.tbmresearch.algorithm.util;

public class Swap {

    public static <T> void swap( final T[] s, int i, int j ) {
        final T temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static void swap( final char[] s, int i, int j ) {
        final char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static void swap( final int[] s, int i, int j ) {
        final int temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}
