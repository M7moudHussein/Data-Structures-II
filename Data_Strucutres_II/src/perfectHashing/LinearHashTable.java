package perfectHashing;

import java.util.*;

public class LinearHashTable implements PerfectHashTable {
    private static final Random RANDOM = new Random();
    private List<Integer> keys;
    private List<QuadraticHashTable> hashTable;
    private Integer[] h;
    private boolean containsNull;
    private int fullSize;
    private long size, a, c, p = 10007;

    LinearHashTable() {
        containsNull = false;
        keys = new ArrayList<>();
        fullSize = 0;
    }

    @Override
    public void build() {
        long start = System.currentTimeMillis();
        hashTable = new ArrayList<>();
        long totalSize;
        do {
            clearHashTable();
            totalSize = 0;
            int b = (int) (Math.log10(hashTable.size()) / Math.log10(2) + 1);
            buildHTable(b);
            for (Integer number : keys) {
                if (number == null) containsNull = true;
                else {
                    int index = getHashCode(number);
                    if (index >= hashTable.size() || index < 0) throw new RuntimeException(index + "key Out Of Bound");
                    else {
                        if (hashTable.get(index) == null) hashTable.set(index, new QuadraticHashTable());
                        hashTable.get(index).add(number);
                    }
                }
            }
            for (QuadraticHashTable table : hashTable) {
                if (table != null) {
                    table.build();
                    totalSize += table.fullSize();
                    size += table.size();
                    fullSize += table.fullSize();
                } else {
                    fullSize++;
                }
            }
        } while (totalSize > 5 * keys.size());
    }

    private void clearHashTable() {
        if (hashTable.size() != keys.size()) {
            for (int i = 0; i < keys.size(); i++) {
                hashTable.add(null);
            }
        } else {
            for (int i = 0; i < hashTable.size(); i++) {
                hashTable.set(i, null);
            }
        }
    }

//    private void buildHTable(int b) {
//        h = new Integer[31];
//        for (int i = 0; i < h.length; i++) {
//            h[i] = RANDOM.nextInt(1 << (b - 1));
//        }
//    }
//
//    private int getHashCode(Integer number) {
//        long hashCode = 0;
//        for (int i = 0; i < h.length; i++) {
//            if ((number & (1 << i)) != 0) {
//                hashCode = hashCode ^ h[i];
//            }
//        }
//        return (int)hashCode;
//    }
    
    private void buildHTable(int b) {
	a = RANDOM.nextInt(Integer.MAX_VALUE);
	c = RANDOM.nextInt(Integer.MAX_VALUE);
	p = -1;
	for(int i = keys.size(); p == -1;i++) {
	    if(isPrime(i))
		p = i;
	}
    }

    private int getHashCode(Integer number) {
        long hashCode = ((a * number + c) % p) % keys.size();
        return (int)hashCode;
    }
    
    private boolean isPrime(int num) {
	for(int i = 2; i * i <= num; i++) {
	    if(num % i == 0) {
		return false;
	    }
	}
	return true;
    }

    @Override
    public boolean contains(Integer number) {
        if (number == null) {
            return containsNull;
        } else {
            int index = getHashCode(number);
            if (index < 0 || index >= hashTable.size()) {
                throw new RuntimeException(index + "key Out Of Bound");
            } else
                return hashTable.get(index) != null && hashTable.get(index).contains(number);
        }
    }

    @Override
    public void clear() {
        if (hashTable != null) hashTable.clear();
        if (h != null) h = null;
        if (keys != null) keys.clear();
        size = 0;
        fullSize = 0;
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
    public int size() {
        return keys.size();
    }

    @Override
    public int fullSize() {
        return this.fullSize;
    }
}