package project3;

import models.GeneralizedLinkedList;

public class GllMain {
    public static void main(String[] args) {
        String s = "((ab))c()d(e(f(hi)((j)))kl)m";
        GeneralizedLinkedList<Character> gll = GeneralizedLinkedList.createCharGLL(s.toCharArray());
        System.out.println("Original GLL:\n" + gll);

        System.out.println("\nReversed GLL:");
        gll.reverse();
        System.out.println(gll);
        gll.reverse();

        System.out.println("\nAdding node(z) after node(k):");
        gll.addAfter('z', 'k');
        System.out.println(gll);

        System.out.println("\nAdding node(u) after node(x) (doesn't exist in GLL):");
        gll.addAfter('u', 'x');
        System.out.println(gll);
    }
}
