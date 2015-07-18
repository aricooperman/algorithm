package com.tbmresearch.algorithm.string;

import static org.junit.Assert.*;

import org.junit.Test;

public class PatternMatchingTest {

    @Test
    public final void testFindMatchStringString() {
        final String text = "Perhaps you are interested finding where “Skiena” appears in a given news " +
                "article (well, I would be interested in such a thing). This is an instance of string " +
                "pattern matching with t as the news article and p=“Skiena.”";
        
        assertEquals( PatternMatching.findMatch( "Skiena", text ), 42 );
        assertEquals( PatternMatching.findMatch( "skiena", text ), -1 );
    }

    @Test
    public final void testFindMatchCharArrayCharArray() {
        final char[] text = ("Perhaps you are interested finding where “Skiena” appears in a given news " +
                "article (well, I would be interested in such a thing). This is an instance of string " +
                "pattern matching with t as the news article and p=“Skiena.”").toCharArray();
        
        assertEquals( PatternMatching.findMatch( "Skiena".toCharArray(), text ), 42 );
        assertEquals( PatternMatching.findMatch( "skiena".toCharArray(), text ), -1 );
    }

}
