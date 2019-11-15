package assn3_Parking_Lot_Revisited;

public class Dwarf implements Comparable<Dwarf>{
    private String data;
    private int id;
    static  int count = 0;
    public Dwarf(String x){
        data = x;
        id = count++;
    }
    @Override
    public int compareTo(Dwarf b2){
        return (this.data.compareTo( b2.data));
    }
    public String toString(){
        return  data + id;
    }
}

