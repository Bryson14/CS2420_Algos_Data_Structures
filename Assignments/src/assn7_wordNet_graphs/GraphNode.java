package assn7_wordNet_graphs;

import java.util.LinkedList;

public class GraphNode {
    private int nodeID;
    PathInfo p1;
    PathInfo p2;
    private LinkedList<EdgeInfo> succ;

    public GraphNode(){
        this(0);
    }

    GraphNode(int nodeID){
        this.nodeID = nodeID;
        this.p2 = new PathInfo();
        this.p1 = new PathInfo();
        this.succ = new LinkedList<>();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(nodeID);
        sb.append(p1.toString());
        sb.append(p2.toString());
        for (EdgeInfo e:succ){
            sb.append(e.toString());
        }
        sb.append("\n");
        return sb.toString();
    }

    void addEdge(int v1, int v2){
        succ.addFirst( new EdgeInfo(v1,v2) );
    }
}
