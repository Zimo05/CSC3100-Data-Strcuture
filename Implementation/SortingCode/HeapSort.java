public class HeapSort {

    public static void buidHeap(int[] arr){
        int n = arr.length;
        for (int i = n / 2; i >= 0; i--){
            heapify(arr, i);
        }

    }
    public static void heapify(int[] arr, int parent){
        int parent1 = parent;
        int left = parent * 2 + 1;
        int right = parent * 2 + 2;

        if (left < arr.length && arr[left] > arr[parent]){
            parent = left;
        }
        if (right < arr.length && arr[right] > arr[parent]){
            parent = right;
        }

        if (parent != parent1){
            swap(arr, parent, parent1);
            heapify(arr, parent);
        }
    }
    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = {4, 10, 3, 5, 1};
        System.out.println("Original array:");
        printArray(arr);

        buidHeap(arr);

        System.out.println("Array after heapify:");
        printArray(arr);
    }
}
