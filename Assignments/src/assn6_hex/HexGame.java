package assn6_hex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class HexGame {

    public static void main(String[] args) {

        int[] c = {1,2};
        int[] b = {1,2};
        System.out.println(Arrays.equals(c,b));

        Scanner move1;
        int[] edgeLeft, edgeRight, edgeTop, edgeBottom;

        // Finds which spots are edges for future reference
        edgeLeft = new int[11];
        edgeRight = new int[11];
        edgeTop = new int[11];
        edgeBottom = new int[11];
        boolean won = false;
        HexBoardFunctions hbf = new HexBoardFunctions();

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
                    int[] neighborIdx = hbf.getNeighbors((a));
                    if (turns) {
                        playerBlue.addRoot(a);
                        playerBlue.tryUnion(a, neighborIdx);
                        if (hbf.checkWinner(playerBlue, edgeLeft, edgeRight)) {
                            System.out.println("Blue player wins!");
                            won = true;
                            break;
                        }
                    } else {
                        playerRed.addRoot(a);
                        playerRed.tryUnion(a, neighborIdx);
                        if (hbf.checkWinner(playerBlue, edgeTop, edgeBottom)) {
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
            System.out.println("\n Red Players Array\n" + playerRed.printHexBoard());

        } catch (FileNotFoundException e) {
            System.out.println("File not found\n" + e);
        }
        if (!won) System.out.println("Sorry no winners :(");
    }

}
