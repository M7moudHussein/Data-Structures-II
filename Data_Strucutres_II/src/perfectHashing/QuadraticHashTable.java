import java.util.Random;

public class QuadraticHashTable {
    private static final Random RANDOM = new Random();
    private Integer[] hashTable;
    private Integer[] h;
    private boolean containsNull;
    private int b;
    private Integer[] array;

    QuadraticHashTable(Integer[] array) {
        this.array = array.clone();
        containsNull = false;
        buildTable();
    }

    private void buildTable() {
        b = (int) (Math.log10(array.length * array.length) / Math.log10(2) + 1);
        boolean hasCollisions;
        do {
            hashTable = new Integer[array.length * array.length];
            hasCollisions = false;
            buildH();
            for (Integer number : array) {
                if (number == null) {
                    containsNull = true;
                } else {
                    int index = getHashCode(number);
                    if (index >= hashTable.length || index < 0) {
                        throw new RuntimeException(index + "key Out Of Bound");
                    } else {
                        if (hashTable[index] != null) {
                            if (!hashTable[index].equals(number)) {
                                hasCollisions = true;
                                break;
                            }
                        } else {
                            hashTable[index] = number;
                        }
                    }
                }
            }
        } while (hasCollisions);
    }

    private int getHashCode(Integer number) {
        int hashCode = 0;
        for (int i = 0; i < h.length; i++) {
            if ((number & (1 << i)) != 0) {
                hashCode = hashCode ^ h[i];
            }
        }
        return hashCode;
    }

    private void buildH() {
        h = new Integer[32];
        for (int i = 0; i < h.length; i++) {
            h[i] = RANDOM.nextInt(1 << (b - 1));
        }
    }

    public boolean contains(Integer key) {
        if (key == null) {
            return containsNull;
        } else {
            int hashcode = getHashCode(key);
            if (hashcode >= hashTable.length) {
                throw new RuntimeException("Key Out of Bound");
            } else {
                return hashTable[hashcode] != null && hashTable[hashcode].equals(key);
            }
        }
    }

    public Integer[] getTable() {
        return this.array;
    }
}