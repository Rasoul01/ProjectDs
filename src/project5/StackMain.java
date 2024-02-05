package project5;

import tools.CustomException;

import java.util.*;

public class StackMain {
    public static void main(String[] args) throws CustomException {
        int choice;
        do {
            System.out.print("Enter number of elements (Zero to Quit): ");
            try {
                choice = new Scanner(System.in).nextInt();
            } catch (InputMismatchException ignored) {
                choice = -1;
            }

            if (choice > 0) {
                LinkedList<Object> input = new LinkedList<>();
                for (int i = 1; i <= choice; i++) {
                    input.offer(i);
                }

                List<Object> allPermutations = StackPermutation.permute(input);
                printList(allPermutations);
            }
        } while (choice != 0);

    }

    public static void printList(List<Object> list) {
        int digitCount = (int) Math.log10(list.size()) + 1;
        int n = 1;
        for (Object el : list) {
            System.out.printf("%" + digitCount + "d) %s%n", n, el);
            n++;
        }
    }
}
