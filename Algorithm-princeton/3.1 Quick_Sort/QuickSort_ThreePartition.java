import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdRandom;
// Speed up the quicksort when there are a lot of duplicate keys.
// Implement 3 Way partition in quicksort.
public class QuickSort_ThreePartition {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        // When we partition to the smallest array which contains only one element,return back.
        if (hi<= lo) {
            return;
        }
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        // Achieve a[lo....lt-1] < v = a[lt.....gt] < a[gt+1...hi]
        while(i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp<0) exch(a, lt++, i++);
            else if (cmp >0) exch(a, i, gt--);
            else    i++;
        }
        sort(a, lo, lt-1); // sort two sub-arrays.
        sort(a, gt+1, hi);
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
