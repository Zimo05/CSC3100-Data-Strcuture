import java.util.*;

public class BucketSort {
    public static void bucketSort(float[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }

        float min = arr[0];
        float max = arr[0];
        for (float num : arr) {
            if (num < min) min = num;
            if (num > max) max = num;
        }

        int bucketCount = arr.length;
        List<Float>[] buckets = new List[bucketCount];

        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (float num : arr) {
            int bucketIndex = (int) ((num - min) / (max - min) * (bucketCount - 1)); // int is imoplement floar fnction
            buckets[bucketIndex].add(num);
        }
        for (List<Float> bucket : buckets) {
            Collections.sort(bucket);
        }
        int index = 0;
        for (List<Float> bucket : buckets) {
            for (float num : bucket) {
                arr[index++] = num;
            }
        }
    }

    public static void main(String[] args) {
        float[] arr = {543.0f, 435.0f, 234.0f, 123.0f, 345.0f, 456.0f, 654.0f, 765.0f, 876.0f, 987.0f};
        bucketSort(arr);
        System.out.println(Arrays.toString(arr)); 
    }
}
