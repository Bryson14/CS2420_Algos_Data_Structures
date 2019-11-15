package assn6_hex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HexGame {


    public void tryUnion(int x) {
        int[] neighborIdx = {x-1, x+1,x-11, x-10, x+10, x+11};
    }

    public static void main(String[] args) {
        Scanner move1;
        int[] edgeLeft, edgeRight, edgeTop, edgeBottom;

        edgeLeft = new int[11];
        edgeRight = new int[11];
        edgeTop = new int[11];
        edgeBottom = new int[11];

        for (int i = 0; i < 11; i++) {
            edgeLeft[2*i] = i * 11 + 1;
            edgeRight[2*i+1] = i * 11 + 11;
        }

        for (int i = 0; i < 11; i++) {
            edgeTop[2*i] = i + 1;
            edgeBottom[2*i+1] = i + 111;
        }

        try {
            String sep = System.getProperty("file.separator");
            String file1 = sep + "Assignments" + sep + "src" + sep + "assn6_hex" + sep + "moves.txt";
            String dir = System.getProperty("user.dir");
            move1 = new Scanner(new File(dir + file1));

            UpTree playerBlue = new UpTree();
            UpTree playerRed = new UpTree();

            boolean turns = true;
            while(move1.hasNextInt()) {
                int a = move1.nextInt();
                int[] neighborIdx = {a-1, a+1,a-11, a-10, a+10, a+11};
                if (turns) {
                    playerBlue.addRoot(a);
                    playerBlue.tryUnion(a, neighborIdx);
                    checkWinner(playerBlue, true);
                } else {
                    playerRed.addRoot(a);
                    playerRed.tryUnion(a, neighborIdx);
                    checkWinner(playerRed, false);
                }
                turns ^= true;
            }

            System.out.println(playerBlue.printList());
            System.out.println("");
            System.out.println(playerRed.printList());

        } catch (FileNotFoundException e) {

        }

    }
    public static boolean checkWinner(UpTree player, boolean isBluePlayer) {

        return true;
    }
}
