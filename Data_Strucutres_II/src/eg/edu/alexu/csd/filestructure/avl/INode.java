package eg.edu.alexu.csd.filestructure.avl;

public interface INode<T extends Comparable<T>> {
    INode<T> getLeftChild();

    void setLeftChild(INode<T> node);

    INode<T> getRightChild();

    void setRightChild(INode<T> node);

    T getValue();

    void setValue(T value);

    int getHeight();

    void setHeight(int height);
}
