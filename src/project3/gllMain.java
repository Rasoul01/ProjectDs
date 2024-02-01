package project3;

import models.GeneralizedLinkedList;

public class gllMain {
    public static void main(String[] args) {
        String s = "((a,b)),c,(),d,(e,(f,(h,i),((j))),k,l),m";
        GeneralizedLinkedList<Character> gll = GeneralizedLinkedList.createCharGLL(s.toCharArray());
        System.out.println("Original GLL: " + gll);

        System.out.println("\nAdding node(z) after node(k):");
        gll.addAfter('z', 'k');
        System.out.println(gll);

        System.out.println("\nAdding node(u) after node(x) (doesn't exist in GLL):");
        gll.addAfter('u', 'x');
        System.out.println(gll);

        System.out.println("\nReversed GLL:");
        GeneralizedLinkedList<Character> revGLL = gll.reverse();
        System.out.println(revGLL);
    }
}
