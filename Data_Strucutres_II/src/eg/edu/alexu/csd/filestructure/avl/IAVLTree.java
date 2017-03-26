package eg.edu.alexu.csd.filestructure.avl;

public interface IAVLTree<T extends Comparable<T>> {
    void insert(T key);

    boolean delete(T key);

    boolean search(T key);

    int height();

    INode<T> getTree();
}
