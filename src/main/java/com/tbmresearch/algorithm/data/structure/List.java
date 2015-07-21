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

package com.tbmresearch.algorithm.data.structure;

import java.util.Iterator;
import java.util.Objects;
import java.util.logging.Logger;

public class List<T> implements Iterable<T> {

    private Node<T> head = null;
    
    private static class Node<T> {
        private T item = null;
        private Node<T> next = null;
    }
    
    public boolean contains( T x ) {
        return search( head, x ) != null;
    }
    
    private Node<T> search( Node<T> node, T x ) {
        if( node == null )
            return null;
        
        if( node.item.equals( x ) )
            return node;
        else
            return( search( node.next, x ) );
    }
    
    public void insert( T x ) {
        final Node<T> node = new Node<T>();
        node.item = x;
        node.next = head;
        head = node;
    }
    
    private Node<T> predecessor( Node<T> node, T x ) {
        if( node == null || node.next == null ) {
            Logger.getLogger( List.class.getName() ).severe( "Error: predecessor sought on null list" );
            return null;
        }
        
        if( node.next.item.equals( x ) )
            return node;
        else
            return predecessor( node.next, x );
    }

    public void delete( T x ) {
        final Node<T> p = search( head, x );
        if( p != null )
            delete( p );
    }

    private void delete( Node<T> node ) {
        Objects.requireNonNull( node );
        
        final Node<T> pred = predecessor( head, node.item );
        if( pred == null )    /* splice out out list */
            head = node.next;
        else
            pred.next = node.next;
    }
    
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> next = head; 

            public boolean hasNext() {
                return next != null;
            }

            public T next() {
                final Node<T> n = next;
                next = next.next;
                return n.item;
            }

            public void remove() {
                delete( next );
            }
        };
    }

    public static void main( String[] args ) {
        // TODO Auto-generated method stub
        
    }
}
