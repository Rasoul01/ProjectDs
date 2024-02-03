package models;

import java.util.*;

public class BinaryTree<E> {
    private final Node<E> head;

    public BinaryTree(Node<E> head) {
        this.head = head;
    }

    public Node<E> getHead() {
        return head;
    }

    public E[] preorderTraversal () {
        LinkedList<E> preorderTraversal = new LinkedList<>();
        preorderTraversal.add(head.data);
        if (head.lLink != null)
            preorderTraversal.addAll(Arrays.asList(new BinaryTree<>(head.lLink).preorderTraversal()));
        if (head.rLink != null)
            preorderTraversal.addAll(Arrays.asList(new BinaryTree<>(head.rLink).preorderTraversal()));

        return (E[]) preorderTraversal.toArray();
    }

    public E[] postorderTraversal () {
        LinkedList<E> postorderTraversal = new LinkedList<>();
        if (head.lLink != null)
            postorderTraversal.addAll(Arrays.asList(new BinaryTree<>(head.lLink).postorderTraversal()));
        if (head.rLink != null)
            postorderTraversal.addAll(Arrays.asList(new BinaryTree<>(head.rLink).postorderTraversal()));
        postorderTraversal.add(head.data);

        return (E[]) postorderTraversal.toArray();
    }

    public E[] levelorderTraversal () {
        LinkedList<E> levelorderTraversal = new LinkedList<>();
        LinkedList<Node<E>> queue = new LinkedList<>();
        queue.add(head);
        Node<E> node;
        while (!queue.isEmpty()) {
            node = queue.removeFirst();
            levelorderTraversal.add(node.data);
            if (node.lLink != null)
                queue.add(node.lLink);
            if (node.rLink != null)
                queue.add(node.rLink);
        }

        return (E[]) levelorderTraversal.toArray();
    }

    public static class Node<E> {
        Node<E> lLink;
        E data;
        Node<E> rLink;

        public void setlLink(Node<E> lLink) {
            this.lLink = lLink;
        }

        public void setData(E data) {
            this.data = data;
        }

        public void setrLink(Node<E> rLink) {
            this.rLink = rLink;
        }
    }
}