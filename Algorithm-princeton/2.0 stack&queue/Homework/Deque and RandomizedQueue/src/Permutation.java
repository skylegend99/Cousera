import edu.princeton.cs.algs4.StdIn;
public class Permutation {
    public static void main(String[] args){
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randQueue = new RandomizedQueue<String>(k);
        while(!StdIn.isEmpty()){
            randQueue.enqueue(StdIn.readString());
        }
        while(k>0) {
            System.out.println(randQueue.dequeue());
            k--;
        }
    }
}