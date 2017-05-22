import java.util.*;

public class QuadraticHashTable implements PerfectHashTable {
    private static final Random RANDOM = new Random();
    private List<Integer> hashTable;
    private Integer[] h;
    private boolean containsNull;
    private List<Integer> keys;
    private int size;

    QuadraticHashTable() {
        containsNull = false;
        size = 0;
        keys = new ArrayList<>();
    }

    @Override
    public void build() {
        int b = (int) (Math.log10(Math.pow(keys.size(), 2)) / Math.log10(2) + 1);
        hashTable = new ArrayList<>();
        for (int i = 0; i < 1 << b; i++) hashTable.add(null);
        boolean hasCollisions;
        int cnt = 0;
        do {
            cnt++;
            buildH(b);
            hasCollisions = false;
            for (Integer number : keys) {
                if (number == null && !containsNull) {
                    containsNull = true;
                    size++;
                } else {
                    int index = getHashCode(number);
                    if (index >= hashTable.size() || index < 0) throw new RuntimeException(index + " key Out Of Bound");
                    else {
                        if (hashTable.get(index) != null) {
                            if (!hashTable.get(index).equals(number)) {
                                hasCollisions = true;
                                break;
                            }
                        } else {
                            hashTable.set(index, number);
                            size++;
                        }
                    }
                }
            }
            if (hasCollisions) clearHashTable();
        } while (hasCollisions);
        System.out.println("Rebuilt\t" + cnt + " times");
    }

    public void add(Integer number) {
        keys.add(number);
    }

    private void clearHashTable() {
        for (int i = 0; i < hashTable.size(); i++) hashTable.set(i, null);
    }

    private int getHashCode(Integer number) {
        int hashCode = 0;
        for (int i = 0; i < h.length; i++) {
            if ((number & (1 << i)) != 0) hashCode = hashCode ^ h[i];
        }
        return hashCode;
    }

    private void buildH(int b) {
        h = new Integer[32];
        for (int i = 0; i < h.length; i++) h[i] = RANDOM.nextInt(1 << (b - 1));
    }

    @Override
    public boolean contains(Integer key) {
        if (hashTable.isEmpty()) throw new RuntimeException("HashTable must be built before being used!");
        if (key == null) return containsNull;
        else {
            int hashcode = getHashCode(key);
            if (hashcode >= hashTable.size()) {
                throw new RuntimeException("Key Out of Bound");
            } else {
                return hashTable.get(hashcode) != null && hashTable.get(hashcode).equals(key);
            }
        }
    }

    @Override
    public void clear() {
        if (hashTable != null) hashTable.clear();
        if (h != null) h = null;
        if (keys != null) keys.clear();
        size = 0;
    }

    @Override
    public void addAll(Collection<Integer> arr) {
        keys.addAll(arr);
    }

    @Override
    public void addAll(List<Integer> arr) {
        keys.addAll(arr);
    }

    @Override
    public void addAll(Integer[] numbers) {
        keys.addAll(Arrays.asList(numbers));
    }

    @Override
    public int fullSize() {
        return hashTable == null ? 0 : hashTable.size();
    }

    @Override
    public int size() {
        return this.size;
    }
}