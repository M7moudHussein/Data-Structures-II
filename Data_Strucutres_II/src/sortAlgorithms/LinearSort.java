package sortAlgorithms;

import java.util.Arrays;

public class LinearSort {

    private static int getMaxValue(Integer[] array) {
	int max = Integer.MIN_VALUE;
	for (int i = 0; i < array.length; i++) {
	    max = Math.max(max, array[i]);
	}
	return max;
    }

    public static void countSort(Integer[] array) {
	int k = getMaxValue(array);
	int[] count = new int[k + 1];

	for (int i = 0; i < array.length; i++) {
	    count[array[i]]++;
	}

	for (int i = 1; i <= k; i++) {
	    count[i] += count[i - 1];
	}

	Integer[] output = new Integer[array.length];

	for (int i = array.length - 1; i >= 0; i--) {
	    output[--count[array[i]]] = array[i];
	}
	System.arraycopy(output, 0, array, 0, array.length);
    }

    public static void radixSort(Integer[] array) {
	Integer[] lastAns = array;
	int[] count = new int[2];
	Integer[] output = new Integer[array.length];
	for (int bitPlace = 0; bitPlace < 32; bitPlace++) {
	    Arrays.fill(count, 0);
	    for (int i = 0; i < array.length; i++) {
		count[getBit(array[i], bitPlace)]++;
	    }
	    for (int i = 1; i < count.length; i++) {
		count[i] += count[i - 1];
	    }

	    for (int i = array.length - 1; i >= 0; i--) {
		int bit = getBit(array[i], bitPlace);
		output[--count[bit]] = array[i];
	    }
	    Integer[] temp = output;
	    output = array;
	    array = temp;
	}
	System.arraycopy(array, 0, lastAns, 0, array.length);
    }

    private static int getBit(Integer number, int bitPlace) {
	if ((number & (1 << bitPlace)) == 0) {
	    return 0;
	} else {
	    return 1;
	}
    }
}
