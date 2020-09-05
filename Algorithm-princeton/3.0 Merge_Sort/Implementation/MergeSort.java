//Basic plan of merge sort:
//Divide array into two halves
//Recursively sort each half
//Merge two halves

//Operation Complexity: at most Nlog(N) compares and 6Nlog(N) array accesses.
public class MergeSort {
    private static Comparable[] aux;


//    We are give two sorted subarrays a[lo] to a[mid] and a[mid+1] to a[hi]
//    we need to replace with sorted subarray a[lo] to a[hi].
    public static void merge(Comparable[] a, int lo, int mid, int hi){
        //Merge a[lo...mid] and a[mid+1...hi] together
        //precondition: both subarrays are sorted.
        int i = lo, j = mid+1; //create two pointers that could be used to compare aux[i] and aux[j]
        for (int k= lo; k<= hi; k++){
            aux[k] = a[k]; //Copy all elements to aux[]
        }
        for (int k = lo; k <= hi; k++){
            if (i > mid) {  //Run out of the index of the first array
                a[k] = aux[j++];
            } else if (j > hi) { //Run out of the index of the second array
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {  //index on second array less than index on first array.
                a[k] = aux[j++]; //Abstract the element on the second array.
            } else {
                a[k] = aux[i++];
            }
        }
    }

    //Do not create aux[] array each time in the recursion
    //
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;;
        int mid = lo + (hi-lo) / 2;
        sort(a, lo, mid);
        sort(a,mid+1, hi);

        // what if the array is already sorted(Biggest item in first half smaller than the smallest item in second half.
        // We add:
        // if(!less(a[mid+1], a[mid])) return;

        merge(a, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a,0, a.length-1); // Recursion
    }
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    public static void main(String[] args) {
        Comparable[] a = new Comparable[11];

        // Put value inside a[]
        for (int i = 10,j = 0; i >= 0; i--, j++) {
            a[j] = i;
        }
        sort(a);


        for (int i = 0; i < 11; i++) {
            System.out.println(a[i]);
        }
    }

}
