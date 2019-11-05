package assn5_priority_queues;

public class Term implements Comparable<Term> {

    private long freq;
    private String word;

    Term(String word, long freq){
        this.word = word;
        this.freq = freq;
    }

    public String toString(){
        return "Wt: " + freq + "\t " + word + "\n";
    }

    public int compareTo(Term t2){
        if (this.freq == t2.freq) return 0;
        else if (this.freq < t2.freq) return -1;
        return 1;
     }

    public String getWord() {
        return word;
    }
}