// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

class UnderflowException extends RuntimeException {
    /**
     * Construct this exception object.
     * @param message the error message.
     */
    public UnderflowException(String message) {
        super(message);
    }
}

public class Tree<E extends Comparable<? super E>> {
    final String ENDLINE = "\n";
    private BinaryNode<E> root;  // Root of tree
    private BinaryNode<E> curr;  // Last node accessed in tree
    private String treeName;     // Name of tree

    /**
     * Create an empty tree
     *
     * @param label Name of tree
     */
    public Tree(String label) {
        treeName = label;
        root = null;
    }

    /**
     * Create non ordered tree from list in preorder
     * @param arr    List of elements
     * @param label  Name of tree
     * @param height Maximum height of tree
     */
    public Tree(ArrayList<E> arr, String label, int height) {
        this.treeName = label;
        root = buildTree(arr, height, null);
    }

    /**
     * Create BST
     * @param arr   List of elements to be added
     * @param label Name of tree
     */
    public Tree(ArrayList<E> arr, String label) {
        root = null;
        treeName = label;
        for (int i = 0; i < arr.size(); i++) {
            bstInsert(arr.get(i));
        }
    }


    /**
     * Create BST from Array
     * @param arr   List of elements to be added
     * @param label Name of  tree
     */
    public Tree(E[] arr, String label) {
        root = null;
        treeName = label;
        for (int i = 0; i < arr.length; i++) {
            bstInsert(arr[i]);
        }
    }

    /**
     * Change name of tree
     * @param name new name of tree
     */
    public void changeName(String name) {
        this.treeName = name;
    }

    /**
     * Return a string displaying the tree contents as a tree with one node per line
     * O(n)
     */
    public String toString() {
        if (root == null) {
            return (treeName + " Empty tree\n");
        } else {
            String space = "";
            return treeName + "\n" + toStringRecur(root.left, space)  + "\n" + root.element +
                    "[No Parent so sad]" + toStringRecur(root.right, space) + "\n";
        }
    }
    private String toStringRecur(BinaryNode<E> node, String space) {
        if (node == null) {
            return "";
        } else {
            space += "  ";
            return toStringRecur(node.left, space) + "\n" + space + node.element +
                    "[" + node.parent.element + "]" + toStringRecur(node.right, space);
        }
    }

    /**
     * Return a string displaying the tree contents as a single line
     */
    public String toString2() {
        if (root == null)
            return treeName + " Empty tree";
        else
            return treeName + " " + toString2(root);
    }

    /**
     * reverse left and right children recursively
     * O(n)
     */
    public void flip() {
        flipRecur(root);
    }

    private void flipRecur(BinaryNode<E> node) {
        if (node == null) {return;}

        BinaryNode<E> leftOG = node.left;
        node.left = node.right;
        node.right = leftOG;

        flipRecur(node.right);
        flipRecur(node.left);
    }

    /**
     * Find successor of "curr" node in tree
     * @return String representation of the successor
     */
    public String successor() {
        if (curr == null) curr = root;
        if (curr.right != null) {
            curr = min(curr.right);  //if right child exist, the successor is the min of that branch
        } else if (curr.parent.left == curr) {
            curr = curr.parent;  // if there no right child, then a right parent is successor
        } else {
            curr = successorAbove(curr.parent); // successor is null or is at least 2 levels up
        }
        if (curr == null) return "null";
        else return curr.toString();
    }

    private BinaryNode<E> successorAbove(BinaryNode<E> node) {
            if (node == root) return null;

            else if (node.parent.right == node) {
                return successorAbove(node.parent);

            } else return node.parent;
    }

    public String inorder(BinaryNode<E> node) {
        if (node == null) {return "";}
        return inorder(node.left) + " " + node.element + inorder(node.right);
    }

    private BinaryNode<E> min(BinaryNode<E> node) {
        if (node.left != null) return min(node.left);
        else return node;
    }


    /**
     * Counts number of nodes in specifed level
     * @param level Level in tree, root is zero
     * @return count of number of nodes at specified level
     */
    //TODO fix incorrect output
    public int nodesInLevel(int level) {
        if (level == 0) return 1;
        return nodesInLevel(root, level, 0);
    }

