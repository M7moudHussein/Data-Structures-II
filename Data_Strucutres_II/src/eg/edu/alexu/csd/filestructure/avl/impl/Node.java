package eg.edu.alexu.csd.filestructure.avl.impl;

import eg.edu.alexu.csd.filestructure.avl.INode;

public class Node<T extends Comparable<T>> implements INode<T> {
    private INode<T> leftChild, rightChild;
    private T value;
    private Integer height;

    public Node(T value) {
        this.setValue(value);
        this.setHeight(0);
        this.setLeftChild(null);
        this.setRightChild(null);
    }

    public INode<T> getLeftChild() {
        return this.leftChild;
    }

    @Override
    public void setLeftChild(INode<T> node) {
        this.leftChild = node;
    }

    public INode<T> getRightChild() {
        return this.rightChild;
    }

    @Override
    public void setRightChild(INode<T> node) {
        this.rightChild = node;
    }

    public T getValue() {
        return this.value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

}
