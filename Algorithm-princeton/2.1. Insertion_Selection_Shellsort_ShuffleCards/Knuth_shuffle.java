import edu.princeton.cs.algs4.StdRandom;
public class Knuth_shuffle {
    //In iteration i,pick integer r between 0 and i uniformly at random.
    public static void shuffle(int[] a){
        int N = a.length;
        for (int i= 0;i<N;i++){
            int r = StdRandom.uniform(i+1); //any number between 0 and i
            int temp = a[r];
            a[r] = a[i];
            a[i] = temp;
        }
    }

}
