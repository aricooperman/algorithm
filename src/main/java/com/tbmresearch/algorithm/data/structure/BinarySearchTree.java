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

import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    
    public enum TraverseType { IN_ORDER, PRE_ORDER, POST_ORDER }


    private Node<T> root = null;
    
    
    private static class Node<T extends Comparable<T>> {
        private T item = null;
        private Node<T> parent = null;
        private Node<T> left = null;
        private Node<T> right = null;
    }
    
    public boolean contains( T x ) {
        return search( root, x ) != null;
    }
    
    private Node<T> search( Node<T> node, T x ) {
        if( node == null ) 
            return null;
        
        if( node.item.equals( x ) )
            return node;

        if( x.compareTo( node.item ) < 0 )
            return search( node.left, x );
        else
            return search( node.right, x );
    }
    
    public T findMinimum() {
        if( root == null ) 
            return null;
        
        Node<T> min = root;
        while( min.left != null )
            min = min.left;
        
        return min.item;
    }
    
    public T findMaximum() {
        if( root == null ) 
            return null;
        
        Node<T> max = root;
        while( max.right != null )
            max = max.right;
        
        return max.item;
    }
    
    public void insert( T x ) {
        if( root == null ) {
            root = new Node<T>(); /* allocate new node */
            root.item = x;
        }
        else
            insert( root, x );
    }
    
    private void insert( Node<T> node, T x ) {        
        if( x.compareTo( node.item ) < 0 ) {
            if( node.left == null ) {
                node.left = new Node<T>(); /* allocate new node */
                node.left.item = x;
                node.left.parent = node;
            }
            else
                insert( node.left, x );
        }
        else {
            if( node.right == null ) {
                node.right = new Node<T>(); /* allocate new node */
                node.right.item = x;
                node.right.parent = node;
            }
            else
                insert( node.right, x );
        }
    }
    
    public void traverse( TraverseType type, Consumer<T> consumer ) {
        if( type == null )
            return;
        
        switch( type ) {
            case IN_ORDER:
                inOrderTraversal( root, consumer );
                break;
            case PRE_ORDER:
                preOrderTraversal( root, consumer );
                break;
            case POST_ORDER:
                postOrderTraversal( root, consumer );
                break;
        }
    }
    
    private void inOrderTraversal( Node<T> node, Consumer<T> consumer ) {
        if( node != null ) {
            inOrderTraversal( node.left, consumer );
            consumer.accept( node.item );
            inOrderTraversal( node.right, consumer );
        }
    }
    
    private void preOrderTraversal( Node<T> node, Consumer<T> consumer ) {
        if( node != null ) {
            consumer.accept( node.item );
            inOrderTraversal( node.left, consumer );
            inOrderTraversal( node.right, consumer );
        }
    }
    
    private void postOrderTraversal( Node<T> node, Consumer<T> consumer ) {
        if( node != null ) {
            inOrderTraversal( node.left, consumer );
            inOrderTraversal( node.right, consumer );
            consumer.accept( node.item );
        }
    }
    

    public static void main( String[] args ) {
        // TODO Auto-generated method stub

    }

}
