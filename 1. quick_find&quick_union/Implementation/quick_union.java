import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class quick_union {
    private int[] id; // initial id that will contain all the value
    private int count; //count how many id will be stored in the list

    //initial how many value will be stored in array id
    public quick_union(int N) {
        count = N; // how many id (points) intotal
        id = new int[N]; // give array id "N" numbers to be stored
        for (int i = 0; i < N; i++) {
            // each time use "for loop" to create a space for each value
            id[i] = i;
        }
    }
    public int count(){
        return count;
    }  //count how many value we stored

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    } // test if two value have the same root

    public int find(int p){
        while (p != id[p]) p = id[p]; //find the root value if current != current.nextvalue
        return p;
    }

    //connect to root of objects if they are not connected
    public void union(int p,int q){
        int pid = find(p);
        int qid = find(q); // find the root value
        if (pid == qid) return; // if they have the same root
        id[pid] = qid; //connect their root
        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt(); //read how many values
        quick_union qf = new quick_union(N); // initialize N values
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt(); //read two values
            if(qf.connected(p,q)) continue;
            qf.union(p,q);
            StdOut.println(p+"" + q);
        }
        StdOut.println(qf.count()+"components");
    }
}
