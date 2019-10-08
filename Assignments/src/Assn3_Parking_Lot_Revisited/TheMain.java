package Assn3_Parking_Lot_Revisited;

import java.io.*;

/**
 * The main begins by reading in
 * all of the puzzles described in a file named jams.txt.
 * It then proceeds to run a brute force solution., In each case, it prints out the solution
 * path that was computed.
 */

public class TheMain {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        System.out.println(System.getProperty("user.dir"));
        Puzzle[] puzzles = Puzzle.readPuzzlesFromFile("src\\Assn3_Parking_Lot_Revisited\\jamsAll.txt");
//        int num_puzzles = puzzles.length;
        int num_puzzles = 7;

        boolean doPrint = true;
        boolean freakishlyFast = false;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < num_puzzles; i++) {
            puzzles[i].solve(doPrint, freakishlyFast);
            System.out.println("solved Problem" + i);
        }
        System.out.printf("\n%.3f seconds to solve %d Problems", (double)(System.currentTimeMillis() - startTime)/1000, num_puzzles);
    }

}