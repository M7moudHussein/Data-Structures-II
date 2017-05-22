package perfectHashing;

import java.util.ArrayList;
import java.util.List;

public class QuadraticHashTable extends PerfectHashTable {
    private List<Integer> hashTable;
    private Integer[] h;
    private boolean containsNull;
    private List<Integer> keys;
    private int size;
    private int rebuildTimes = 0;

    QuadraticHashTable(List<Integer> keys) {
        containsNull = false;
        size = 0;
        this.keys = new ArrayList<>();
        this.keys.addAll(keys);
        rebuildTimes = 0;
    }

    public void build() {
        hashTable = new ArrayList<>();
        int b = (int) (Math.log10(Math.pow(keys.size(), 2)) / Math.log10(2) + 1);
        for (int i = 0; i < keys.size() * keys.size(); i++) hashTable.add(null);
        boolean hasCollisions;
        do {
            buildHMatrix(b);
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
            rebuildTimes++;
        } while (hasCollisions);
    }

    @Override
    public int getRebuildTimes() {
        return rebuildTimes;
    }

    private void clearHashTable() {
        for (int i = 0; i < hashTable.size(); i++) hashTable.set(i, null);
    }

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

    public void clear() {
        if (hashTable != null) hashTable.clear();
        if (h != null) h = null;
        if (keys != null) keys.clear();
        size = 0;
    }

    public int fullSize() {
        return keys.size() * keys.size();
    }

    public int size() {
        return this.size;
    }

    private int getHashCode(Integer number) {
        int hashCode = 0;
        for (int i = 0; i < h.length; i++) {
            if ((number & (1 << i)) != 0) hashCode = hashCode ^ h[i];
        }
        return hashCode;
    }

    private void buildHMatrix(int b) {
        h = new Integer[32];
        for (int i = 0; i < h.length; i++) h[i] = RANDOM.nextInt(1 << (b - 1));
    }
}