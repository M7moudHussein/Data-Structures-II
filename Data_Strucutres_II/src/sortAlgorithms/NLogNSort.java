package sortAlgorithms;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class NLogNSort {

    public static <T extends Comparable<T>> void heapSort(T[] array) {
	HeapSorter<T> heapSorter = new HeapSorter<T>();
	heapSorter.heapSort(array);
    }

    // =================================================================================
    // =================================================================================
    // =================================================================================
    // =================================================================================
    // =================================================================================
    // =================================================================================

    @SuppressWarnings("rawtypes")
    private static List temp;

    public static <T extends Comparable<T>> void mergeSort(T[] array) {
	temp = new LinkedList<T>();
	mergeSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void mergeSort(T[] array, int left, int right) {
	if (left >= right) {
	    return;
	}
	mergeSort(array, left, ((left + right) >>> 1));
	mergeSort(array, ((left + right) >>> 1) + 1, right);
	merge(array, left, (left + right) >>> 1, right);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void merge(T[] array, int left, int mid, int right) {
	temp.clear();
	int i = left;
	int j = mid + 1;
	while (i <= mid && j <= right) {
	    int comp = array[i].compareTo(array[j]);
	    if (comp < 0) {
		temp.add(array[i++]);
	    } else if (comp == 0) {
		temp.add(array[i++]);
		temp.add(array[j++]);
	    } else {
		temp.add(array[j++]);
	    }
	}
	while (j <= right) {
	    temp.add(array[j++]);
	}
	while (i <= mid) {
	    temp.add(array[i++]);
	}
	Iterator<T> it = temp.iterator();
	int index = left;
	while (it.hasNext()) {
	    array[index++] = it.next();
	}
    }

    // =================================================================================
    // =================================================================================
    // =================================================================================
    // =================================================================================

    public static <T extends Comparable<T>> void quickSort(T[] array) {
	quickSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] array, int left, int right) {
	if (left >= right) {
	    return;
	}
	int pivot = partition(array, left, right);
	quickSort(array, left, pivot - 1);
	quickSort(array, pivot + 1, right);
    }

    private static final Random RANDOM = new Random();

    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
	swap(array, left + RANDOM.nextInt(right - left + 1), right);
	T x = array[right];
	int i = left - 1;
	for (int j = left; j < right; j++) {
	    if (array[j].compareTo(x) <= 0) {
		i++;
		swap(array, i, j);
	    }
	}
	swap(array, i + 1, right);
	return i + 1;
    }

    private static <T> void swap(T[] array, int index1, int index2) {
	T temp = array[index1];
	array[index1] = array[index2];
	array[index2] = temp;
    }

}
