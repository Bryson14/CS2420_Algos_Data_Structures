package assn1_Parking_Lot;

public class LinkedList {

    private Node head;
    private int size;
    private Node tail;
    private int count;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public LinkedList(Node node) {
        this.head = node;
        this.tail = node;
        this.size++;
    }

    public void add(Node node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.addNext(node);
            this.tail = node;
            this.size++;
        }
    }
    protected int getSize() {
        return size;
    }

    public Node getNext() {
        return getNextRecur(head, 0);
    }

    private Node getNextRecur(Node node, int times) {
        if (times == count) {
            count++;
            return node;
        } else {
            return getNextRecur(node.getNext(), ++times);
        }
    }
    public void resetCount() {
        count = 0;
    }
}
