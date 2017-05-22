package perfectHashing;
import java.util.*;

public class QuadraticHashTable extends PerfectHashTable {
    private static final Random RANDOM = new Random();
    private List<Integer> hashTable;
    private Integer[] h;
    private boolean containsNull;
    private List<Integer> keys;
    private int size;
    
    QuadraticHashTable(List<Integer> keys) {
        containsNull = false;
        size = 0;
        this.keys = new ArrayList<>();
        this.keys.addAll(keys);
        makePrime(keys.size() * keys.size());
    }
    
    public void build() {
        hashTable = new ArrayList<>();
        for (int i = 0; i < keys.size() * keys.size(); i++) hashTable.add(null);
        boolean hasCollisions;
        int cnt = 0;
//        int i  =0 ;
        do {
            cnt++;
            buildHTable();
            hasCollisions = false;
            for (Integer number : keys) {
                if (number == null && !containsNull) {
                    containsNull = true;
                    size++;
                } else {
                    int index = getHashCode(number, keys.size() * keys.size());
                    if (index >= hashTable.size() || index < 0) throw new RuntimeException(index + " key Out Of Bound");
                    else {
                        if (hashTable.get(index) != null) {
                            if (!hashTable.get(index).equals(number)) {
                                hasCollisions = true;
//                                if(i == 0)
//                                System.out.println(number + " " + index + " "+ a + " " + b + " " + p + " " + keys.size());
//                                i++;
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
    }

    private void clearHashTable() {
        for (int i = 0; i < hashTable.size(); i++) hashTable.set(i, null);
    }
    
    public boolean contains(Integer key) {
        if (hashTable.isEmpty()) throw new RuntimeException("HashTable must be built before being used!");
        if (key == null) return containsNull;
        else {
            int hashcode = getHashCode(key, keys.size() * keys.size());
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
}