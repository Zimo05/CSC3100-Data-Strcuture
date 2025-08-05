import java.util.Arrays;

public class ShellSort {
    
    public static void shellSort(int[] arr){
        int n = arr.length;
        int gap = n / 2;

        while (gap > 0){
            for (int i = gap; i < n; i += gap){
                int key = arr[i];
                int j;
                for (j = i - gap; j >=0 && key < arr[j]; j -= gap){
                    arr[j + gap] = arr[j];
                }

                arr[j + gap] = key;
            }
            gap /= 2;
        }
    }

    public static void shellSortRec(int[] arr, int gap){
        if (gap <= 0){
            return;
        }

        for (int i = gap; i < arr.length; i += gap){
            int key = arr[i];
            int j;
            for (j = i - gap; j >= 0 && arr[j] > key; j -= gap){
                arr[j + gap] = arr[j];
            }
            arr[j + gap] = key;
        }

        shellSortRec(arr, gap / 2);
    }

    public static void main(String[] args) {
        int[] arr = {25, 64, 12, 22, 11};
        System.out.println("排序前: " + Arrays.toString(arr));

        shellSortRec(arr, arr.length / 2);

        System.out.println("排序后: " + Arrays.toString(arr));
    }
}
