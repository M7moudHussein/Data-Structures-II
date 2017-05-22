import java.util.Collection;
import java.util.List;

public interface PerfectHashTable {

    public void build();

    public void addAll(Collection<Integer> collection);

    public void addAll(List<Integer> list);

    public void addAll(Integer[] array);

    public boolean contains(Integer number);

    public void clear();

    public int fullSize();

    public int size();
}
