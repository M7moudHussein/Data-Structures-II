package eg.edu.alexu.csd.filestructure.avl.tester;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.impl.AVLTree;

public class AVLTest {
    private static final Random RANDOM = new Random();

    @Test
    public void testAVLTree() {
	int testSize = RANDOM.nextInt(100);
	int elementsSize = RANDOM.nextInt(10000);
	for (int j = 0; j < testSize; j++) {
	    IAVLTree<Integer> actual = new AVLTree<Integer>();
	    Set<Integer> expected = new TreeSet<Integer>();
	    for (int i = 0; i < elementsSize; i++) {
		int temp = RANDOM.nextInt();
		expected.add(temp);
		actual.insert(temp);
		if (RANDOM.nextBoolean())
		    assertEquals(expected.contains(temp), actual.search(temp));
		temp = RANDOM.nextInt();
		if (RANDOM.nextBoolean())
		    assertEquals(expected.remove(temp), actual.delete(temp));
		if (RANDOM.nextBoolean())
		    assertEquals(expected.contains(temp), actual.search(temp));
	    }
	}
    }

    @Test
    public void testIterator() {
	Set<Integer> expected = new TreeSet<Integer>();
	AVLTree<Integer> actual = new AVLTree<Integer>();
	int testSize = RANDOM.nextInt(1000000);
	for (int i = 0; i < testSize; i++) {
	    int element = RANDOM.nextInt();
	    actual.insert(element);
	    expected.add(element);
	}
	assertEquals(new ArrayList<>(expected), actual.toList());
    }
}
