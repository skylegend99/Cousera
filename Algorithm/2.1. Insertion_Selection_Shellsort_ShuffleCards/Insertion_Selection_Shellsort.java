class sort{
    public static void Insertion(int[] a){
        int N = a.length;
        for (int i = 0; i<N;i++){
            for(int j = i; j>0;j--){
                if (a[j]<a[j-1]){
                    int temp = a[j-1];
                    a[j-1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
    public static void Selection(int[] a){
        for(int i =0;i<a.length;i++){
            int min = -1;
            for(int j = i;j<a.length;j++){
                if (a[j]<min){
                    min = j;
                }
            }
            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
    }

}


//Shell sort is like the insertion sort but less exchanges.
    public static void Shellsort(int[] a){
        int N = a.length;
        int h = 1;
        while(h<N/3) h-3*h+1;  //1,4,13,40,121------
        while (h>=1) {
            for(int i = h; i < N; i++){    //implementation is the insertion sort
                for (int j = i; j >= h && a[j] < a[j-h];j -= h){
                    int temp = a[i];
                    a[i] = a[j-h];
                    a[j-h] = temp;
                }
            }
            h = h / 3;  //decrease the size of step
        }











    }