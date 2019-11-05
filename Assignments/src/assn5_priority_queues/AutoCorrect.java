package assn5_priority_queues;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;


public class AutoCorrect {
    public static void main(String[] args) {
        try {
            String sep = System.getProperty("file.separator");
            String filename = sep +"Assignments" +sep + "src" +sep+ "assn5_priority_queues" +sep+ "SortedWords.txt";
            String dir = System.getProperty("user.dir");
            Scanner reader = new Scanner( new File( dir + filename ) );
            List<Term> words = new ArrayList<>();

            while ((reader.hasNext())) {
                String word = reader.next();
                long freq = reader.nextLong();
                words.add(new Term(word, freq));
            }
            reader.close();

            Scanner user = new Scanner(System.in);
            System.out.println("Enter the beginning of some word and count: ");
            String prefix = user.next().toLowerCase();
            int count = user.nextInt();
            SkewHeap h = new SkewHeap();

            boolean end = false;
            boolean start = false;
            int j = 0;

            while (!end) {
                if (j >= words.size() - 1) {
                    end = true;
                }
                if (words.get(j).getWord().startsWith(prefix)) {
                    h.insert(words.get(j));
                    start = true;
                } else if (start) end = true;
                j++;
            }

            for (int i = 0 ; i < count; i++) {
                Term max = h.pop();
                if (max != null ) System.out.print(max);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}