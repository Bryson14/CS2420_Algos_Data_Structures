package assn6_hex;

public class HexBoardFunctions {

    HexBoardFunctions () {

    }

    boolean checkWinner(UpTree player, int[] edge1, int[] edge2) {
        for (int a : edge2) {
            if (player.find(a) != 0) { //a is in tree
                for (int b: edge1) {
                    if (player.find(a) == player.find(b)) { //both a and b belong to same tree
                        return true;
                    }
                }
            }
        }
        return false;
    }

    int[] getNeighbors(int a) {
        if (a == 1) {
            int[] arr = {2,12};
            return arr;
        }
        else if (a == 11) {
            int[] arr = {10,21,22};
            return arr;
        }
        else if (a == 111) {
            int[] arr = {100,101,112};
            return arr;
        }
        else if (a == 121) {
            int[] arr = {110, 120};
            return arr;
        }
        else if (a < 12) { //top edge
            int[] arr = {a-1, a+1, a+10, a+11};
            return arr;
        }
        else if (a > 110) {// bottom edge
            int[] arr = {a-11, a-10, a - 1, a + 1};
            return arr;
        }
        else if (a % 11 == 0) {//right edge
            int[] arr = {a-11, a - 1, a+10, a+11};
            return arr;
        }
        else if ((a - 1) % 11 == 0) { //left edge
            int[] arr = {a - 11 , a - 10, a + 1, a + 11};
            return arr;
        }
        else {
            int[] arr = {a-11, a-10 ,a-1, a+1, a+10, a+11};
            return arr;
        }
    }
}
