package sortAlgorithms;

public class NSquaredSort {
    public static <T extends Comparable<T>> void insertionSort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[j].compareTo(array[j - 1]) < 0) {
                swap(array, j, --j);
            }
        }
    }

    public static <T extends Comparable<T>> void bubbleSort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
                if (array[j].compareTo(array[j - 1]) < 0) {
                    swap(array, j, j - 1);
                }
            }
        }
    }

    public static <T extends Comparable<T>> void selectionSort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            int pivot = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[pivot].compareTo(array[j]) > 0) {
                    pivot = j;
                }
            }
            swap(array, i, pivot);
        }
    }

    private static <T> void swap(T array[], int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}
