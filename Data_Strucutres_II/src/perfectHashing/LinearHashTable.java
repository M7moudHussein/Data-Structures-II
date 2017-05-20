import java.util.Random;

public class LinearHashTable {
    private static final Random RANDOM = new Random();
    private Integer[] array;
    private QuadraticHashTable[] hashTable;
    private Integer[] h;
    private int b;
    private boolean containsNull;

    LinearHashTable(Integer[] array) {
        this.array = array.clone();
        buildTable();
    }

    private void buildTable() {
        containsNull = false;
        hashTable = new QuadraticHashTable[array.length];
        b = (int) (Math.log10(hashTable.length) / Math.log10(2) + 1);
        buildHTable();
        for (Integer number : array) {
            if (number == null) {
                containsNull = true;
            } else {
                int index = getHashCode(number);
                if (index >= hashTable.length || index < 0) {
                    throw new RuntimeException(index + "key Out Of Bound");
                } else {
                    if (hashTable[index] == null) {
                        hashTable[index] = new QuadraticHashTable(new Integer[]{number});
                    } else if (!hashTable[index].contains(number)) {
                        hashTable[index] = new QuadraticHashTable(concat(hashTable[index].getTable(), new Integer[]{number}));
                    }
                }
            }
        }
    }

    private void buildHTable() {
        h = new Integer[31];
        for (int i = 0; i < h.length; i++) {
            h[i] = RANDOM.nextInt(1 << (b - 1));
        }
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

    private Integer[] concat(Integer[] a, Integer[] b) {
        Integer[] ans = new Integer[a.length + b.length];
        int i = 0;
        for (Integer number : a) {
            ans[i++] = number;
        }
        for (Integer number : b) {
            ans[i++] = number;
        }
        return ans;
    }

    public boolean contains(Integer number) {
        if (number == null) {
            return containsNull;
        } else {
            int index = getHashCode(number);
            if (index < 0 || index >= hashTable.length) {
                throw new RuntimeException(index + "key Out Of Bound");
            } else
                return hashTable[index] != null && hashTable[index].contains(number);
        }
    }
}