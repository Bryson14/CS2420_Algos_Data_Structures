public class LinkedList {

    private Node head, tail;
    private int size;

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
        }
            this.tail = node;
    }
    protected int getSize() {
        return size;
    }

    public void getNext(int i) {
        return;
    }
}
