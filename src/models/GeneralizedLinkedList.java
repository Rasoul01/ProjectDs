package models;

import java.util.Arrays;
import java.util.Stack;

public class GeneralizedLinkedList<E> {

    private Node<E> head;

    public GeneralizedLinkedList(Node<E> head) {
        this.head = head;
    }

    public void addAfter (E newNodeData, E desiredNodeData) {
        Node<E> desiredNode = find(desiredNodeData);
        Node<E> newNode = new Node<>(newNodeData, null);
        if (desiredNode != null) {
            newNode.nextLink = desiredNode.nextLink;
            desiredNode.nextLink = newNode;
        } else {
            Node<E> node = head;
            while (node.nextLink != null)
                node = node.nextLink;
            node.nextLink = newNode;
        }
    }

    public Node<E> find (E data) {
        Node<E> desiredNode = null;
        for (Node<E> node = head; node != null; node = node.nextLink){
            if (node.tag == Node.isData) {
                if (node.data == data) {
                    desiredNode = node;
                    break;
                }
            } else {
                GeneralizedLinkedList<E> newGLL = new GeneralizedLinkedList<>(node.dlink);
                desiredNode = newGLL.find(data);
            }
        }
        return desiredNode;
    }

    public static GeneralizedLinkedList<Character> createCharGLL(char[] input) {

        GeneralizedLinkedList<Character> returnVal = new GeneralizedLinkedList<>(null);
        Node<Character> newNode = null;
        Node<Character> lastNode = new Node<>(Node.isDLink);    // flag is not important

        for (int i = 0; i < input.length; i++) {

            if ((int)input[i] >= 97 && (int)input[i] <= 122 ) {
                newNode = new Node<>(input[i], null);

                lastNode.nextLink = newNode;
                lastNode = newNode;

            } else if (input[i] == '(') {
                newNode = new Node<>(Node.isDLink);
                lastNode.nextLink = newNode;
                lastNode = newNode;
                int rightParenthesisIndex = findRightParenthesisIndex(input, i);
                newNode.dlink = createCharGLL(Arrays.copyOfRange(input, i + 1,rightParenthesisIndex)).head;
                i = rightParenthesisIndex;

            } else if (input[i] == ')' ) {
                break;
            }

            if (returnVal.head == null)
                returnVal.head = newNode;
        }

        return returnVal;
    }

    private static int findRightParenthesisIndex (char[] input, int leftParenthesisIndex) {
        Stack<Integer> leftParenthesesStack = new Stack<>();
        for (int i = 0; i < input.length; i++) {
            if (input[i] == '(') {
                leftParenthesesStack.push(i);
            } else if (input[i] == ')') {
                int index = leftParenthesesStack.pop();
                if (index == leftParenthesisIndex)
                    return i;
            }
        }

        return -1;
    }

    public void reverse () {
        for (Node<E> node = head; node != null; node = node.nextLink) {
            if (node.tag == Node.isDLink) {
                Node<E> temp = node.nextLink;
                node.nextLink = node.dlink;
                node.dlink = temp;
                new GeneralizedLinkedList<>(node.dlink).reverse();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("(");

        for (Node<E> node = head; node != null; node = node.nextLink){
            if (node.tag == Node.isData)
                str.append(node.data);
            else {
                GeneralizedLinkedList<E> newGLL = new GeneralizedLinkedList<>(node.dlink);
                str.append(newGLL.toString());
            }
        }
        str.append(")");

        return str.toString();
    }

    private static class Node<E> {
        boolean tag;
        E data;
        Node<E> dlink;
        Node<E> nextLink;

        private Node(boolean tag) {    //Creates a Node with default values
            this.tag = tag;
        }

        private Node(E data, Node<E> nextLink) {
            this.tag = isData;
            this.dlink = null;
            this.data = data;
            this.nextLink = nextLink;
        }

        private final static boolean isData = false;
        private final static boolean isDLink = true;
    }

}