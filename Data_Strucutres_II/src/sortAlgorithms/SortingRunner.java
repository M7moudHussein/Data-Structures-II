package sortAlgorithms;

import java.util.Random;

public class SortingRunner {
    private static final Random RANDOM = new Random();
    private Integer[] testCase;
    private Integer[] backUp;

    public SortingRunner(int size) {
	initializeTestCase(size);
	backUp = testCase.clone();

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

    public static void main(String[] args) {
	int size = 15000;
	SortingRunner testApp = new SortingRunner(size);
	testApp.useHeapSort();
	testApp.useQuickSort();
	testApp.useMergeSort();

	testApp.useBubbleSort();
	testApp.useInsertionSort();
	testApp.useSelectionSort();
    }

}
