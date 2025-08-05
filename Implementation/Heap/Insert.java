package Heap;
class Heap{
    int[] heap;
    int size;

    Heap(int capacity) {
        heap = new int[capacity];
        int size = 0;
    }
}

class Insert {
    int parent(int ind){
        return (ind - 1) / 2;
    }

    int left(int ind){
        return 2 * ind + 1;
    }

    int right(int ind){
        return 2 * ind + 2;
    }

    void heapify(Heap H, int ind){
        int[] heap = H.heap;
        while (heap[ind] < heap[parent(ind)]){
            swap(heap, ind, parent(ind));

            ind = parent(ind);
        }
    }
    void insert(Heap H, int x){
        int size = H.size;
        if (size == H.heap.length){
            System.out.println("full");
            return;
        }

        H.heap[size] = x;
        size++;

        heapify(H, size - 1);

    }

    void swap(int[] heap, int i, int j){
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}
