package eg.edu.alexu.csd.filestructure.avl.tester;

import eg.edu.alexu.csd.filestructure.avl.IDictionary;
import eg.edu.alexu.csd.filestructure.avl.impl.Dictionary;

import java.io.File;
import java.util.Scanner;

public class DictionaryRunner {
    private static IDictionary dictionary;

    public static void main(String[] args) {
        dictionary = new Dictionary();
        File dictionaryFile = new File(System.getProperty("user.home") + File.separator + "dictionary.txt");
        dictionary.load(dictionaryFile);
        boolean exitFlag = false;
        Scanner scanner = new Scanner(System.in);
        while (exitFlag == false) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    choice1(scanner);
                    break;
                case "2":
                    choice2(scanner);
                    break;
                case "3":
                    choice3(scanner);
                    break;
                case "4":
                    choice4();
                    break;
                case "5":
                    choice5();
                    break;
                case "6":
                    exitFlag = true;
                    break;
                default:
                    System.out.println("Wrong Choice!");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.print("[1] Insert a new word.\n" + "[2] Search for a word.\n" + "[3] Delete a word.\n"
                + "[4] Print dictionary size.\n" + "[5] Print the hight of the  dictionary's avl tree.\n"
                + "[6] exit.\n" + ">>> ");
    }

    private static void choice1(Scanner scanner) {
        System.out.print(">>> ");
        String word = scanner.nextLine();
        if (!dictionary.insert(word)) {
            System.out.println("Word already exists!");
        }
    }

    private static void choice2(Scanner scanner) {
        System.out.print(">>> ");
        String word = scanner.nextLine();
        if (dictionary.exists(word)) {
            System.out.println("Word exists.");
        } else {
            System.out.println("The word does not exist.");
        }
    }

    private static void choice3(Scanner scanner) {
        System.out.print(">>> ");
        String word = scanner.nextLine();
        if (!dictionary.delete(word)) {
            System.out.println("The word does not exist!");
        }
    }

    private static void choice4() {
        System.out.println(dictionary.size());
    }

    private static void choice5() {
        System.out.println(dictionary.height());
    }

}