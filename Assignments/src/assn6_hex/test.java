package assn6_hex;

import java.util.ArrayList;

public class test {

    public static void main(String[] args) {

        UpTree u = new UpTree();
        u.addRoot(2);
        u.addRoot(1);
        u.addRoot(5);
        u.addRoot(8);
        u.addRoot(7);
        u.union(2,5);
        u.union(7, 8);
        u.union(7,5);
        u.union(1, 5);
        System.out.println(u.printList());
    }
}
