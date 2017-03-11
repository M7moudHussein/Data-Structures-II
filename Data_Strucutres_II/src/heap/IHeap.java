package heap;

public interface IHeap<T> {

    public void add(T object);

    public T getMax();

    public T poll();

    public int size();

    public boolean isEmpty();
}
