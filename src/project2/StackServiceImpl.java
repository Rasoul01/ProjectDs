package project2;

import models.Stack;

import java.util.LinkedList;

public class StackServiceImpl implements StackService{
    @Override
    public void push(Stack stack, Object data) {

        LinkedList<Object> stackList = stack.getStackList();
        stackList.add(data);
        stack.setStackList(stackList);
        
    }

    @Override
    public Object pop(Stack stack) {

        LinkedList<Object> stackList = stack.getStackList();
        Object element =  stackList.get(stackList.size()-1);
        stackList.remove(stackList.size()-1);
        stack.setStackList(stackList);
        return element;

    }

    @Override
    public Object peek(Stack stack) {

        LinkedList<Object> stackList = stack.getStackList();
        Object element = stackList.get(stackList.size()-1);
        return element;

    }

    @Override
    public boolean isEmpty(Stack stack) {

        int size = stack.getStackList().size();
        return size==0;

    }

}
