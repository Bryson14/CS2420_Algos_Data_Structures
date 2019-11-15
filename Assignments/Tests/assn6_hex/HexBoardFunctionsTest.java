package assn6_hex;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class HexBoardFunctionsTest {

    @Test
    public void checkWinner() {
        HexBoardFunctions hbf = new HexBoardFunctions();

    }

    @Test
    public void getNeighbors() {
        HexBoardFunctions hbf = new HexBoardFunctions();
        int[] arrLT = {2,12};
        int[] arrRT = {10,21,22};
        int[] arrLB = {100,101,112};
        int[] arrRB = {110,120};
        Assert.assertTrue(Arrays.equals(arrLT, hbf.getNeighbors(1)));
        Assert.assertTrue(Arrays.equals(arrRT, hbf.getNeighbors(11)));
        Assert.assertTrue(Arrays.equals(arrLB, hbf.getNeighbors(111)));
        Assert.assertTrue(Arrays.equals(arrRB, hbf.getNeighbors(121)));
    }

    @Test
    public void getNeighbors2() {
        HexBoardFunctions hbf = new HexBoardFunctions();
        int[] arrL = {12,13,24,34};
        int[] arrR = {11,21,32,33};
        int[] arrT = {6, 8, 17, 18};
        int[] arrB = {108, 109, 118,120};
        Assert.assertTrue(Arrays.equals(arrL, hbf.getNeighbors(23)));
        Assert.assertTrue(Arrays.equals(arrR, hbf.getNeighbors(22)));
        Assert.assertTrue(Arrays.equals(arrT, hbf.getNeighbors(7)));
        Assert.assertTrue(Arrays.equals(arrB, hbf.getNeighbors(119)));
    }

    @Test
    public void getNeighbors3() {
        HexBoardFunctions hbf = new HexBoardFunctions();
        int[] arr1 = {2,3,12,14,23,24};
        int[] arr2 = {19,20,29,31,40,41};
        Assert.assertTrue(Arrays.equals(arr1, hbf.getNeighbors(13)));
        Assert.assertTrue(Arrays.equals(arr2, hbf.getNeighbors(30)));
    }
}