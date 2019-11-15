package assn6_hex;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class UpTreeTest {

    @Test
    public void addRoot() {
        UpTree ut = new UpTree();
        ut.addRoot(-1);
        ut.addRoot(0);
        ArrayList<Integer> lst = new ArrayList<>();
        Assert.assertEquals(lst, ut.getPaths());
    }

    @Test
    public void addRoot2() {
        UpTree ut = new UpTree();
        ut.addRoot(1);
        ArrayList<Integer> lst = new ArrayList<>();
        lst.add(0);
        lst.add(-1);
        Assert.assertEquals(lst, ut.getPaths());
    }

    @Test
    public void addRoot3() {
        UpTree ut = new UpTree();
        ut.addRoot(1);
        ut.addRoot(2);
        ArrayList<Integer> lst = new ArrayList<>();
        lst.add(0);
        lst.add(-1);
        lst.add(-1);
        Assert.assertEquals(lst, ut.getPaths());
    }

    @Test
    public void addRoot4() {
        UpTree ut = new UpTree();
        ut.addRoot(1);
        ut.addRoot(1);
        ArrayList<Integer> lst = new ArrayList<>();
        lst.add(0);
        lst.add(-1);
        Assert.assertEquals(lst, ut.getPaths());
    }

    @Test
    public void addRoot5() {
        UpTree ut = new UpTree();
        ut.addRoot(100);
        ArrayList<Integer> lst = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            lst.add(0);
        }
        lst.add(-1);
        Assert.assertEquals(lst, ut.getPaths());
    }

    @Test
    public void union() {
        UpTree ut = new UpTree();
        ut.addRoot(2);
        ut.addRoot(1);
        ut.addRoot(5);
        ut.addRoot(8);
        ut.addRoot(7);
        ut.addRoot(9);
        ut.union(2,5);
        ut.union(5,7);
        ut.addRoot(2);
        ut.addRoot(-500);
        ut.union(-55, 9);
        ArrayList<Integer> lst = new ArrayList<>();
        lst.add(0);
        lst.add(-1);
        lst.add(-3);
        lst.add(0);
        lst.add(0);
        lst.add(2);
        lst.add(0);
        lst.add(2);
        lst.add(-1);
        lst.add(-1);
        Assert.assertEquals(lst, ut.getPaths());
    }

    @Test
    public void union2() {
        UpTree ut = new UpTree();
        ut.addRoot(2);
        ut.addRoot(1);
        ut.addRoot(3);
        ut.addRoot(4);
        ut.addRoot(5);
        ut.addRoot(6);
        ut.union(1,2);
        ut.union(3,4);
        ut.union(5,6);
        ut.union(1,3);
        ut.union(4,5);
        ArrayList<Integer> lst = new ArrayList<>();
        lst.add(0);
        lst.add(-6);
        lst.add(1);
        lst.add(1);
        lst.add(3);
        lst.add(1);
        lst.add(5);
        Assert.assertEquals(lst, ut.getPaths());
    }

    @Test
    public void pathCompressionFind() {
        UpTree ut = new UpTree();
        ut.addRoot(2);
        ut.addRoot(1);
        ut.addRoot(3);
        ut.addRoot(4);
        ut.addRoot(5);
        ut.addRoot(6);
        ut.union(1,2);
        ut.union(3,4);
        ut.union(5,6);
        ut.union(1,3);
        ut.union(4,5);
        ut.pathCompressionFind(6);
        ArrayList<Integer> lst = new ArrayList<>();
        lst.add(0);
        lst.add(-6);
        lst.add(1);
        lst.add(1);
        lst.add(3);
        lst.add(1);
        lst.add(1);
        Assert.assertEquals(lst, ut.getPaths());
    }

    @Test
    public void tryUnion() {
        UpTree ut = new UpTree();
        ut.addRoot(2);
        ut.addRoot(1);
        ut.addRoot(3);
        ut.addRoot(4);
        ut.addRoot(5);
        ut.addRoot(6);
        int[] lst1 = {0,1,2,3,8};
        ut.tryUnion(4, lst1);

        ArrayList<Integer> lst2 = new ArrayList<>();
        lst2.add(0);
        lst2.add(4);
        lst2.add(4);
        lst2.add(4);
        lst2.add(-4);
        lst2.add(-1);
        lst2.add(-1);
        Assert.assertEquals(lst2, ut.getPaths());
    }

    @Test
    public void find() {
        UpTree ut = new UpTree();
        ut.addRoot(1);
        ut.addRoot(2);
        ut.addRoot(3);
        ut.addRoot(4);
        ut.union(1,3);
        ut.union(4,1);
        Assert.assertNotEquals(ut.find(1),ut.find(2));
        Assert.assertEquals(ut.find(1), ut.find(3));
    }
}