package project4;

import models.BinaryTree;

import java.util.Arrays;

import static project4.BinaryTreeConv.*;

public class Test {
    public static void main(String[] args) {

        BinaryTree<Character> tree;

        tree = createBTreeFromPre("abdehicfjg".toCharArray(), "dbheiafjcg".toCharArray());
        System.out.println("Postorder traversal:  " + Arrays.toString(tree.postorderTraversal()));
        System.out.println("Levelorder traversal: " + Arrays.toString(tree.levelorderTraversal()) + "\n");
        tree = createBTreeFromPost("dhiebjfgca".toCharArray(), "dbheiafjcg".toCharArray());
        System.out.println("Preorder traversal:   " + Arrays.toString(tree.preorderTraversal()));
        System.out.println("Levelorder traversal: " + Arrays.toString(tree.levelorderTraversal()) + "\n");

        System.out.println("============================================================\n");

        tree = createBTreeFromPre("abdecfgh".toCharArray(), "dbeagfhc".toCharArray());
        System.out.println("Postorder traversal:  " + Arrays.toString(tree.postorderTraversal()));
        System.out.println("Levelorder traversal: " + Arrays.toString(tree.levelorderTraversal()) + "\n");
        tree = createBTreeFromPost("debghfca".toCharArray(), "dbeagfhc".toCharArray());
        System.out.println("Preorder traversal:   " + Arrays.toString(tree.preorderTraversal()));
        System.out.println("Levelorder traversal: " + Arrays.toString(tree.levelorderTraversal()) + "\n");

        System.out.println("============================================================");

        tree = createBTreeFromPre("abdhilmecfjngko".toCharArray(), "hdlimbeafjnckog".toCharArray());
        System.out.println("Postorder traversal:  " + Arrays.toString(tree.postorderTraversal()));
        System.out.println("Levelorder traversal: " + Arrays.toString(tree.levelorderTraversal()) + "\n");
        tree = createBTreeFromPost("hlmidebnjfokgca".toCharArray(), "hdlimbeafjnckog".toCharArray());
        System.out.println("Preorder traversal:   " + Arrays.toString(tree.preorderTraversal()));
        System.out.println("Levelorder traversal: " + Arrays.toString(tree.levelorderTraversal()) + "\n");
    }
}