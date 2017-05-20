import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int n = 1000000;
        Integer[] test = new Integer[n];
        for (int i = 0; i < n; i++) {
            test[i] = random.nextInt();
        }
        HashSet<Integer> set = new HashSet<Integer>();
        LinearHashTable hashTable = new LinearHashTable(test);
        Collections.addAll(set, test);
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
