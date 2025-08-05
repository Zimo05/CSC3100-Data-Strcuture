
import java.util.Arrays;

public class CountingSort {
    public static int[] countingSortArray(int[] arr){
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();

        int[] bucket = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++){
            bucket[arr[i] - min] ++;
        }

        int[] sortedArr = new int[arr.length];
        int index = 0;
        for (int i = 0; i < bucket.length; i ++){
            while (bucket[i] > 0){
                sortedArr[index] = i + min;
                index++;
                bucket[i]--;
            }
        }
        return sortedArr;
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("排序前: " + Arrays.toString(arr));

        int[] sorted = countingSortArray(arr);

        System.out.println("排序后: " + Arrays.toString(sorted));
    }
}

