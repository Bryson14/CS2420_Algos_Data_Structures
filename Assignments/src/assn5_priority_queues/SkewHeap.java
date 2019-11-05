package assn5_priority_queues;

class SkewHeap {

    private SkewNode root;

    class SkewNode implements Comparable<SkewNode>{
        Term value;
        SkewNode left;
        SkewNode right;

        SkewNode(Term value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        @Override
        public int compareTo(SkewNode o) {
            return (value.compareTo(o.value));
        }
    }

    SkewHeap() {
        this(null);
    }

    private SkewHeap(Term item) {
        root = new SkewNode(item);
    }

    void insert(Term value) {
        root = merge(new SkewNode(value), root);
    }

    //unceremoniously named beep and boop
    private SkewNode merge(SkewNode beep, SkewNode boop) {
        if (beep == null || beep.value == null) {
            return boop;
        }
        if (boop == null || boop.value == null) {
            return beep;
        }

        if (beep.value.compareTo(boop.value) > 0) { //new node has higher priority
            SkewNode temp = merge(beep.right, boop);
            beep.right = beep.left; //swap
            beep.left = temp;
            return beep;

        } else {
            SkewNode temp = merge(beep , boop.right); // they are the same value or new node has less priority
            boop.right = boop.left; // swap
            boop.left = temp;
            return boop;
        }
    }

    public Term pop() {
        if (root == null || root.value == null) {
            return null;
        }
        Term term = root.value;
        root = merge(root.left, root.right);
        return term;
    }
}
