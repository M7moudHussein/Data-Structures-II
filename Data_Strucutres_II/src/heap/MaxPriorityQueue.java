package heap;

import java.util.ArrayList;
import java.util.List;

public class MaxPriorityQueue<T extends Comparable<T>> implements IHeap<T> {
    private List<T> tree;

    public MaxPriorityQueue() {
	tree = new ArrayList<T>();
	tree.add(null);
    }

    @Override
    public void add(T object) {
	tree.add(object);
	int index = this.size();
	while (this.getParent(index) != 0 && tree.get(this.getParent(index)).compareTo(tree.get(index)) < 0) {
	    this.swap(index, this.getParent(index));
	    index = this.getParent(index);
	}
    }

    public void printTree() {
	for (int i = 1; i < tree.size(); i++) {
	    System.out.print(i + "\t");
	}
	System.out.println();
	for (int i = 1; i < tree.size(); i++) {
	    System.out.print(tree.get(i) + "\t");
	}
	System.out.println();
    }

    @Override
    public T getMax() {
	return this.size() == 0 ? null : tree.get(1);
    }

    @Override
    public T poll() {
	if (this.size() == 0) {
	    throw new RuntimeException("Queue is Empty");
	}
	T ret = tree.get(1);
	this.swap(1, this.size());
	tree.remove(this.size());
	this.heapify(1);
	return ret;
    }

    @Override
    public int size() {
	return tree.size() - 1;
    }

    @Override
    public boolean isEmpty() {
	return this.size() == 0;
    }

    public void buildHeap(T[] array) {
	for (int i = 0; i < array.length; i++) {
	    tree.add(array[i]);
	}
	int mid = array.length / 2;
	for (int i = mid; i > 0; i--) {
	    heapify(i);
	}
    }

    private int getLeftIndex(int index) {
	return index << 1;
    }

    private int getRightIndex(int index) {
	return (index << 1) + 1;
    }

    private int getParent(int index) {
	return index >>> 1;
    }

    private void heapify(int nodeIndex) {
	if (nodeIndex > this.size()) {
	    return;
	}
	int left = getLeftIndex(nodeIndex);
	int right = getRightIndex(nodeIndex);
	int target = nodeIndex;
	if (left <= this.size() && tree.get(nodeIndex).compareTo(tree.get(left)) < 0) {
	    target = left;
	}
	if (right <= this.size() && tree.get(target).compareTo(tree.get(right)) < 0) {
	    target = right;
	}
	if (target != nodeIndex) {
	    this.swap(nodeIndex, target);
	    this.heapify(target);
	}
    }

    private void swap(int index1, int index2) {
	T temp = tree.get(index1);
	tree.set(index1, tree.get(index2));
	tree.set(index2, temp);
    }
}