    private int nodesInLevel(BinaryNode<E> node, int level, int times) {
        if (node == null) return 0;
        if (times + 1 == level) {

            if (node.left != null && node.right != null) return 2;
            else if (node.left == null && node.right == null) return 0;
            else return 1;

        } else return nodesInLevel(node.left, level, ++times) + nodesInLevel(node.right, level, ++times);
    }


    /**
     * Print all paths from root to leaves
     */
    public void printAllPaths() {
        if (root == null) System.out.println("No paths for you!");
        else {
            printPaths(root.left, root.element.toString());
            printPaths(root.right, root.element.toString());
        }
    }

    private void printPaths(BinaryNode node, String path) {
        if (node == null) {
            System.out.println(path);
        } else if (node.right != null && node.left != null){
            path += " " + node.element.toString();
            printPaths(node.left, path);
            printPaths(node.right, path);
        } else if (node.right == null) {
            path += " " + node.element.toString();
            printPaths(node.left, path);
        } else {
            path += " " + node.element.toString();
            printPaths(node.right, path);
        } // extra logic stops double printing
    }

    /**
     * Print contents of levels in zig zag order starting at maxLevel
     * @param maxLevel
     */
    // TODO write zigzag function
    public void byLevelZigZag(int maxLevel) {
        if (root == null) System.out.println("Empty Tree\n");
        else if (maxLevel <= 0) System.out.println(root.element);
        else {
            zigZagger(root.left, 1, maxLevel);
            zigZagger(root.right, 1, maxLevel);
            System.out.println(root.element);
        }
    }
    private void zigZagger(BinaryNode node, int currLevel, int maxLevel) {
        if (node == null) return;
        else if (maxLevel <= currLevel) System.out.println(node.element);
        else {
            zigZagger(node.left, ++currLevel, maxLevel);
            zigZagger(node.right, ++currLevel, maxLevel);
            System.out.println(node.element);
        }

    }

    /**
     * Counts all non-null binary search trees embedded in tree
     * @return Count of embedded binary search trees
     */
    public Integer countBST() {
        if (root == null) return 0;
        try {
            Integer x = (Integer)root.element;
        } catch (NumberFormatException e) {
            System.out.println("count BST works only with comparable values, AKA Integers." + e);
            return 0;
        }
        return countBSTRecur(root);
    }

    private Integer countBSTRecur(BinaryNode node) {
        if (node == null) return 0;

        Integer BSTOnLeft = countBSTRecur(node.left);
        Integer BSTOnRight = countBSTRecur(node.right);

        if (isBST(node)) {
            return BSTOnLeft + BSTOnRight + 1;
        } else return BSTOnLeft + BSTOnRight;
    }

    /*
    checks if node and everything below is a bst
     */
    private Boolean isBST(BinaryNode node) {
        if (node == null) return true;

        if (node.right == null && node.left == null) {
            return true;
        } else if (node.right == null) {
            if ((Integer)node.left.element < (Integer)node.element) return isBST(node.left);
            else return false;
        } else if (node.left == null) {
            if ((Integer)node.right.element > (Integer)node.element) return isBST(node.right);
            return false;
        } else if ((Integer)node.right.element > (Integer)node.element && (Integer)node.left.element < (Integer)node.element){
            return isBST(node.right) && isBST(node.left);
        } else {return false;}
    }


    /**
     * Insert into a bst tree; duplicates are allowed
     * @param x the item to insert.
     */
    public void bstInsert(E x) {

        root = bstInsert(x, root, null);
    }

    /**
     * Determines if item is in tree
     * @param item the item to search for.
     * @return true if found.
     */
    public boolean contains(E item) {

        return bstContains(item, root);
    }

    /**
     * Remove all paths from tree that sum to less than given value
     * @param k: minimum path sum allowed in final tree
     */
    public void pruneK(Integer k) {
        Integer sum = (Integer)root.element;
        if (largestPath(root.left, sum) >= k) {
            pruneRecur(root.left, sum, k);
        } else {
            root.left = null;
        }
        if (largestPath(root.right, sum) >= k) {
            pruneRecur(root.right, sum, k);
        } else {
            root.right = null;
        }
    }

