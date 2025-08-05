import java.util.Arrays;

public class quickSort {
    public static void quicksort(int[] arr, int left, int right){
        if (left >= right){
            return;
        }

        int pivot = (left + right) / 2;
        int pivotPos = partition(arr, left, right, pivot);

        quicksort(arr, left, pivotPos - 1);
        quicksort(arr, pivotPos + 1, right);
    }
    public static int partition(int[] arr ,int left, int right, int pivot){
        int pivotVal = arr[pivot];  
        int newPivotPos = left;
        arr[pivot] = arr[right];
        arr[right] = pivotVal;
        for (int i = left; i <= right - 1; i ++){
            if (arr[i] < pivotVal){
                int tmp = arr[newPivotPos];
                arr[newPivotPos] = arr[i];
                arr[i] = tmp;
                newPivotPos++;
            }
        }
        arr[right] = arr[newPivotPos];
        arr[newPivotPos] = pivotVal;
        return newPivotPos;
    }

    public static void median3(int[] arr, int left, int right){
        if (left >= right){
            return;
        }
        int mid = (left + right) / 2;
        int[] tmp = {arr[left], arr[mid], arr[right]};
        InsertionSort.insertSort(tmp);
        arr[left] = tmp[0];
        arr[mid] = tmp[1];
        arr[right] = tmp[2];

        int newPivotPos = partition2(arr, left, right, mid);
        median3(arr, left, newPivotPos - 1);
        median3(arr, newPivotPos + 1, right);
    }
    public static int partition2(int[] arr, int left, int right, int pivot){
        int pivotVal = arr[pivot];

        arr[pivot] = arr[right - 1];
        arr[right - 1] = pivotVal;

        int i = left + 1;
        int j = right - 2;

        while (i <= j) { 
            if (arr[i] < pivotVal){
                i ++;
            } else if (arr[j] > pivotVal){
                j --;
            } else {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i ++;
                j --;
            }
        }
        int temp = arr[i];
        arr[i] = arr[right - 1];
        arr[right - 1] = temp;
        return i;
    }

    void quickSortIterative(int[] arr, int low, int high) {
        int[] stack = new int[high - low + 1];
        int top = -1;
        stack[++top] = low;
        stack[++top] = high;

        while(top >= 0){
            high = stack[top--];
            low = stack[top--];

            int p = partitionStack(arr, low, high);

            if (p - low > high - p){
                if(p - 1 > low){
                    stack[++top] = low;
                    stack[++top] = p - 1;
                }
                low = p + 1;
            } 
            else{
                if (p + 1 < high){
                    stack[++top] = p + 1;
                    stack[++top] = high;
                }
                high = p - 1;
            }
            if (low < high) {
                stack[++top] = low;
                stack[++top] = high;
            }
        }
    }

    int partitionStack(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        int n = arr.length;
        System.out.println("排序前: " + Arrays.toString(arr));

        median3(arr, 0, n - 1);

        System.out.println("排序后: " + Arrays.toString(arr));
    }
}
