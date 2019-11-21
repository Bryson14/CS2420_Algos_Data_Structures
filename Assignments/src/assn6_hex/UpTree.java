package assn6_hex;

import java.util.ArrayList;

/**
 * takes in integers and preforms smart union on them to form a tree structure
 */
public class UpTree {

    private ArrayList<Integer> paths;

    UpTree() {
        paths = new ArrayList<>();
    }

    /**
     * Adds item as a new root. Does not allow overwriting
     * @param a integer to addRoot
     */
    boolean addRoot(int a) {
        // adding the first item in the tree
        if (a < 1) return false; //cannot addRoot negative or existing integers

        while (paths.size() <= a) { //adjusting size of array
            paths.add(0);
        }
        if (paths.get(a) == 0) {
            paths.set(a, -1); //will not override if node already exists
            return true;
        }
        return false;
    }

    /**
     * if a is found, returns its parent root
     * @param a int to be found
     * @return return 0 if int A was not found
     */
    public int find(int a) {
        try {
           int parent = a;

           if (paths.get(a) == 0) return 0;

           while (paths.get(parent) > 0) {
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
     * @return true if both a and b exist and were joined
     */
    boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) { // they are in the same tree already
            pathCompressionFind(a);
            pathCompressionFind(b);
            return false;
        }
        if (rootA == 0 || rootB == 0) { //one of the numbers doesn't exist in the list
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

    /**
     * finds node A and connects it and all the intermediary nodes directly to the root
     * @param a int
     */
    void pathCompressionFind(int a) {
        int root = find(a);
        if (root == 0) return;
        int parent = paths.get(a);
        int curr = a;

        while (parent > 0 && curr != root) {
            paths.set(curr, root);
            curr = parent;
            parent = paths.get(parent);
        }
    }

    /**
     * takes a list of integers and attempts to union them to the known-to-exist node a
     * @param a int that is in the uptree
     * @param neighbors ints that may or may not be in the uptree
     */
    void tryUnion(int a, int[] neighbors) {
        for (int b : neighbors) {
            if (b > 0) {
                union(a,b);
            }
        }
    }

    /**
     * returns array list of paths for testing purposes
     * @return ArrayList of integers with structure of upTree
     */
    public ArrayList<Integer> getPaths() {
        return paths;
    }

    /**
     * Displays a reader-friendly print statement with pointer on top and index location on bottom
     * @return String on upTree structure
     */
    String printList() {
        StringBuilder sb = new StringBuilder();
        for (int a = 1; a < paths.size(); a ++) {
            sb.append(paths.get(a)).append("\t");
        }
        sb.append("\n");
        for (int i = 0; i < paths.size(); i++) {
            sb.append(i).append("\t");
        }

        return sb.toString();
    }

    /**
     * Displays hexBoard representation of the upTree
     * @return String of 11 x 11 HexBoard structure
     */
    String printHexBoard() {
        StringBuilder sb = new StringBuilder();
        int eleven = 0;
        for (int a = 1; a < paths.size(); a ++) {
            int b = paths.get(a);
            if (b > 9) sb.append(b).append("   "); //all this crap is to make it lot pretty when it prints out
            else if (b < -9) sb.append(b).append("  ");
            else if (b < 0) sb.append(b).append("   ");
            else sb.append(b).append("    ");
            eleven ++;
            if (eleven >= 11) {
                eleven = 0;
                sb.append("\n");
                for(int i = 0; i < (a-1)/11; i ++) {
                    sb.append("  ");
                }
                sb.append("  ");
            }
        }
        return sb.toString();
    }
}
