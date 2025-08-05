import java.util.Arrays;

public class InsertionSort {
    public static void insertSort(int[] arr){
        int n = arr.length;
        for (int i = 1; i < n; i++){ // start from the second term
            int tmp = arr[i]; 
            int j;
            for (j = i - 1; j >= 0 && arr[j] > tmp; j--){ // find the positon to insert from rightr to left
                arr[j + 1] = arr[j]; // once the smaller one is founded, stop, insert, 这里其实吧更大的向右，也可以理解为吧该数字的插入位置向左移动
            }
            arr[j + 1] = tmp; 
        }
    }

    public static void insertSortRecursion(int[] arr, int n){
        if (n == 1){
            return;
        }

        insertSortRecursion(arr, n - 1);
        
        int tmp = arr[n - 1];
        int j = n - 2;

        while (j >= 0 && arr[j] > tmp){
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("排序前: " + Arrays.toString(arr));

        insertSortRecursion(arr, arr.length);

        System.out.println("排序后: " + Arrays.toString(arr));
    }
}
