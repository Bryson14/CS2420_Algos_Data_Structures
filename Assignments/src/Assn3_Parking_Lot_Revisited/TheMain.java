package Assn3_Parking_Lot_Revisited;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 * The main begins by reading in
 * all of the puzzles described in a file named jams.txt.
 * It then proceeds to run a brute force solution., In each case, it prints out the solution
 * path that was computed.
 */

public class TheMain {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // read all the puzzles in file.  Only the first few are solvable without additional strategies
        System.out.println(System.getProperty("user.dir"));
        Puzzle[] puzzles = Puzzle.readPuzzlesFromFile("src\\Assn3_Parking_Lot_Revisited\\jamsAll.txt");
        int num_puzzles = puzzles.length;
//        int num_puzzles = 7;

        long startTime = System.currentTimeMillis();
        boolean doPrint = false;
        // solve each of the first six puzzles.  The others will likely take too long
        for (int i = 0; i < num_puzzles; i++) {
            puzzles[i].solve(doPrint);
            System.out.println("solved Problem" + i);
        }
        System.out.printf("\n%.3f seconds to solve %d Problems", (double)(System.currentTimeMillis() - startTime)/1000, num_puzzles);
    }

}
