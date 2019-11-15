package assn6_hex;

import java.util.ArrayList;

public class test {

    public static void main(String[] args) {

        UpTree u = new UpTree();
        u.addRoot(2);
        u.addRoot(1);
        ArrayList<Integer> lst = new ArrayList<>();
        lst.add(0);
        lst.add(-1);
        lst.add(-1);
        System.out.println(u.printList());
        System.out.println(lst.equals(u.getPaths()));
    }
}
