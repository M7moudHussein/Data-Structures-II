package perfectHashing;

import java.util.*;

public class LinearHashTable extends PerfectHashTable{
    private static final Random RANDOM = new Random();
    private List<Integer> keys;
    private List<List<Integer>> hashTable;
    private List<QuadraticHashTable> quadHash;
    private boolean containsNull;
    private int fullSize, totalSize;
    private int size;

    LinearHashTable(List<Integer> keys) {
        containsNull = false;
        this.keys = new ArrayList<>();
        this.keys.addAll(keys);
        makePrime(keys.size());
        fullSize = 0;
    }

    public void build() {
        long start = System.currentTimeMillis();
        hashTable = new ArrayList<List<Integer>>();
        quadHash = new ArrayList<QuadraticHashTable>();
        int re = 0;
        do {
            clearHashTable();
            totalSize = 0;
            fullSize = 0;
            int b = (int) (Math.log10(hashTable.size()) / Math.log10(2) + 1);
            buildHTable();
            for (Integer number : keys) {
                if (number == null) containsNull = true;
                else {
                    int index = getHashCode(number, keys.size());
                    if (index >= hashTable.size() || index < 0) throw new RuntimeException(index + "key Out Of Bound");
                    else {
                        if (hashTable.get(index) == null) hashTable.set(index, new ArrayList<Integer>());
                        hashTable.get(index).add(number);
                    }
                }
            }
            buildQuadratic();
            re++;
        } while (totalSize > 4 * keys.size());
    }
    
    private void buildQuadratic() {
	int quad = 0;
	for(List<Integer> list : hashTable) {
	    if(list != null) {
		Collections.sort(list);
	    	List<Integer> reqList = new ArrayList<Integer>();
	    	for(int i = 0; i < list.size(); i++) {	
	    	    reqList.add(list.get(i));
	    	    while(i + 1 < list.size() && list.get(i).equals(list.get(i + 1)))
	    		i++;
	    	}
	    	quadHash.set(quad, new QuadraticHashTable(reqList));
//	    	System.out.println(reqList + " " + list);
	    	quadHash.get(quad).build();
	    	totalSize += (reqList.size() * reqList.size());
	    	fullSize += (reqList.size() * reqList.size());
	    } else {
		fullSize++;
	    }
	    quad++;
	}
    }

    private void clearHashTable() {
        hashTable.clear();
        quadHash.clear();
        for(int i = 0; i < keys.size(); i++) {
            quadHash.add(null);
            hashTable.add(null);
        }
    }
    
    public boolean contains(Integer number) {
        if (number == null) {
            return containsNull;
        } else {
            int index = getHashCode(number, keys.size());
            if (index < 0 || index >= hashTable.size()) {
                throw new RuntimeException(index + "key Out Of Bound");
            } else
                return quadHash.get(index) != null && quadHash.get(index).contains(number);
        }
    }

    public void clear() {
        if (hashTable != null) hashTable.clear();
        if (keys != null) keys.clear();
        if(quadHash != null) quadHash.clear();
        size = 0;
        fullSize = 0;
    }

    public int size() {
        return keys.size();
    }
    
    public int fullSize() {
        return this.fullSize;
    }
}