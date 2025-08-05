import java.util.Arrays;

public class BubbleSort {
    public static void bubbleSort(int[] arr){
        int n = arr.length;
        int i = n;
        while (i > 0){
            for (int j = 0; j < i - 1; j++){
                if (arr[j] > arr[j + 1]){
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
            i --;
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("排序前: " + Arrays.toString(arr));

        bubbleSort(arr);

        System.out.println("排序后: " + Arrays.toString(arr));
    }
}
