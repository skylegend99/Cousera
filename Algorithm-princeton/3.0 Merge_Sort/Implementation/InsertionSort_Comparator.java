class InsertionSort_Comparator{
    public static void Insertion(Comparable[] a){
        int N = a.length;
        for (int i = 0; i<N;i++){
            for(int j = i; j>0 && less(a[j],a[j-1]);j--){
                exch(a,j,j-1);
            }
        }
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
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
        Insertion(a);
        for (int i = 0; i < 11; i++) {
            System.out.println(a[i]);
        }
    }
}