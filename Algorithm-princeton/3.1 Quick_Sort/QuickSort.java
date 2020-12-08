import edu.princeton.cs.algs4.StdRandom;
// Time complexity: usually requires 2Nlog(N)
//                  At most: (N^2)/2
public class QuickSort {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        // When we partition to the smallest array which contains only one element,return back.
        if (hi<= lo) {
            return;
        }
        // Divide the array into two sub-arrays.
        // J is the element not in half but make a[smaller than j] < a[j] < a[greater than j];
        int j = partition(a, lo, hi);
        sort(a, lo, j-1); // sort two sub-arrays.
        sort(a, j+1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi; // Define the low and high elements
        Comparable v = a[lo]; // Store the first element to separate the array into two part.
        while (true) {
            // While the element start from the left is less than v,
            // we increase the index until find the element that is larger than v.
            while (less(a[++i], v)) {
                if (i == hi) { break; }
            }
            // While the element start from the right is greater v,
            // we decrease the index until find the element that is smaller than v.
            while (less(v, a[--j])) {
                if (j == lo) { break; }
            }
            // When two index meet or pass each other: break.
            if (i >= j) break;
            exch(a, i, j); // exchange two element until the left side is always less than the right side.
        }
        exch(a, lo, j); // Exchange the first element with a[j], then partition process completed.
        return j; // Return the index of middle element for partition sub-arrays.
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
