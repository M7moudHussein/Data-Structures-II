package sortAlgorithms;

import java.util.Arrays;
import java.util.Random;

public class SortingRunner {
    private static final Random RANDOM = new Random();
    private static Integer[] testCase;
    private Integer[] backUp;

    public SortingRunner(int size) {
	initializeTestCase(size, 1000000);
	backUp = testCase.clone();

    }

    private void initializeTestCase(int size, int MAX_VALUE) {
	testCase = new Integer[size];
	for (int i = 0; i < size; i++) {
	    testCase[i] = RANDOM.nextInt(MAX_VALUE);
	}
    }

    private void initializeTestCase(int size) {
	testCase = new Integer[size];
	for (int i = 0; i < size; i++) {
	    testCase[i] = RANDOM.nextInt();
	}
    }

    public void useHeapSort() {
	System.arraycopy(backUp, 0, testCase, 0, backUp.length);
	long start = System.currentTimeMillis();
	NLogNSort.heapSort(testCase);
	long end = System.currentTimeMillis();
	System.out.println("HeapSort\t" + backUp.length + "\t" + (end - start));
    }

    public void useQuickSort() {
	System.arraycopy(backUp, 0, testCase, 0, backUp.length);
	long start = System.currentTimeMillis();
	NLogNSort.quickSort(testCase);
	long end = System.currentTimeMillis();
	System.out.println("QuickSort\t" + backUp.length + "\t" + (end - start));
    }

    public void useMergeSort() {
	System.arraycopy(backUp, 0, testCase, 0, backUp.length);
	long start = System.currentTimeMillis();
	NLogNSort.mergeSort(testCase);
	long end = System.currentTimeMillis();
	System.out.println("MergeSort\t" + backUp.length + "\t" + (end - start));
    }

    public void useInsertionSort() {
	System.arraycopy(backUp, 0, testCase, 0, backUp.length);
	long start = System.currentTimeMillis();
	NSquaredSort.insertionSort(testCase);
	long end = System.currentTimeMillis();
	System.out.println("InsertionSort\t" + backUp.length + "\t" + (end - start));
    }

    public void useBubbleSort() {
	System.arraycopy(backUp, 0, testCase, 0, backUp.length);
	long start = System.currentTimeMillis();
	NSquaredSort.bubbleSort(testCase);
	long end = System.currentTimeMillis();
	System.out.println("BubbleSort\t" + backUp.length + "\t" + (end - start));
    }

    public void useSelectionSort() {
	System.arraycopy(backUp, 0, testCase, 0, backUp.length);
	long start = System.currentTimeMillis();
	NSquaredSort.selectionSort(testCase);
	long end = System.currentTimeMillis();
	System.out.println("SelectionSort\t" + backUp.length + "\t" + (end - start));
    }

    public void useCountSort() {
	System.arraycopy(backUp, 0, testCase, 0, backUp.length);
	long start = System.currentTimeMillis();
	LinearSort.countSort(testCase);
	long end = System.currentTimeMillis();
	System.out.println("CountSort\t" + backUp.length + "\t" + (end - start));
    }

    public void useRadixSort() {
	System.arraycopy(backUp, 0, testCase, 0, backUp.length);
	long start = System.currentTimeMillis();
	LinearSort.radixSort(testCase);
	long end = System.currentTimeMillis();
	System.out.println("RadixSort\t" + backUp.length + "\t" + (end - start));
    }

    public static void main(String[] args) {
	int size = 10;
	SortingRunner testApp = new SortingRunner(size);
	System.out.println(Arrays.asList(testCase));
	System.out.println();
	System.out.println("Type\t\tSize\tTime");
	System.out.println();
	testApp.useCountSort();
	testApp.useRadixSort();

	testApp.useHeapSort();
	testApp.useQuickSort();
	testApp.useMergeSort();

	testApp.useBubbleSort();
	testApp.useInsertionSort();
	testApp.useSelectionSort();
	System.out.println();

	System.out.println(Arrays.asList(testCase));

    }

}
