package sortAlgorithms;

public class HeapSorter<T extends Comparable<T>> {

    public void heapSort(T[] array) {
        buildHeap(array);
        int size = array.length;
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            maxheapify(array, 0, --size);
        }
    }

    private void buildHeap(T[] array) {
        for (int i = array.length / 2; i >= 0; i--) {
            maxheapify(array, i, array.length);
        }
    }

    private void maxheapify(T[] array, int index, int size) {
        int target = index;
        int left = getLeft(index);
        int right = getRight(index);
        if (left < size && array[left].compareTo(array[index]) > 0) {
            target = left;
        }
        if (right < size && array[right].compareTo(array[target]) > 0) {
            target = right;
        }
        if (target != index) {
            swap(array, target, index);
            maxheapify(array, target, size);
        }
    }

    private void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private int getLeft(int index) {
        return (index << 1) + 1;
    }

    private int getRight(int index) {
        return (index << 1) + 2;
    }
}
