package perfectHashing;

import java.util.Random;

public abstract class PerfectHashTable {
    protected static final Random RANDOM = new Random();

    protected long a, b, p;

    public abstract boolean contains(Integer key);

    public abstract void clear();

    public abstract int fullSize();

    public abstract int size();

    public abstract void build();

    public abstract int getRebuildTimes();
}
