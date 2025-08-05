import java.util.Arrays;

public class SelectionSort {
    
    public static void selectSort(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n - 1; i ++){
            int min = i;
            int key = arr[i];
            for (int j = i + 1; j < n; j ++){
                if (arr[j] < arr[min]){
                    min = j;
                }
            }

            arr[i] = arr[min];
            arr[min] = key;

        }
    }

    public static void selectRec(int[] arr, int i){
        int n = arr.length;
        if (i == n - 1) {
            return;
        }
        int min = i;
        for (int j = i + 1; j < n; j++){
            if (arr[j] < arr[min]) {
                min = j;
            }
        }
        int tmp = arr[i];
        arr[i] = arr[min];
        arr[min] = tmp;
        selectRec(arr, i + 1);
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("排序前: " + Arrays.toString(arr));
        selectRec(arr, 0);
        System.out.println("排序后: " + Arrays.toString(arr));
    }
}
