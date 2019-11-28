package assn7_wordNet_graphs;

import java.io.File;
import java.util.*;

public class Graph {
    private int numVertex;  // Number of vertices in the graph.
    private GraphNode[] G;  // Adjacency list for graph.
    private String graphName;  //The file from which the graph was created.


    private Graph() {
        this.numVertex = 0;
        this.graphName = "";
    }

    public Graph(int numVertex) {
        this.numVertex = numVertex;
        G = new GraphNode[numVertex];
        for (int i = 0; i < numVertex; i++) {
            G[i] = new GraphNode( i );
        }
    }

    public boolean addEdge(int source, int destination) {
        if (source < 0 || source >= numVertex) return false;
        if (destination < 0 || destination >= numVertex) return false;
        G[source].addEdge( source, destination );
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("The Graph ").append(graphName).append(" \n" );
        for (int i = 0; i < numVertex; i++) {
            sb.append( G[i].toString() );
        }
        return sb.toString();
    }

    private void makeGraph(String pathname, String filename) {
        try {
            graphName = filename;
            Scanner reader = new Scanner( new File( pathname + filename ) );
            System.out.println( "\n" + filename );
            numVertex = reader.nextInt();
            G = new GraphNode[numVertex];
            for (int i = 0; i < numVertex; i++) {
                G[i] = new GraphNode( i );
            }
            while (reader.hasNextInt()) {
                int v1 = reader.nextInt();
                int v2 = reader.nextInt();
                G[v1].addEdge( v1, v2 );
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void clearAllPred() {
        for (int i = 0; i < numVertex; i++) {
            G[i].p1.clear();
            G[i].p2.clear();
        }
    }

    /**
     * Find the path from v1 to v2 going through anc.
     *
     * @param anc: ancestor of v1 and v2
     * @return the path
     */
    private StringBuilder reportPath(int anc) {
        int idx = anc;
        StringBuilder sb = new StringBuilder();
        sb.append(anc);

        //first half
        while (G[idx].p1.dist > 0) {
            sb.insert(0," ").insert(0,G[idx].p1.pred);
            idx = G[idx].p1.pred;
        }
        idx = anc;
        // 2nd half
        while (G[idx].p2.dist > 0) {
            sb.append(" ").append(G[idx].p2.pred);
            idx = G[idx].p2.pred;
        }
        return sb;
    }

    /**
     * Computes the least common ancestor of v1 and v2, prints the length of the path, the ancestor, and the path itself.
     *
     * @param v1: first vertex
     * @param v2: second vertex
     * @return returns the length of the shortest ancestral path.
     */
    private int lca(int v1, int v2, boolean print) {
        // Compute lca
        alterPathInfo(v1, v1, 0, true);
        alterPathInfo(v2, v2, 0, false);

        PathInfo best = new PathInfo();

        // now that the information is all set, find the smallest of all the paths
        for (GraphNode node: G) {
            int sum = node.p1.dist + node.p2.dist;
            if (sum < best.dist) {
                best.dist = sum;
                best.pred = node.nodeID;
            }
        }
        if (print) {
            System.out.println( graphName + " Best lca " + v1 + " " + v2 + " Distance: "
                    + best.dist + " Ancestor " + best.pred + " Path:" + reportPath(best.pred) );
        }

        clearAllPred();
        return best.dist;
    }

    private void alterPathInfo(int pred, int curr, int dist, boolean swap) {

        if (swap) {
            //make sure that you're not overriding a more direct route in case or two paths
            if (G[curr].p1.dist > dist) {
                G[curr].p1.set(pred, dist);
            }
        }
        else {
            if (G[curr].p2.dist > dist) {
                G[curr].p2.set(pred, dist);
            }
        }

        if (G[curr].succ.size() > 0) { // pred is not the root
            for (EdgeInfo edge: G[curr].succ) {
                alterPathInfo(curr, edge.to, dist + 1, swap); //a number is its own predecessor the first time through
            }
        }
    }

    private int outcast(int[] v) {
        int outcast = -1;
        int longest = -1;

        for (int i : v) {
            int dist = 0;

            for (int j : v) {
                dist += lca(i,j, false);
            }
            if (longest == -1 || dist > longest) {
                outcast = i;
                longest = dist;
                clearAllPred();
            }
        }
        System.out.println( "The outcast of " + Arrays.toString( v ) + " is "
                + outcast + " with a distance of " + longest + ".");
        return outcast;
    }

    public static void main(String[] args) {
        Graph graph1 = new Graph();
        String srcDir = System.getProperty("user.dir");
        String s = System.getProperty("file.separator");
        String pathname = srcDir+s+"Assignments"+s+"src"+s+"assn7_wordNet_graphs"+s;
        graph1.makeGraph(pathname , "digraph1.txt");
        //System.out.println(graph.toString());
        int[] set1 = {7, 10, 2};
        int[] set2 = {7, 17, 5, 11, 4, 23};
        int[] set3 = {10, 17, 13};

        graph1.lca( 5, 6 , true);
        graph1.lca( 3, 7 , true);
        graph1.lca( 9, 1 , true);
        graph1.outcast( set1 );

        Graph graph2 = new Graph();
        graph2.makeGraph( pathname , "digraph2.txt" );
        System.out.println(graph2.toString());

        graph2.lca( 3, 24 , true);
        graph2.lca(21,23, true);
        graph2.lca(3,13, true);
        graph2.lca(24,14, true);
        graph2.lca(1,10, true);
        graph2.lca(0,17, true);

        graph2.outcast( set3 );
        graph2.outcast( set2 );
        graph2.outcast( set1 );
    }
}
