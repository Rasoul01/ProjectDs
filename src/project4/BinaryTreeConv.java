package project4;

import models.BinaryTree;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BinaryTreeConv {

    public static void main(String[] args) {

        BinaryTree<Character> tree;
        Scanner scanner = new Scanner(System.in);
        String destOrder, inorder;
        int choice;

        do {
            System.out.print("""
                    ====================================================
                    1) Convert preorder traversal to postorder traversal
                    2) Convert postorder traversal to preorder traversal
                    3) Exit
                    Select:\s""");

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ignored) {
                choice = -1;
            }
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("\nEnter inorder traversal: ");
                inorder = scanner.nextLine();
                System.out.print("Enter preorder traversal: ");
                destOrder = scanner.nextLine();

                tree = createBTreeFromPre(destOrder.toCharArray(), inorder.toCharArray());
                System.out.println("Postorder traversal:  " + Arrays.toString(tree.postorderTraversal()));
                System.out.println("Levelorder traversal: " + Arrays.toString(tree.levelorderTraversal()) + "\n");

            } else if (choice == 2) {
                System.out.print("\nEnter inorder traversal: ");
                inorder = scanner.nextLine();
                System.out.print("Enter postorder traversal: ");
                destOrder = scanner.nextLine();

                tree = createBTreeFromPost(destOrder.toCharArray(), inorder.toCharArray());
                System.out.println("Preorder traversal:   " + Arrays.toString(tree.preorderTraversal()));
                System.out.println("Levelorder traversal: " + Arrays.toString(tree.levelorderTraversal()) + "\n");
            }
        } while (choice != 3);
    }

    public static BinaryTree<Character> createBTreeFromPre(char[] preorder, char[] inorder) {
        BinaryTree<Character> tree = new BinaryTree<>(new BinaryTree.Node<>());
        BinaryTree.Node<Character> head = tree.getHead();
        char[] rChildren, lChildren;
        int rootIndex = indexOf(inorder, preorder[0]);

        head.setData(preorder[0]);
        lChildren = Arrays.copyOfRange(inorder, 0, rootIndex);
        rChildren = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
        if (lChildren.length > 0)
            head.setlLink(createBTreeFromPre(Arrays.copyOfRange(preorder, 1, rootIndex + 1), lChildren).getHead());
        if (rChildren.length > 0)
            head.setrLink(createBTreeFromPre(Arrays.copyOfRange(preorder, rootIndex + 1, preorder.length), rChildren).getHead());
        return tree;
    }


    public static BinaryTree<Character> createBTreeFromPost(char[] postorder, char[] inorder) {
        BinaryTree<Character> tree = new BinaryTree<>(new BinaryTree.Node<>());
        BinaryTree.Node<Character> head = tree.getHead();
        char[] rChildren, lChildren;
        int rootIndex = indexOf(inorder, postorder[postorder.length - 1]);

        head.setData(postorder[postorder.length - 1]);
        lChildren = Arrays.copyOfRange(inorder, 0, rootIndex);
        rChildren = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
        if (lChildren.length > 0)
            head.setlLink(createBTreeFromPost(Arrays.copyOfRange(postorder, 0, rootIndex), lChildren).getHead());
        if (rChildren.length > 0)
            head.setrLink(createBTreeFromPost(Arrays.copyOfRange(postorder, rootIndex, postorder.length - 1), rChildren).getHead());

        return tree;
    }

    public static int indexOf (char[] array, char key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i;
            }
        }
        return -1;
    }
}
