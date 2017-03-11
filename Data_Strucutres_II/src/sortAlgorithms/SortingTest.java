package sortAlgorithms;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class SortingTest {
    private static final Random RANDOM = new Random();
    private Integer[] actual;
    private Integer[] expected;

    @Test
    public void testHeapSort() {
	reset(RANDOM.nextInt(1000000), Integer.MAX_VALUE);
	HeapSorter<Integer> sorter = new HeapSorter<Integer>();
	sorter.heapSort(expected);
	assertArrayEquals(actual, expected);
    }

    @Test
    public void testQuickSort() {
	reset(RANDOM.nextInt(1000000), Integer.MAX_VALUE);
	NLogNSort.quickSort(expected);
	assertArrayEquals(actual, expected);
    }

    @Test
    public void testMergeSort() {
	reset(RANDOM.nextInt(1000000), Integer.MAX_VALUE);
	NLogNSort.mergeSort(expected);
	assertArrayEquals(actual, expected);
    }

    @Test
    public void testInsertionSort() {
	reset(RANDOM.nextInt(5000), Integer.MAX_VALUE);
	NSquaredSort.insertionSort(expected);
	assertArrayEquals(actual, expected);
    }

    @Test
    public void testBubbleSort() {
	reset(RANDOM.nextInt(5000), Integer.MAX_VALUE);
	NSquaredSort.bubbleSort(expected);
	assertArrayEquals(actual, expected);
    }

    @Test
    public void testSelectionSort() {
	reset(RANDOM.nextInt(5000), Integer.MAX_VALUE);
	NSquaredSort.selectionSort(expected);
	assertArrayEquals(actual, expected);
    }

    @Test
    public void testCountSort() {
	reset(1000000, 1000000);
	LinearSort.countSort(expected);
	assertArrayEquals(actual, expected);

    }

    @Test
    public void testRadixSort() {
	reset(1000000, 1000000);
	LinearSort.radixSort(expected);
	assertArrayEquals(actual, expected);
    }

    private void reset(int size, int maxValue) {
	actual = new Integer[size];
	for (int i = 0; i < actual.length; i++) {
	    actual[i] = RANDOM.nextInt(maxValue);
	}
	expected = actual.clone();
	Arrays.sort(actual);
    }
}
