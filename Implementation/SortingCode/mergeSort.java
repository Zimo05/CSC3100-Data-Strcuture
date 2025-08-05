import java.util.Arrays;

public class mergeSort {
    public static void mergesort1(int[] arr, int[] tmp, int left, int right){
        if (left == right){
            return;
        }

        int mid = (left + right) / 2;
        mergesort1(arr, tmp, left, mid);
        mergesort1(arr, tmp, mid + 1, right);
        merge1(arr, tmp, left, right, mid);
    }
    public static void merge1(int[] arr, int[] tmp, int left, int right, int mid){
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right){
            if (arr[i] < arr[j]){
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }
        while (i <= mid){
            tmp[k++] = arr[i++];
        }
        while (j <= right){
            tmp[k++] = arr[j++];
        }
        for (int m = left; m <= right; m ++) {
            arr[m] = tmp[m];
        }
    }


    public static int[] mergesort2(int[] arr, int left, int right){
        if (left == right){
            return new int[] { arr[left] };
        }

        int mid = (left + right) / 2;
        int[] leftArr = mergesort2(arr, left, mid);
        int[] rightArr = mergesort2(arr, mid + 1, right);
        return merge2(leftArr, rightArr);
    }
    public static int[] merge2(int[]leftArr, int[] rightArr){
        int[] merged = new int[leftArr.length + rightArr.length];
        int i = 0; // for leftArr
        int j = 0; // for rightArr
        int k = 0; // for merged
        while (i < leftArr.length && j < rightArr.length){
            if (leftArr[i] < rightArr[j]){
                merged[k++] = leftArr[i++];
            } else {
                merged[k++] = rightArr[j++];
            }
        }
        while (i < leftArr.length){
            merged[k++] = leftArr[i++];
        }
        while (j < rightArr.length){
            merged[k++] = rightArr[j++];
        }
        return merged;
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        int n = arr.length;
        int[] tmp = new int[n];
        System.out.println("排序前: " + Arrays.toString(arr));

        mergesort1(arr, tmp, 0, n - 1);

        System.out.println("排序后: " + Arrays.toString(arr));
    }
    
}
