package assn4_hashing;

import java.io.IOException;
import java.util.Scanner;

public class ReviewRater {

    public static void main(String[] args) {
        try {
            String workingDir = "Assignments\\src\\assn4_hashing\\";
            Reviews r1 = new Reviews();
            r1.readReviews(workingDir + "movieReviews.txt");

            Scanner s = new Scanner(System.in);

            System.out.println("Please enter a movie review...:");
            String myReview = s.nextLine();
            String[] words = myReview.split("\\s+");
            double myTotalScore = 0.0;

            for (int i = 0; i < words.length; i++) {
                    double ave = r1.getWordScore(words[i].toLowerCase());
                    if (ave >= 0.0) {
                        myTotalScore += ave;
                    }
            }

            System.out.printf("Score For Review Calculated At %.2f\n", myTotalScore/words.length);

            if (myTotalScore/words.length <= 1.75) System.out.println("Negative");
            else if (myTotalScore/words.length <= 2.25) System.out.println("Neutral");
            else System.out.println("Positive");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
