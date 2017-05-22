import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int n = 100000;
        System.out.println("Test Size =\t" + n);
        List<Integer> test = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            test.add(random.nextInt());
        }
        HashSet<Integer> set = new HashSet<>(test);
        PerfectHashTable hashTable = new QuadraticHashTable();
        hashTable.addAll(test);
        System.out.println("Building...");
        long t = System.currentTimeMillis();
        hashTable.build();
        System.out.println("Time:\t" + (System.currentTimeMillis() - t) + " ms.");
        System.out.println("Memory:\t" + hashTable.fullSize() + " Slots\n");

        for (Integer aTest : test) {
            if (hashTable.contains(aTest) != set.contains(aTest)) {
                System.out.println("Error + " + aTest);
                System.exit(1);
            }
        }

        System.out.println("Success 1!");

        for (int i = 0; i < 500; i++) {
            int temp = random.nextInt();
            if (hashTable.contains(temp) != set.contains(temp)) {
                System.out.println("Error + " + temp);
                System.exit(1);
            }
        }

        System.out.println("Success 2!");

    }
}
