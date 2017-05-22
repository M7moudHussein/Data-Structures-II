package perfectHashing;

import java.util.Random;

public abstract class PerfectHashTable {
    
    private static final Random RANDOM = new Random();
    protected long a, b, p;
    
    protected void buildHTable() {
	a = RANDOM.nextInt((int)p) + 1;
	b = RANDOM.nextInt((int)p) + 1;
    }

    protected int getHashCode(Integer number, int m) {
        long hashCode = ((a * number + b) % p) % m;
        return (int)hashCode;
    }
    
    protected void makePrime(int m) {
	p = -1;
        for(int i = m + 1; p == -1;i++) {
	    if(isPrime(i))
		p = i;
	}
    }
    
    private boolean isPrime(int num) {
	for(int i = 2; i * i <= num; i++) {
	    if(num % i == 0) {
		return false;
	    }
	}
	return true;
    }
    
    public abstract boolean contains(Integer key);
    public abstract void clear();
    public abstract int fullSize();
    public abstract int size();
    public abstract void build();
}
