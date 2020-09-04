import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class path_compresss_weighted_qu {
    private int[] id; // initial id that will contain all the value
    private int[] sz; // size of each tree
    private int count; //count how many id will be stored in the list

    //initial how many value will be stored in array id
    public path_compresss_weighted_qu(int N) {
        count = N; // how many id (points) intotal
        id = new int[N]; // give array id "N" numbers to be stored
        for (int i = 0; i < N; i++) id[i] = i; // each time use "for loop" to create a space for each value

        for (int i = 0; i<N; i++) sz[i] = i; // each time use "for loop" to create size for each small tree
    }
    public int count(){
        return count;
    }  //count how many value we stored

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    } // test if two value have the same root

    public int find(int p){
        while (p != id[p]) {
            id[p] = id[id[p]];
            p = id[p]; //current = current.nextvalue
        }
        return p;
    }

    //connect to root of objects if they are not connected
    public void union(int p,int q){
        int pid = find(p);
        int qid = find(q); // find the root value
        if (pid == qid) return; // if they have the same root
        if (sz[pid] < sz[qid]) { //determine the size of each tree and put the small tree under the large tree
            id[pid] = qid;
            sz[qid] += sz[pid];
        } else {
            id[qid] = pid;
            sz[pid] += sz[qid];
        }
        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt(); //read how many values
        path_compresss_weighted_qu wqn = new path_compresss_weighted_qu(N); // initialize N values
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt(); //read two values
            if(wqn.connected(p,q)) continue;
            wqn.union(p,q);
            StdOut.println(p+"" + q);
        }
        StdOut.println(wqn.count()+"components");
    }
}
