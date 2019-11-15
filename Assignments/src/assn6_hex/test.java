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
        u.addRoot(9);
        u.union(2,5);
        u.union(8,1);
        u.union(9, 7);
        u.union(2,8);
        u.union(2,7);
        u.union(6,2);
        u.addRoot(2);
        u.pathCompressionFind(1);
        System.out.println(u.union(1, 4));
        System.out.println(u.printList());

        int[] edges = new int [22];
        for (int i = 0; i < 11; i++) {
            edges[2*i] = i + 1;
            edges[2*i+1] = i + 111;
        }

        for (int a : edges) {
            System.out.println(a);
        }
    }
}
