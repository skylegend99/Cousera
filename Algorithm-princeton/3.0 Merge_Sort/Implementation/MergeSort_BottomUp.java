// Merge smaller array first. Get larger array
// Merge subarrays of size 1. Repeat subarrays of size 2.4.8.16.....
// Example:   1 1 1 1 1 1 1 1
//             2   2   2   2
//                4     4
//                    8
// 多次遍历数组并把子数组大小两两归并，子数组大小初始值为1，每次翻倍。
// 最后一个子数组的大小只有在数字大小是sz的偶数倍时候才等于sz（否则必sz小）

public class MergeSort_BottomUp {

    private static Comparable[] aux;

    public static void sort(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz+sz){  // sz is the size of sub array
            for (int lo = 0; lo < N-sz; lo += sz+sz) { // lo is the index of sub array
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
        }
    }



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