    private void pruneRecur(BinaryNode node, Integer sum, Integer k) {
        if (node == null) return;
        else {
            sum += (Integer)node.element;
            if (largestPath(node.left, sum) >= k) {
                pruneRecur(node.left, sum, k);
            } else {
                node.left = null;
            }
            if (largestPath(node.right, sum) >= k) {
                pruneRecur(node.right, sum, k);
            } else {
                node.right = null;
            }
        }
    }

    private Integer largestPath(BinaryNode node, Integer sum) {
        if (node == null) {
            return sum;
        } else {
            sum += (Integer)node.element;
            Integer sumR = largestPath(node.right, sum);
            Integer sumL = largestPath(node.left, sum);

            if (sumR > sumL) return sumR;
            else return sumL;
        }
    }

    /**
     * Build tree given inOrder and preOrder traversals.  Each value is unique
     * @param inOrder  List of tree nodes in inorder
     * @param preOrder List of tree nodes in preorder
     */
    // TODO write this function
    public void buildTreeTraversals(E[] inOrder, E[] preOrder) {
        root = null;
    }

    /**
     * Find the least common ancestor of two nodes
     * @param a first node
     * @param b second node
     * @return String representation of ancestor
     */
    // TODO write this function
    public String lca(E a, E b) {
        BinaryNode<E> ancestor = null;
//        if (a.compareTo(b) < 0) {
//            ancestor = lca(root, a, b);
//        } else {
//            ancestor = lca(root, b, a);
//        }
        if (ancestor == null) return "none";
        else return ancestor.toString();
    }

    /**
     * Balance the tree
     */
    // TODO write this function
    public void balanceTree() {
        //root = balanceTree(root);
    }

    /**
     * In a BST, keep only nodes between range
     * @param a lowest value
     * @param b highest value
     */
    // TODO write this function
    public void keepRange(E a, E b) {
    }

    //PRIVATE

    /**
     * Build a NON BST tree by preorder
     *
     * @param arr    nodes to be added
     * @param height maximum height of tree
     * @param parent parent of subtree to be created
     * @return new tree
     */
    private BinaryNode<E> buildTree(ArrayList<E> arr, int height, BinaryNode<E> parent) {
        if (arr.isEmpty()) return null;
        BinaryNode<E> curr = new BinaryNode<>(arr.remove(0), null, null, parent);
        if (height > 0) {
            curr.left = buildTree(arr, height - 1, curr);
            curr.right = buildTree(arr, height - 1, curr);
        }
        return curr;
    }

    /**
     * Internal method to insert into a subtree.
     * In tree is balanced, this routine runs in O(log n)
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<E> bstInsert(E x, BinaryNode<E> t, BinaryNode<E> parent) {
        if (t == null)
            return new BinaryNode<>(x, null, null, parent);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = bstInsert(x, t.left, t);
        } else {
            t.right = bstInsert(x, t.right, t);
        }

        return t;
    }


    /**
     * Internal method to find an item in a subtree.
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree: a=1, b=2, k=0
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     *          SIDE EFFECT: Sets local variable curr to be the node that is found
     * @return node containing the matched item.
     */
    private boolean bstContains(E x, BinaryNode<E> t) {
        curr = null;
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return bstContains(x, t.left);
        else if (compareResult > 0)
            return bstContains(x, t.right);
        else {
            curr = t;
            return true;    // Match
        }
    }



