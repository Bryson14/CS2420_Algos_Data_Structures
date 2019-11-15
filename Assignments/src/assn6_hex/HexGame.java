package assn6_hex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class HexGame {

    public static void main(String[] args) {

        Scanner move1;
        int[] edgeLeft, edgeRight, edgeTop, edgeBottom;

        // Finds which spots are edges for future reference
        edgeLeft = new int[11];
        edgeRight = new int[11];
        edgeTop = new int[11];
        edgeBottom = new int[11];
        boolean won = false;

        for (int i = 0; i < 11; i++) {
            edgeLeft[i] = i * 11 + 1;
            edgeRight[i] = i * 11 + 11;
        }

        for (int i = 0; i < 11; i++) {
            edgeTop[i] = i + 1;
            edgeBottom[i] = i + 111;
        }

        try {
            String sep = System.getProperty("file.separator");
            String file1 = sep + "Assignments" + sep + "src" + sep + "assn6_hex" + sep + "moves.txt";
            String dir = System.getProperty("user.dir");
            move1 = new Scanner(new File(dir + file1));

            UpTree playerBlue = new UpTree();
            UpTree playerRed = new UpTree();
            HashSet<Integer> takenSpots = new HashSet<>();

            boolean turns = true;

            while(move1.hasNextInt()) {
                int a = move1.nextInt();
                if (!takenSpots.contains(a)) { //not allowing players to override taken spots
                    int[] neighborIdx = getNeighbors(a);
                    if (turns) {
                        playerBlue.addRoot(a);
                        playerBlue.tryUnion(a, neighborIdx);
                        if (checkWinner(playerBlue, edgeLeft, edgeRight)) {
                            System.out.println("Blue player wins!");
                            won = true;
                            break;
                        }
                    } else {
                        playerRed.addRoot(a);
                        playerRed.tryUnion(a, neighborIdx);
                        if (checkWinner(playerBlue, edgeTop, edgeBottom)) {
                            System.out.println("Red player wins!");
                            won = true;
                            break;
                        }
                    }
                } else {
                    System.out.printf("Spot %d has already been taken! Changing turns.\n", a);
                }
                takenSpots.add(a);

                turns ^= true;
            }

            System.out.println("\n Blue Players Array\n" + playerBlue.printHexBoard());
            System.out.println("\n Red Players Array\n" + playerRed.printList());

        } catch (FileNotFoundException e) {
            System.out.println("File not found\n" + e);
        }
        if (!won) System.out.println("Sorry no winners :(");
    }

    private static boolean checkWinner(UpTree player, int[] edge1, int[] edge2) {
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

    private static int[] getNeighbors(int a) {
        if (a == 1) {
            int[] arr = {1,12};
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
            int[] arr = {120, 110};
            return arr;
        }
        else if (a < 12) { //top edge
            int[] arr = {a-1, a+1, a+10, a+11};
            return arr;
        }
        else if (a > 110) {// bottom edge
            int[] arr = {a-1, a+1,a-11, a-10};
            return arr;
        }
        else if (a % 11 == 0) {//right edge
            int[] arr = {a-1,a-11, a+10, a+11};
            return arr;
        }
        else if ((a - 1) % 11 == 0) { //left edge
            int[] arr = { a+1,a-11, a-10, a+11};
            return arr;
        }
        else {
            int[] arr = {a-1, a+1,a-11, a-10, a+10, a+11};
            return arr;
        }
    }
}
