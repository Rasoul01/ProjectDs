package models;

import java.util.LinkedList;

public class Stack {

    private LinkedList<Object> stackList = new LinkedList<>();



    public Stack() {
    }


    public LinkedList<Object> getStackList() {
        return stackList;
    }

    public void setStackList(LinkedList<Object> stackList) {
        this.stackList = stackList;
    }

}
