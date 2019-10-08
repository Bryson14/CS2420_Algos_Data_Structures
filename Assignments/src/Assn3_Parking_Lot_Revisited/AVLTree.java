package Assn3_Parking_Lot_Revisited;

// AvlTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x (unimplemented)
// boolean contains( x )  --> Return true if x is present
// boolean remove( x )    --> Return true if x was present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an AVL tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class AVLTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public AVLTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param item the item to insert.
     */
    public void insert( AnyType item )
    {
        root = insert( item, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param item the item to remove.
     */
    public void remove( AnyType item )
    {
        root = remove( item, root );
    }


    /**
     * Internal method to remove from a subtree.
     * @param item the item to remove.
     * @param avlNode the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AvlNode<AnyType> remove( AnyType item, AvlNode<AnyType> avlNode )
    {
        if( avlNode == null )
            return avlNode;   // Item not found; do nothing

        int compareResult = item.compareTo( avlNode.element );

        if( compareResult < 0 )
            avlNode.left = remove( item, avlNode.left );
        else if( compareResult > 0 )
            avlNode.right = remove( item, avlNode.right );
        else if( avlNode.left != null && avlNode.right != null ) // Two children
        {
            avlNode.element = findMin( avlNode.right ).element;
            avlNode.right = remove( avlNode.element, avlNode.right );
        }
        else
            avlNode = ( avlNode.left != null ) ? avlNode.left : avlNode.right;
        return balance( avlNode );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new RuntimeException( );
        return findMin( root ).element;
    }


    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new RuntimeException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param item the item to search for.
     * @return true if x is found.
     */
    public boolean contains( AnyType item )
    {
        return contains( item, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( String label)
    {
        System.out.println(label);
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root,"" );
    }

    private static final int ALLOWED_IMBALANCE = 1;

    // Assume t is either balanced or within one of being balanced
    private AvlNode<AnyType> balance( AvlNode<AnyType> avlNode )
    {
        if( avlNode == null )
            return avlNode;

        if( height( avlNode.left ) - height( avlNode.right ) > ALLOWED_IMBALANCE )
            if( height( avlNode.left.left ) >= height( avlNode.left.right ) )
                avlNode = rightRotation( avlNode );
            else
                avlNode = doubleRightRotation( avlNode );
        else
        if( height( avlNode.right ) - height( avlNode.left ) > ALLOWED_IMBALANCE )
            if( height( avlNode.right.right ) >= height( avlNode.right.left ) )
                avlNode = leftRotation( avlNode );
            else
                avlNode = doubleLeftRotation( avlNode );

        avlNode.height = Math.max( height( avlNode.left ), height( avlNode.right ) ) + 1;
        return avlNode;
    }

    public void checkBalance( )
    {
        checkBalance( root );
    }

    private int checkBalance( AvlNode<AnyType> avlNode )
    {
        if( avlNode == null )
            return -1;

        if( avlNode != null )
        {
            int hl = checkBalance( avlNode.left );
            int hr = checkBalance( avlNode.right );
            if( Math.abs( height( avlNode.left ) - height( avlNode.right ) ) > 1 ||
                    height( avlNode.left ) != hl || height( avlNode.right ) != hr )
                System.out.println( "\n\n***********************OOPS!!" );
        }

        return height( avlNode );
    }


    /**
     * Internal method to insert into a subtree.  Duplicates are allowed
     * @param item the item to insert.
     * @param avlNode the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AvlNode<AnyType> insert( AnyType item, AvlNode<AnyType> avlNode )
    {
        if( avlNode == null )
            return new AvlNode<>( item, null, null ,null);

        int compareResult = item.compareTo( avlNode.element );

        if( compareResult < 0 )
            avlNode.left = insert( item, avlNode.left );
        else
            avlNode.right = insert( item, avlNode.right );

        return balance(avlNode);
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param avlNode the node that roots the tree.
     * @return node containing the smallest item.
     */
    private AvlNode<AnyType> findMin( AvlNode<AnyType> avlNode )
    {
        if( avlNode == null )
            return avlNode;

        while( avlNode.left != null )
            avlNode = avlNode.left;
        return avlNode;
    }

    /**
     * find the parent of desired node for deletion puposes
     * @currNode current node in the search
     * @param child the node to find the parent from
     * @return parent node of child
     */
    private AvlNode<AnyType> findParent(AvlNode<AnyType> child, AvlNode<AnyType> currNode) {
        if (child == null) return child; //child is null
        else if (currNode == null) return currNode; // no child found
        if (currNode.left == child || currNode.right == child) return currNode; //parent found
        else {
            currNode = (findParent(child, currNode.left) == null)? findParent(child, currNode.right) : findParent(child ,currNode.left);
            return currNode;
        }
    }

    /**
     * helper function for deleteMin recursion call
     */
    public  void  deleteMin( ){

        AvlNode<AnyType> min = findMin(root);
        root =  deleteMin(root, min);
    }

    /**
     * deletes the smallest node. If duplicates of the min exist, those will be deleted too.
     * @param avlNode
     * @return
     */
    private AvlNode<AnyType> deleteMin( AvlNode<AnyType> avlNode , AvlNode<AnyType> min) {
        if (avlNode == null) return null;
        if (avlNode.left == min) {
            avlNode.left = min.right;
            return balance(avlNode);
        } else {
            deleteMin(avlNode.left, min);
            return balance(avlNode);
        }
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param avlNode the node that roots the tree.
     * @return node containing the largest item.
     */
    private AvlNode<AnyType> findMax( AvlNode<AnyType> avlNode )
    {
        if( avlNode == null )
            return avlNode;

        while( avlNode.right != null )
            avlNode = avlNode.right;
        return avlNode;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param item is item to search for.
     * @param avlNode the node that roots the tree.
     * @return true if x is found in subtree.
     */
    private boolean contains( AnyType item, AvlNode<AnyType> avlNode )
    {
        while( avlNode != null )
        {
            int compareResult = item.compareTo( avlNode.element );

            if( compareResult < 0 )
                avlNode = avlNode.left;
            else if( compareResult > 0 )
                avlNode = avlNode.right;
            else
                return true;    // Match
        }

        return false;   // No match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param avlNode the node that roots the tree.
     */
    private void printTree( AvlNode<AnyType> avlNode, String indent )
    {
        if( avlNode != null )
        {
            printTree( avlNode.right, indent+"   " );
            System.out.println( indent+ avlNode.element + "("+ avlNode.height  +")" );
            printTree( avlNode.left, indent+"   " );
        }
    }

    /**
     * Return the height of node t, or -1, if null.
     */
    private int height( AvlNode<AnyType> avlNode )
    {   if (avlNode==null) return -1;
        return avlNode.height;
    }

    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> rightRotation(AvlNode<AnyType> avlNode )
    {
        AvlNode<AnyType> theLeft = avlNode.left;
        avlNode.left = theLeft.right;
        theLeft.right = avlNode;
        avlNode.height = Math.max( height( avlNode.left ), height( avlNode.right ) ) + 1;
        theLeft.height = Math.max( height( theLeft.left ), avlNode.height ) + 1;
        return theLeft;
    }

    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> leftRotation(AvlNode<AnyType> avlNode )
    {
        AvlNode<AnyType> theRight = avlNode.right;
        avlNode.right = theRight.left;
        theRight.left = avlNode;
        avlNode.height = Math.max( height( avlNode.left ), height( avlNode.right ) ) + 1;
        theRight.height = Math.max( height( theRight.right ), avlNode.height ) + 1;
        return theRight;
    }

    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> doubleRightRotation( AvlNode<AnyType> avlNode )
    {
        avlNode.left = leftRotation( avlNode.left );
        return rightRotation( avlNode );

    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child.
     * For AVL trees, this is a double rotation for case 3.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> doubleLeftRotation(AvlNode<AnyType> avlNode )
    {
        avlNode.right = rightRotation( avlNode.right );
        return leftRotation( avlNode );
    }

    private static class AvlNode<AnyType>
    {
        // Constructors
        AvlNode( AnyType theElement )
        {
            this( theElement, null, null , null);
        }

        AvlNode( AnyType theElement, AvlNode<AnyType> left, AvlNode<AnyType> right , AvlNode<AnyType> parent)
        {
            element  = theElement;
            this.left = left;
            this.right = right;
            height   = 0;
        }

        AnyType           element;      // The data in the node
        AvlNode<AnyType>  left;         // Left child
        AvlNode<AnyType>  right;        // Right child
        int               height;       // Height
    }

    /** The tree root. */
    private AvlNode<AnyType> root;


    // Test program
    public static void main( String [ ] args ) {
        AVLTree<Integer> tree1 = new AVLTree<>();
        AVLTree<Dwarf> tree2 = new AVLTree<>();

        String[] nameList = {"Snowflake", "Sneezy", "Doc", "Grumpy", "Bashful", "Dopey", "Happy", "Doc", "Grumpy", "Bashful", "Doc", "Grumpy", "Bashful"};
        for (int i=0; i < nameList.length; i++)
            tree2.insert(new Dwarf(nameList[i]));

        tree2.printTree( "The Tree" );

        tree2.remove(new Dwarf("Bashful"));

        tree2.printTree( "The Tree after delete Bashful" );
        for (int i=0; i < 8; i++) {
            tree2.deleteMin();
            tree2.printTree( "\n\n The Tree after deleteMin" );
        }
    }
}
