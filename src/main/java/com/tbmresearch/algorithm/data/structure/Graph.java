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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Graph {

    private static final int DEFAULT_MAXV = 1000;                   /* default maximum number of vertices */
    
    private static class EdgeNode {
        private int y;      /* adjacency info */
        private int weight; /* edge weight, if any */
        private EdgeNode next = null;
    }
    
    private final EdgeNode[] edges;                                 /*adjacency info */
    private final int degree[];                                     /* outdegree of each vertex */
    
    private int nvertices = 0;                                          /* number of vertices in graph */
    private int nedges = 0;                                             /* number of edges in graph */
    private boolean directed;                                       /* is the graph directed? */

    public Graph() {
        this( false );
    }
    
    public Graph( final boolean directed ) {
        this( DEFAULT_MAXV, directed );
    }
    
    public Graph( int maxEdges ) {
        this( maxEdges, false );
    }
    
    public Graph( final int maxEdges, final boolean directed ) {
        edges = new EdgeNode[maxEdges+1];
        degree = new int[maxEdges+1];
        this.directed = directed;
    }
    
    public static Graph fromConsole() throws IOException {
        return fromInputStream( System.in );
    }
    
    public static Graph fromFile( final String file ) throws IOException {
        try( FileInputStream fis = new FileInputStream( file ) ) {
            return fromInputStream( fis );
        }
    }
    
    public static Graph fromFile( final File file ) throws IOException {
        try( FileInputStream fis = new FileInputStream( file ) ) {
            return fromInputStream( fis );
        }
    }
    
    public static Graph fromInputStream( final InputStream is ) {
        Objects.requireNonNull( is );
        
        try( Scanner scanner = new Scanner( is ) ) {
    //        scanner.useDelimiter( "\\w+" );
            final int verticeCount = scanner.nextInt();
            final int m = scanner.nextInt();    /* number of edges */
                
            boolean directed = scanner.hasNextBoolean() ? scanner.nextBoolean() : false;
            final Graph graph = new Graph( verticeCount, directed );
            
            int x, y; /* vertices in edge (x,y) */
            for( int i = 1; i <= m; i++ ) {
                x = scanner.nextInt();
                y = scanner.nextInt();
                graph.insertEdge( x, y, directed );
            }
            return graph;
        }        
    }

    private void insertEdge( int x, int y, boolean directed ) {
        final EdgeNode p = new EdgeNode();
        //p.weight = NULL;
        p.y = y;
        p.next = edges[x];
        edges[x] = p; /* insert at head of list */
        degree[x]++;
        
        if( !directed )
            insertEdge( y, x, true );
        else
            nedges++;
        
    }
    
    public void print() {
        print( System.out );
    }
    
    public void print( PrintStream ps ) {
        EdgeNode p;
        for( int i = 1; i <= nvertices; i++ ) {
            ps.format( "%d: ", i );
            p = edges[i];
            while( p != null ) {
                ps.format( " %d", p.y );
                p = p.next;
            }
            ps.println();
        }
    }
    
    public void findPath( int start, int end, int[] parents ) {
        //TODO make it return path
        if( start == end || end == -1 )
            System.out.printf( "%n%d", start );
        else {
            findPath( start, parents[end], parents );
            System.out.printf( " %d", end );
        }
    }
    
    public abstract class GraphSearch {
        protected final boolean[] processed = new boolean[nedges];         /* which vertices have been processed */
        protected final boolean[] discovered = new boolean[nedges];        /* which vertices have been found */
        protected final int[] parent = new int[nedges];                    /* discovery relation */
        
        public GraphSearch() {
            Arrays.fill( processed, false );            // Being explicit
            Arrays.fill( discovered, false );
            Arrays.fill( parent, -1 );
        }
        
        public void search() {
            search( 0 );
        }
        
        public abstract void search( int start );

        protected abstract void processVertexLate( int v );

        protected abstract void processVertexEarly( int v );

        protected abstract void processEdge( int x, int y );

    }
    
    public abstract class DepthFirstSearch extends GraphSearch {
        protected static final int TREE = 0;
        protected static final int BACK = 1;
        protected static final int FORWARD = 2;
        protected static final int CROSS = 3;
        
        protected int[] entryTime = new int[nedges];          /* time of vertex entry */
        protected int[] exitTime = new int[nedges];           /* time of vertex exit */
        protected int time = 0;                               /* current event time */
        protected boolean finished = false;   /* if true, cut off search immediately */

        public DepthFirstSearch() {
            super();
        }
        
        /**
         * @see com.tbmresearch.algorithm.data.structure.GraphSearch#search(int)
         */
        @Override
        public void search( final int v ) {
            EdgeNode p;     /* temporary pointer */
            int y;          /* successor vertex */
            
            if( finished )   /* allow for search termination */
                return;
           
            discovered[v] = true;
            time = time + 1;
            entryTime[v] = time;
            processVertexEarly( v );
            p = edges[v];
            
            while( p != null ) {
                y = p.y;
                
                if( !discovered[y] ) {
                    parent[y] = v;
                    processEdge( v, y );
                    search( y );
                }
                else if( (!processed[y] && parent[v] != y) || directed )
                    processEdge( v, y );
                
                if( finished ) 
                    return;

                p = p.next;
            }
            
            processVertexLate( v );
            time = time + 1;
            exitTime[v] = time;
            processed[v] = true;
        }
        
        public int edgeClassification( int x, int y ) {
            if( parent[y] == x ) 
                return TREE;
            
            if( discovered[y] && !processed[y] )
                return BACK;

            if( processed[y] && (entryTime[y] > entryTime[x]) ) 
                return FORWARD;
            
            if( processed[y] && (entryTime[y] < entryTime[x]) ) 
                return CROSS;
            
            //Should not happen
            throw new RuntimeException( String.format( "Warning: unclassified edge (%d,%d)%n", x, y ) );
        }
    }
    
    public abstract class BreadthFirstSearch extends GraphSearch {
        
        public BreadthFirstSearch() {
            super();
        }
    
        /**
         * @see com.tbmresearch.algorithm.data.structure.GraphSearch#search(int)
         */
        @Override
        public void search( final int start ) {
            final Queue<Integer> q = new Queue<>();                 /* queue of vertices to visit */
            int v;                                                  /* current vertex */
            int y;                                                  /* successor vertex */
            EdgeNode p;                                             /* temporary pointer */
            q.enqueue( start );
            discovered[start] = true;
            
            while( !q.isEmpty() ) {
                v = q.dequeue();
                processVertexEarly( v );
                processed[v] = true;
                p = edges[v];
                while( p != null ) {
                    y = p.y;
                    if( !processed[y] || directed )
                        processEdge( v, y );
                    
                    if( !discovered[y] ) {
                        q.enqueue( y );
                        discovered[y] = true;
                        parent[y] = v;
                    }
                    
                    p = p.next;
                }
                processVertexLate( v );
            }
            
        }
    }
    
    public class ConnectedComponentsSearch extends BreadthFirstSearch {
        
        public ConnectedComponentsSearch() {
            super();
        }

        @Override
        public void processVertexLate( int v ) { }

        @Override
        public void processVertexEarly( int v ) {
            System.out.printf( " %d", v );
        }

        @Override
        public void processEdge( int x, int y ) { /* No Op */ }

        @Override
        public void search() {
            int c = 0;  /* component number */
            
            for( int i = 1; i <= nvertices; i++ ) {
                if( !discovered[i] ) {
                    c = c+1;
                    System.out.printf( "Component %d:", c );
                    search( i );
                    System.out.println();
                }
            }
        }
    }
    
    public class TwoColorSearch extends BreadthFirstSearch {

        private static final byte UNCOLORED = 0;
        private static final byte WHITE = 1;
        private static final byte BLACK = 2;

        private final byte[] color = new byte[nedges];

        private boolean bipartite = true;
                
        
        public TwoColorSearch() {
            super();
            for( int i = 1; i <= nvertices; i++ )
                color[i] = UNCOLORED;
        }

        public boolean isBipartite() {
            return bipartite;
        }

        @Override
        public void search() {
            for( int i = 1; i <= nvertices; i++ ) {
                if( !discovered[i] ) {
                    color[i] = WHITE;
                    search( i );
                }
            }
        }

        @Override
        public void processVertexLate( int v ) { }

        @Override
        public void processVertexEarly( int v ) { }

        @Override
        public void processEdge( int x, int y ) {
            if( color[x] == color[y] ) {
                bipartite = false;
                System.out.printf( "Warning: not bipartite due to (%d,%d)%n", x, y );
            }
            color[y] = complement( color[x] );
        }

        private byte complement( byte color ) {
            if( color == WHITE )
                return BLACK;
            if( color == BLACK ) 
                return WHITE;
            return UNCOLORED;
        }   
    }
    
    public class GraphCycleSearch extends DepthFirstSearch {

        @Override
        protected void processVertexLate( int v ) { }

        @Override
        protected void processVertexEarly( int v ) { }

        @Override
        protected void processEdge( int x, int y ) {
            if( discovered[y] && (parent[x] != y) ) {   /* found back edge */
                System.out.printf( "Cycle from %d to %d:", y, x );
                findPath( y, x, parent );
                System.out.println();
                System.out.println();
                finished = true;
            }
        }
    }
    
    public class ArticulationVerticesSearch extends DepthFirstSearch {
        
        private final int[] reachableAncestor = new int[nedges];  /* earliest reachable ancestor of v */
        private final int[] treeOutDegree = new int[nedges];      /* DFS tree outdegree of v */

        @Override
        protected void processVertexLate( int v ) {
            boolean root;               /* is the vertex the root of the DFS tree? */
            int timeV;                  /* earliest reachable time for v */
            int timeParent;             /* earliest reachable time for parent[v] */
            
            if( parent[v] < 1 ) {             /* test if v is the root */
                if( treeOutDegree[v] > 1 )
                    System.out.printf("root articulation vertex: %d \n",v);
                return;
            }
            
            root = (parent[parent[v]] < 1);  /* is parent[v] the root? */
            if( reachableAncestor[v] == parent[v] && !root )
                System.out.printf( "parent articulation vertex: %d %n", parent[v] );
            
            if( reachableAncestor[v] == v ) {
                System.out.printf( "bridge articulation vertex: %d %n",parent[v] );
                
                if( treeOutDegree[v] > 0 ) /* test if v is not a leaf */
                    System.out.printf( "bridge articulation vertex: %d %n", v );
            }
            
            timeV = entryTime[reachableAncestor[v]];
            timeParent = entryTime[reachableAncestor[parent[v]]];
            
            if( timeV < timeParent )
                reachableAncestor[parent[v]] = reachableAncestor[v];
        }

        @Override
        protected void processVertexEarly( int v ) {
            reachableAncestor[v] = v;
        }

        @Override
        protected void processEdge( int x, int y ) {
            final int edgeClass = edgeClassification( x, y );
            if( edgeClass == TREE )
                treeOutDegree[x] = treeOutDegree[x] + 1;
            
            if( edgeClass == BACK && parent[x] != y ) {
                if( entryTime[y] < entryTime[reachableAncestor[x]] )
                    reachableAncestor[x] = y;
            }
        }        
    }
}
