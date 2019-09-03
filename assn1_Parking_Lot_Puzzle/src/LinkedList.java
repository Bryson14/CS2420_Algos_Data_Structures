public class LinkedList {

    private String message;
    private LinkedList next;
    private LinkedList previous;
    private String name;

    LinkedList(String message, String name, LinkedList previous, LinkedList next) {
        this.message = message;
        this.name = name;
        this.previous = previous;
        this.next = null;
    }
    public boolean addNext(LinkedList next) {
        try {
            this.next = next;
            return true;
        }
        catch (NullPointerException e) {
            System.out.println("Pointer to null linked list node");
            return false;
        }
    }

    public boolean addPrevious(LinkedList previous) {
        try {
            this.previous = previous;
            return true;
        }
        catch (NullPointerException e) {
            System.out.println("Pointer to null linked list node");
            return false;
        }
    }
}
