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
     * if a is found, returns its parent root
     * @param a
     * @return return 0 if int A was not found
     */
    private int findRoot(int a) {
        try {
           int parent = a;

           while (paths.get(parent) >= 0) {
               parent = paths.get(parent);
           }

           return parent;

        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    /**
     * smart union (union of size)
     * @param a first integer
     * @param b second integer
     * @return if both a and b exist and were joined
     */
    public boolean union(int a, int b) {
        int rootA = findRoot(a);
        int rootB = findRoot(b);
        if (rootA == rootB) { // they are in the same tree already
            return false;
        }

        if (paths.get(rootA) <= paths.get(rootB)) { //A is less, aka greater magnitude than B
            paths.set(rootA, paths.get(rootA) + paths.get(rootB));
            paths.set(rootB, rootA);
        } else {
            paths.set(rootB, paths.get(rootB) + paths.get(rootA)); // updating the size of root
            paths.set(rootA, rootB);

        }
        return true;
    }

    public void pathCompressionFind(int a) {
        int root = findRoot(a);
        int parent = paths.get(a);
        int curr = a;

        while (parent > 0 && curr != root) {
            paths.set(curr, root);
            curr = parent;
            parent = paths.get(parent);
        }
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