    /**
     * Internal method to return a string of items in the tree in order
     * This routine runs in O(??)
     * @param t the node that roots the subtree.
     */
    private String toString2(BinaryNode<E> t) {
        if (t == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(toString2(t.left));
        sb.append(t.element.toString() + " ");
        sb.append(toString2(t.right));
        return sb.toString();
    }

    // Basic node stored in unbalanced binary  trees
    private static class BinaryNode<AnyType> {
        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
        BinaryNode<AnyType> parent; //  Parent node

        // Constructors
        BinaryNode(AnyType theElement) {
            this(theElement, null, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt, BinaryNode<AnyType> pt) {
            element = theElement;
            left = lt;
            right = rt;
            parent = pt;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Node:");
            sb.append(element);
            if (parent == null) {
                sb.append("<>");
            } else {
                sb.append("<");
                sb.append(parent.element);
                sb.append(">");
            }

            return sb.toString();
        }

    }


    // Test program
    public static void main(String[] args) {
        long seed = 436543;
        Random generator = new Random(seed);  // Don't use a seed if you want the numbers to be different each time
        final String ENDLINE = "\n";

        int val = 60;
        final int SIZE = 8;

        Integer[] v1 = {25, 10, 60, 55, 58, 56, 14, 63, 8, 50, 6, 9};
        ArrayList<Integer> v2 = new ArrayList<Integer>();
        for (int i = 0; i < SIZE * 2; i++) {
            int t = generator.nextInt(200);
            v2.add(t);
        }
        v2.add(val);
        Integer[] v3 = {200, 15, 3, 65, 83, 70, 90};
        ArrayList<Integer> v4 = new ArrayList<Integer>(Arrays.asList(v3));
        Integer[] v = {21, 8, 5, 6, 7, 19, 10, 40, 43, 52, 12, 60};
        ArrayList<Integer> v5 = new ArrayList<Integer>(Arrays.asList(v));
        Integer[] inorder = {4, 2, 1, 7, 5, 8, 3, 6};
        Integer[] preorder = {1, 2, 4, 3, 5, 7, 8, 6};


        Tree<Integer> tree1 = new Tree<Integer>(v1, "Tree1:");
        Tree<Integer> tree2 = new Tree<Integer>(v2, "Tree2:");
        Tree<Integer> tree3 = new Tree<Integer>(v3, "Tree3:");
        Tree<Integer> treeA = new Tree<Integer>(v4, "TreeA:", 2);
        Tree<Integer> treeB = new Tree<Integer>(v5, "TreeB", 3);
        Tree<Integer> treeC = new Tree<Integer>("TreeC");
//        System.out.println(tree1.toString());
//        System.out.println(tree1.toString2());
//
//        System.out.println(treeA.toString());
//
//        treeA.flip();
//        System.out.println("Now flipped\n" + treeA.toString());
//
//        System.out.println(tree2.toString());
//        tree2.contains(val);  //Sets the current node inside the tree6 class.
//        int succCount = 6;  // how many successors do you want to see?
//        System.out.println("In Tree2, starting at " + val + ENDLINE);
//        for (int i = 0; i < succCount; i++) {
//            System.out.println("The next successor is " + tree2.successor());
//        }

        System.out.println(tree1.toString());
        for (int mylevel = 0; mylevel < SIZE; mylevel += 2) {
            System.out.println("Number nodes at level " + mylevel + " is " + tree1.nodesInLevel(mylevel));
        }
        System.out.println("All paths from tree1");
        tree1.printAllPaths();

//        System.out.println(tree1.toString());
//        System.out.print("Tree1 byLevelZigZag: ");
//        tree1.byLevelZigZag(5);
//        System.out.print("Tree2 byLevelZigZag (3): ");
//        tree2.byLevelZigZag(3);
        treeA.flip();
        System.out.println("tree A");
        System.out.println(treeA.toString());
        System.out.println("treeA Contains BST: " + treeA.countBST());

        System.out.println("tree B");
        System.out.println(treeB.toString());
        System.out.println("treeB Contains BST: " + treeB.countBST());

        System.out.println("Pruning tree B");
        treeB.pruneK(60);
        treeB.changeName("treeB after pruning 60");
        System.out.println(treeB.toString());
        System.out.println("Pruning tree A");
        treeA.pruneK(220);
        treeA.changeName("treeA after pruning 220");
        System.out.println(treeA.toString());

//        treeC.buildTreeTraversals(inorder, preorder);
//        treeC.changeName("Tree C built from inorder and preorder traversals");
//        System.out.println(treeC.toString());
//
//        System.out.println(tree1.toString());
//        System.out.println("tree1 Least Common Ancestor of (56,61) " + tree1.lca(56, 61) + ENDLINE);
//
//        System.out.println("tree1 Least Common Ancestor of (6,25) " + tree1.lca(6, 25) + ENDLINE);
//        System.out.println(tree3.toString());
//        tree3.balanceTree();
//        tree3.changeName("tree3 after balancing");
//        System.out.println(tree3.toString());
//
//        System.out.println(tree1.toString());
//        tree1.keepRange(10, 50);
//        tree1.changeName("tree1 after keeping only nodes between 10 and 50");
//        System.out.println(tree1.toString());
//        tree3.keepRange(3, 85);
//        tree3.changeName("tree3 after keeping only nodes between 3  and 85");
//        System.out.println(tree3.toString());


    }

}
