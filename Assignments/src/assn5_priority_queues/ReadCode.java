package assn5_priority_queues;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


public class ReadCode {
    public static void main(String[] args) {
        try {
            String sep = System.getProperty("file.separator");
            String filename = sep +"Assignments" +sep + "src" +sep+ "assn5_priority_queues" +sep+ "SortedWords.txt";
            String dir = System.getProperty("user.dir");
            Scanner reader = new Scanner( new File( dir + filename ) );
            ArrayList<Term> words = new ArrayList<>();

            while ((reader.hasNext())) {
                String word = reader.next();
                long freq = reader.nextLong();
                words.add(new Term(word, freq));
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}