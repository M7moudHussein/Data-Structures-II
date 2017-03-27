package eg.edu.alexu.csd.filestructure.avl.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.INode;

public class AVLTree<T extends Comparable<T>> implements IAVLTree<T> {
    private INode<T> root;

    public AVLTree() {
	root = null;
    }

    public void insert(T key) {
	root = insert(root, key);
    }

    private INode<T> insert(INode<T> node, T key) {
	if (node == null) {
	    return new Node<T>(key);
	}
	if (node.getValue().compareTo(key) > 0) {
	    node.setLeftChild(insert(node.getLeftChild(), key));
	} else if (node.getValue().compareTo(key) < 0) {
	    node.setRightChild(insert(node.getRightChild(), key));
	} else {
	    return node;
	}
	updateHeight(node);
	return balance(node);
    }

    @Override
    public boolean delete(T key) {
	boolean isPresent = search(key);
	if (isPresent) {
	    root = delete(root, key);
	}
	return isPresent;
    }

    private INode<T> delete(INode<T> node, T key) {
	if (node.getValue().compareTo(key) > 0) {
	    node.setLeftChild(delete(node.getLeftChild(), key));
	} else if (node.getValue().compareTo(key) < 0) {
	    node.setRightChild(delete(node.getRightChild(), key));
	} else {
	    if (node.getLeftChild() != null && node.getRightChild() != null) {
		T minOfRgtSubTree = getMin(node.getRightChild());
		node.setValue(minOfRgtSubTree);
		node.setRightChild(delete(node.getRightChild(), minOfRgtSubTree));
	    } else if (isLeaf(node)) {
		return null;
	    } else if (node.getRightChild() != null) {
		node = node.getRightChild();
	    } else {
		node = node.getLeftChild();
	    }
	}
	updateHeight(node);
	return balance(node);
    }

    private boolean isLeaf(INode<T> node) {
	return node.getRightChild() == null && node.getLeftChild() == null;
    }

    private T getMin(INode<T> node) {
	while (node.getLeftChild() != null) {
	    node = node.getLeftChild();
	}
	return node.getValue();
    }

    @Override
    public boolean search(T key) {
	return search(root, key);
    }

    private boolean search(INode<T> node, T key) {
	if (node == null) {
	    return false;
	}
	if (node.getValue().compareTo(key) > 0) {
	    return search(node.getLeftChild(), key);
	} else if (node.getValue().compareTo(key) < 0) {
	    return search(node.getRightChild(), key);
	} else {
	    return true;
	}
    }

    @Override
    public int height() {
	return isEmpty() ? -1 : root.getHeight() + 1;
    }

    @Override
    public INode<T> getTree() {
	return root;
    }

    public boolean isEmpty() {
	return root == null;
    }

    private void updateHeight(INode<T> node) {
	Integer leftHeight = node.getLeftChild() == null ? -1 : node.getLeftChild().getHeight();
	Integer rightHeight = node.getRightChild() == null ? -1 : node.getRightChild().getHeight();
	node.setHeight(Math.max(leftHeight, rightHeight) + 1);
    }

    private Integer getBalanceFactor(INode<T> node) {
	Integer leftHeight = node.getLeftChild() == null ? -1 : node.getLeftChild().getHeight();
	Integer rightHeight = node.getRightChild() == null ? -1 : node.getRightChild().getHeight();
	return leftHeight - rightHeight;
    }

    private INode<T> leftRotate(INode<T> node) {
	INode<T> right = node.getRightChild();
	INode<T> leftOfRight = right.getLeftChild();
	right.setLeftChild(node);
	node.setRightChild(leftOfRight);
	updateHeight(node);
	updateHeight(right);
	return right;
    }

    private INode<T> rightRotate(INode<T> node) {
	INode<T> left = node.getLeftChild();
	INode<T> rightOfLeft = left.getRightChild();
	left.setRightChild(node);
	node.setLeftChild(rightOfLeft);
	updateHeight(node);
	updateHeight(left);
	return left;
    }

    private INode<T> balance(INode<T> node) {
	Integer balanceFactor = getBalanceFactor(node);
	if (balanceFactor < -1) {
	    if (getBalanceFactor(node.getRightChild()) <= 0) {
		return leftRotate(node);
	    } else {
		node.setRightChild(rightRotate(node.getRightChild()));
		return leftRotate(node);
	    }
	} else if (balanceFactor > 1) {
	    if (getBalanceFactor(node.getLeftChild()) >= 0) {
		return rightRotate(node);
	    } else {
		node.setLeftChild(leftRotate(node.getLeftChild()));
		return rightRotate(node);
	    }
	}
	return node;
    }

    public Iterator<T> iterator() {
	return new TreeIterator();
    }

    public List<T> toList() {
	List<T> list = new ArrayList<T>();
	Iterator<T> it = new TreeIterator();
	while (it.hasNext()) {
	    list.add(it.next());
	}
	return list;
    }

    private class TreeIterator implements Iterator<T> {
	private Stack<INode<T>> stack;

	public TreeIterator() {
	    stack = new Stack<INode<T>>();
	    INode<T> it = root;
	    while (it != null) {
		stack.push(it);
		it = it.getLeftChild();
	    }
	}

	@Override
	public boolean hasNext() {
	    return !stack.isEmpty();
	}

	@Override
	public T next() {
	    INode<T> ret = stack.pop();
	    if (ret.getRightChild() != null) {
		stack.push(ret.getRightChild());
		while (stack.peek().getLeftChild() != null) {
		    stack.push(stack.peek().getLeftChild());
		}
	    }
	    return ret.getValue();
	}

    }
}
