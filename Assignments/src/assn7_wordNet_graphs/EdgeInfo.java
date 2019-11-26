package assn7_wordNet_graphs;

public class EdgeInfo {

    private int from;        // source of edge
    private int to;          // destination of edge

    public EdgeInfo(int from, int to){
        this.from = from;
        this.to = to;
    }

    public String toString(){
        return from + "->" + to + " " ;
    }
}
