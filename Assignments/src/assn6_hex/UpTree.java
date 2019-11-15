package assn6_hex;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * takes in integers and preforms smart union on them to form a tree structure
 */
public class UpTree {
    private ArrayList<Integer> paths;
    private ArrayList<Integer> roots;
    private HashSet<Integer> nodes;

    UpTree() {
        paths = new ArrayList<>();
        roots = new ArrayList<>();
        nodes = new HashSet<>();
    }

    /**
     * Adds item as a new root
     * @param a integer to addRoot
     */
    public void addRoot(int a) {
        // adding the first item in the tree
        if (a < 1 || nodes.contains(a)) return; //cannot addRoot negative or existing integers

        while (paths.size() <= a) { //adjusting size of array
            paths.add(0);
        }
        paths.set(a, -1);
        roots.add(a);
    }

    /**
     * if a is found, the path in compressed
     * @param a
     * @return return true if int a was found
     */
    private Integer findRoot(int a) {
        try {
           int parent = a;

           while (roots.get(parent) >= 0) {
               parent = roots.get(parent);
           }

           return parent;

        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * smart union (union of size)
     * @param a first integer
     * @param b second integer
     * @return if both a and b exist and were joined
     */
    public boolean union(int a, int b) {
        return true;
    }

    public ArrayList<Integer> getPaths() {
        return paths;
    }

    public String printList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paths.size(); i++) {
            sb.append(paths.get(i) + "\t");
        }
        sb.append("\n");
        for (int i = 0; i < paths.size(); i++) {
            sb.append(i + "\t");
        }

        return sb.toString();
    }
}
