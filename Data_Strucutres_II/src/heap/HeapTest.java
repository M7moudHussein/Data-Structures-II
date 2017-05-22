package heap;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class HeapTest {
    private static final int MAX_NUMBER_OF_TEST_CASES = 10000000;

    @Test
    public void testHeap() {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());
        MaxPriorityQueue<Integer> maxQueue = new MaxPriorityQueue<Integer>();
        Random rand = new Random();
        for (int i = 0; i < rand.nextInt(MAX_NUMBER_OF_TEST_CASES); i++) {
            int randomNum = rand.nextInt();
            queue.add(randomNum);
            maxQueue.add(randomNum);
        }
        while (!queue.isEmpty() && !maxQueue.isEmpty()) {
            assertEquals(queue.size(), maxQueue.size());
            assertEquals(queue.peek(), maxQueue.getMax());
            assertEquals(queue.poll(), maxQueue.poll());
            assertEquals(queue.size(), maxQueue.size());
            assertEquals(queue.isEmpty(), maxQueue.isEmpty());
        }
        assertEquals(queue.isEmpty(), maxQueue.isEmpty());
    }

    @Test
    public void testHeapBuilder() {
        HeapBuilder<Integer> builder = new HeapBuilder<>();
        Integer[] array = new Integer[]{0, 8, 3, -1, 15, -20, 17};
        builder.buildHeap(array);
        System.out.println(Arrays.asList(array));
    }
}
