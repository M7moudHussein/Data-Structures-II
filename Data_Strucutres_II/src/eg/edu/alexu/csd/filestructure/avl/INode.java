package eg.edu.alexu.csd.filestructure.avl;

public interface INode<T extends Comparable<T>> {
    INode<T> getLeftChild();

    INode<T> getRightChild();

    T getValue();

    int getHeight();

    void setValue(T value);

    void setRightChild(INode<T> node);

    void setLeftChild(INode<T> node);

    void setHeight(int height);
}
