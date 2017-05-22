package perfectHashing;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> test = readFile("test.txt");
        int n = test.size();
        System.out.println("Test Size =\t" + n);
//        for (int i = 0; i < n; i++) {
//            test.add(random.nextInt(100));
//        }
        HashSet<Integer> set = new HashSet<>(test);
        PerfectHashTable hashTable = new LinearHashTable();
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
            int temp = random.nextInt(Integer.MAX_VALUE);
            if (hashTable.contains(temp) != set.contains(temp)) {
                System.out.println("Error + " + temp);
                System.exit(1);
            }
        }

        System.out.println("Success 2!");

    }
    
    static List<Integer> readFile(String fileName) {
	List<Integer> numbers = new LinkedList<Integer>();
	try {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    String line = null;
	    while ((line = br.readLine()) != null) {
	      String[] values = line.split(",");
	      for (String str : values) {
	        numbers.add(Integer.parseInt(str));
	      }
	    }
	    br.close();
	} catch(IOException e) {
	    System.out.println("ERROR READING FILE");
	}
	return numbers;
    }
}
